package com.zjuqsc.database;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class DatabaseBufferManagerImpl<T extends BlockBuffer> implements BufferManager<T> {

    int pointer = 0;
    final int size;

    private PriorityQueue<Integer> handleHoles;
    private HashMap<Integer, Boolean> isLocked;
    private HashMap<Integer, T> buffersToKill;
    private int largestIndex = 0;

    LinkedHashMap<Integer, T> buffers;

    public void tryToRemoveHandle(int handle, T value) {
        if (isLocked.get(handle) != null && isLocked.get(handle)) {
            buffersToKill.put(handle, value);
        } else {
            removeHandle(handle);
        }
    }

    protected void removeHandle(int handle) {
        handleHoles.add(handle);
    }

    protected void killHandle(int handle) {
        buffersToKill.remove(handle);
    }

//    protected void useHandle(int handle) {
//    }

    /**
     * Retrieve the smallest available handle.
     *
     * @return The smallest handle available;
     */
    protected int getAvailableHandle() {
        if (handleHoles.size() == 0) {
            int handle = largestIndex;
            largestIndex += 1;
            return handle;
        } else {
            return handleHoles.poll();
        }
    }


    public LinkedHashMap<Integer, T> getBuffers() {
        return this.buffers;
    }

    public DatabaseBufferManagerImpl(int size) {
        this.size = size;
        handleHoles = new PriorityQueue<>();
        isLocked = new HashMap<>();
        buffersToKill = new HashMap<>();
        buffers = new LinkedHashMap<Integer, T>(size, 1, true) {
            public T putWithHandle(Integer key, T value) {
//            useHandle(key);
                return put(key, value);
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, T> eldest) {
                if (size() > size) {
                    // May need some lock.
                    tryToRemoveHandle(eldest.getKey(), eldest.getValue());
                    return true;
                }
                return false;
            }
        };
    }


    @Override
    public DatabaseBuffer load() {
        return null;
    }

    public void lock(int handle) {
        isLocked.put(handle, true);
    }

    public void release(int handle) {
        isLocked.remove(handle);
        if (buffersToKill.get(handle) != null) {
            killHandle(handle);
        }
    }

    public boolean isLocked(int handle) {
        return isLocked.get(handle) == null && isLocked.get(handle);
    }

    public T get(int handle) {
        T result = buffers.get(handle);
        if (result == null) {
            result = buffersToKill.get(handle);
        }
        return result;
    }

    public T put(int handle, T value) {
        buffers.put(handle, value);
        return value;
    }

    public int loadBuffer(T buffer) {
        int handle = getAvailableHandle();
        put(handle, buffer);
        return handle;
    }

}

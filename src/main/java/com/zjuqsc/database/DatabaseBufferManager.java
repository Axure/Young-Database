package com.zjuqsc.database;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class DatabaseBufferManager implements BufferManager<FourKBlockBuffer> {

    int pointer = 0;
    private static final int size = 5000;

    private PriorityQueue<Integer> handleHoles;
    private HashMap<Integer, Boolean> isLocked;
    private HashMap<Integer, FourKBlockBuffer> buffersToKill;
    private int largestIndex = 0;

    public void tryToRemoveHandle(int handle, FourKBlockBuffer value) {
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

    LinkedHashMap<Integer, FourKBlockBuffer> buffers = new LinkedHashMap<Integer, FourKBlockBuffer>(size, 1, true) {
        public FourKBlockBuffer putWithHandle(Integer key, FourKBlockBuffer value) {
//            useHandle(key);
            return put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, FourKBlockBuffer> eldest) {
            if (size() > size) {
                // May need some lock.
                tryToRemoveHandle(eldest.getKey(), eldest.getValue());
                return true;
            }
            return false;
        }
    };

    public LinkedHashMap<Integer, FourKBlockBuffer> getBuffers() {
        return this.buffers;
    }

    public DatabaseBufferManager() {
        handleHoles = new PriorityQueue<>();
    }

    int loadBuffer(FilePointer filePointer) throws IOException {
//        pointer++;
        int handle = getAvailableHandle();

        put(handle, new FourKBlockBuffer(filePointer));
        return handle;
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
            buffersToKill.remove(handle);
        }
    }

    public boolean isLocked(int handle) {
        return isLocked.get(handle) == null && isLocked.get(handle);
    }

    public FourKBlockBuffer get(int handle) {
        FourKBlockBuffer result = buffers.get(handle);
        if (result == null) {
            result = buffersToKill.get(handle);
        }
        return result;
    }

    public FourKBlockBuffer put(int handle, FourKBlockBuffer value) {
        buffers.put(handle, value);
        return value;
    }
}


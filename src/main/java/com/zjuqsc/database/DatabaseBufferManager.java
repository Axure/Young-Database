package com.zjuqsc.database;

import java.io.IOException;
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
    private int largestIndex = 0;

    protected void removeHandle(int handle) {
        handleHoles.add(handle);
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
                removeHandle(eldest.getKey());
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
        pointer++;
        int handle = getAvailableHandle();

        buffers.put(handle, new FourKBlockBuffer(filePointer));
        return handle;
    }


    @Override
    public DatabaseBuffer load() {
        return null;
    }
}


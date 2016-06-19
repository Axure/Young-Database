package com.zjuqsc.database;

import java.io.IOException;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class DatabaseBufferManager extends DatabaseBufferManagerImpl<FourKBlockBuffer> {

    private static final int size = 5000;

    public DatabaseBufferManager() {
        super(size);
    }

    /**
     * @param filePointer
     * @return The handle of the newly loaded buffer.
     * @throws IOException
     */
    public int loadBuffer(FilePointer filePointer) throws IOException {
//        pointer++;
        return loadBuffer(new FourKBlockBuffer(filePointer));
    }
}


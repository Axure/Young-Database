package com.zjuqsc.database;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

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

    public int loadBuffer(RandomAccessFile file) throws IOException {
//        pointer++;
        return loadBuffer(new FourKBlockBuffer(file, 0));
    }

    public int loadAndLockBuffer(RandomAccessFile file) throws IOException {
//        pointer++;
        return loadAndLockBuffer(new FourKBlockBuffer(file, 0));
    }
}


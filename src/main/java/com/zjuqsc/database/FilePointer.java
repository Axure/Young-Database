package com.zjuqsc.database;

import java.io.RandomAccessFile;

/**
 * Created by zhenghu on 2016-06-19.
 */

/**
 * A generic interface for pointing to positions on the disk.
 */
public interface FilePointer {
    /**
     * Get the file.
     *
     * @return The file.
     */
    RandomAccessFile getFile();

    /**
     * Get the position in that file.
     *
     * @return The position.
     */
    int getPosition();
}

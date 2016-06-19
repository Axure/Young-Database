package com.zjuqsc.database;

import java.io.RandomAccessFile;

/**
 * Created by zhenghu on 2016-06-19.
 */
public interface FilePointer {
    RandomAccessFile  getFile();
    int getPosition();
}

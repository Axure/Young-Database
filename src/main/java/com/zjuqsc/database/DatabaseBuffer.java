package com.zjuqsc.database;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by zhenghu on 2016-06-19.
 */
public interface DatabaseBuffer extends Storage {
    int getSize();

    void move(int length);
    void proceed();

    void persist() throws IOException;

    RandomAccessFile getFile();
    int getIndex();
}

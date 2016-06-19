package com.zjuqsc.database;

/**
 * Created by zhenghu on 2016-06-19.
 */
public interface DatabaseBuffer extends Storage {
    long getSize();

    void move(int length);
    void proceed();
}

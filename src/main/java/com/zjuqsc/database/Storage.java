package com.zjuqsc.database;

/**
 * Created by zhenghu on 2016-06-19.
 */
public interface Storage {
    void write(byte datum);
    void writeAt(int index, byte datum);
}

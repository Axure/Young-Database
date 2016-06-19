package com.zjuqsc.database;

/**
 * Created by zhenghu on 2016-06-19.
 */

/**
 * The generic interface for a storage. May be on disk or in memory.
 */
public interface Storage {
    void write(byte datum);

    void writeAt(int index, byte datum);
}

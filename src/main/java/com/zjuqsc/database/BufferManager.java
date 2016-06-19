package com.zjuqsc.database;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhenghu on 2016-06-19.
 */
public interface BufferManager<TBuffer> {

    DatabaseBuffer load();
    LinkedHashMap<Integer, TBuffer> getBuffers();

}

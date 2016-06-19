package com.zjuqsc.database;

import java.util.Map;

/**
 * Created by zhenghu on 2016-06-19.
 */
public interface BplusTree<K extends Comparable<K>, V> extends Map<K, V> {

    int nodeWidth();

}

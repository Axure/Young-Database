package com.zjuqsc.database;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class Key {

    enum Type {
        INTEGER, VARCHAR, TEXT
    }

    private String name;
    private Type type;
}

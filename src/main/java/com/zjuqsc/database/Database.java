package com.zjuqsc.database;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class Database {
    private final String name;
    private BufferManager<FourKBlockBuffer> bufferManager;


    public Database(String name) {
        this.name = name;
        this.bufferManager = new DatabaseBufferManager();
    }

    public void persist() {
        persistAll();
    }

    public void persistAll() {

    }

    public void persistDatabase() {

    }

    public void persistTables() {

    }
}

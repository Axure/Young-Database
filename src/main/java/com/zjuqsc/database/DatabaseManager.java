package com.zjuqsc.database;

import com.zjuqsc.database.Database;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class DatabaseManager {

    public DatabaseManager() {

    }

    public void createDatabase(String name) {
        Database database = new Database(name);

    }

    public void loadDatabase(String name) {

    }
}

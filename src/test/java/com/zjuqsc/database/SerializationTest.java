package com.zjuqsc.database;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.Future;

/**
 * Created by zhenghu on 2016-06-20.
 */
public class SerializationTest extends TestCase {

    @Test
    public void testDatabaseSerialization() {
        Database database = new Database("Haha");
        byte[] bytes = new byte[100];

        Future<byte[]> result = database.serializeTo(bytes);

        try {
            result.get();

            assertEquals(bytes[0], 33);
            assertEquals(bytes[1], 33);
            assertEquals(bytes[2], 33);
            assertEquals(bytes[3], 33);
            assertEquals(bytes[4], 33);

            assertEquals(1, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

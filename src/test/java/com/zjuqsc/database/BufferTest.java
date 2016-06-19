package com.zjuqsc.database;

import org.junit.Test;

import java.io.RandomAccessFile;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class BufferTest {

    @Test
    public void testBuffer() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("src/test/resources/test.txt", "rw");
            FourKBlockBuffer fourKBlockBuffer = new FourKBlockBuffer(randomAccessFile, 0);
            fourKBlockBuffer.write((byte)33);
            fourKBlockBuffer.writeAt(1, (byte)33);
            fourKBlockBuffer.writeAt(2, (byte)33);
            fourKBlockBuffer.writeAt(3, (byte)33);
            fourKBlockBuffer.writeAt(4, (byte)33);

            fourKBlockBuffer.persist();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

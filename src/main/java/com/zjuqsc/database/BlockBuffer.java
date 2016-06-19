package com.zjuqsc.database;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by zhenghu on 2016-06-19.
 */
public abstract class BlockBuffer implements DatabaseBuffer {

    protected static final int size = 4096;
    protected int pointer = 0;
    protected byte[] bytes;



    public BlockBuffer() {
        this.bytes = new byte[size];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void move(int length) {
        pointer += length;
    }

    @Override
    public void proceed() {
        pointer += 1;
    }

    @Override
    public void persist() throws IOException {

    }

    @Override
    public abstract RandomAccessFile getFile();

    @Override
    public abstract int getIndex();


    @Override
    public void write(byte datum) {
        bytes[pointer] = datum;
    }



    public void pointTo(int pointer) {
        this.pointer = pointer;
    }

    public byte getCurrent() throws ArrayIndexOutOfBoundsException {
        return bytes[pointer];
    }


}

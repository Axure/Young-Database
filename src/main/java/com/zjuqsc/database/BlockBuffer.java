package com.zjuqsc.database;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class BlockBuffer implements DatabaseBuffer {

    protected static final int size = 4096;
    protected int pointer = 0;
    protected byte[] bytes;



    public BlockBuffer() {
        this.bytes = new byte[size];
    }

    @Override
    public long getSize() {
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

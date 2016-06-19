package com.zjuqsc.database;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class BufferPointer<TBuffer extends Storage> implements StoragePointer {

    private final TBuffer blockBuffer;

    public BufferPointer(TBuffer blockBuffer) {
        this.blockBuffer = blockBuffer;
    }

    @Override
    public TBuffer getStorage() {
        return blockBuffer;
    }
}

package com.zjuqsc.database;

import java.util.concurrent.Future;

/**
 * Created by zhenghu on 2016-06-19.
 */
public abstract class AbstractDatabaseSerializable implements DatabaseSerializable {

    public abstract Future<byte[]> serializeTo(byte[] byteBuffer);


    public abstract Future<byte[]> deserializeOutOf(byte[] byteBuffer);

    public Future<byte[]> deserializeFrom(BufferPointer bufferPointer) {
        return deserializeOutOf(null);
    }

    public Future<byte[]> serializeAt(BufferPointer bufferPointer) {
        return serializeTo(null);
    }
}

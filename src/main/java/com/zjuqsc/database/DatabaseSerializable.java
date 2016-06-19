package com.zjuqsc.database;

import java.util.concurrent.Future;

/**
 * Created by zhenghu on 2016-06-19.
 */
public interface DatabaseSerializable {
    int getSerializeLength();

    Future<byte[]> serializeTo(byte[] byteBuffer);


    Future<Boolean> deserializeOutOf(byte[] byteBuffer);


}

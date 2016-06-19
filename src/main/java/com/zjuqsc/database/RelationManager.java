package com.zjuqsc.database;


/**
 * Created by zhenghu on 2016-06-19.
 */
public class RelationManager {

    public BufferPointer serializeToBuffer(BufferPointer bufferPointer, Relation relation) {
        BlockBuffer buffer = bufferPointer.getStorage();
        String relationName = relation.getName();
        for (int i = 0; i < relationName.length(); ++i) {
            buffer.write((byte)relationName.charAt(i)); // Only English support.
            buffer.proceed();
        }
    }

    public Relation deserializeFromBuffer(BufferPointer bufferPointer) throws Exception {

    }

    public Relation deserializeFromFile(FilePointer filePointer) throws Exception {

    }
}

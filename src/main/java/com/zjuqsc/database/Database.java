package com.zjuqsc.database;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class Database extends AbstractDatabaseSerializable {
    private final String name;
    private BufferManager<FourKBlockBuffer> bufferManager;
    private final ForkJoinPool pool = new ForkJoinPool();

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

    @Override
    public int getSerializeLength() {
        return 5;
    }

    public Future<byte[]> serializeTo(byte[] byteBuffer)  {
        Future<byte[]> future = pool.submit(() -> {
            int length = getSerializeLength();
            for (int i = 0; i < length; ++i) {
                byteBuffer[i] = ((byte) 33);
            }
            return byteBuffer;
        });
        return future;
    }


    @Override
    public Future<byte[]> deserializeOutOf(byte[] byteBuffer) {
        return null;
    }

}

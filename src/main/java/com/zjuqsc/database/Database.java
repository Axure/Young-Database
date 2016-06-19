package com.zjuqsc.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class Database extends AbstractDatabaseSerializable {
    private final static String userHomePath = System.getProperty("user.home");
    private final String rootPath;
    private final static String dbDirectorySuffix = ".ydb";

    private final String name;
    private BufferManager<FourKBlockBuffer> bufferManager;
    private transient final ForkJoinPool pool = new ForkJoinPool();

    private transient Path directory;

    public Database(String name) {
        this.rootPath = userHomePath;
        this.name = name;
        this.bufferManager = new DatabaseBufferManager();
    }


    public Database(String name, String rootPath) {
        this.rootPath = rootPath;
        this.name = name;
        this.bufferManager = new DatabaseBufferManager();
    }

    public void persist() {
        persistAll();
    }

    public void persistAll() {

    }

    public void persistDirectory() throws IOException {
        File homeDirectory = new File(rootPath);
        File dbDirectory = new File(homeDirectory, dbDirectorySuffix);
        if (dbDirectory.isFile()) {
            throw new IOException("Directory already exist as a file!");
        }
        if (dbDirectory.isDirectory()) {

        } else {
            Files.createDirectories(dbDirectory.toPath());
        }
    }

    public void persistDatabase() {

    }

    public void persistTables() {

    }

    @Override
    public int getSerializeLength() {
        return 5;
    }

    public Future<byte[]> serializeTo(byte[] byteBuffer) {
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

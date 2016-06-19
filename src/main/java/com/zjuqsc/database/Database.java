package com.zjuqsc.database;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class Database extends AbstractDatabaseSerializable {
    private final static String userHomePath = System.getProperty("user.home");
    private final String rootPath;
    private boolean loaded = false;
    private final static String dbDirectorySuffix = ".ydb";

    private File homeDirectory;
    private File dbDirectory;
    private File thisDbDirectory;
    private File thisDbFile;

    private final String name;
    private DatabaseBufferManager bufferManager;
    private transient final ForkJoinPool pool = new ForkJoinPool();

    private transient Path directory;

    private void initPaths() {
        homeDirectory = new File(rootPath);
        dbDirectory = new File(homeDirectory, dbDirectorySuffix);
        thisDbDirectory = new File(dbDirectory, name);
        thisDbFile = new File(thisDbDirectory, name + ".db");
    }


    public Database(String name) {

        this.rootPath = userHomePath;
        this.name = name;
        this.bufferManager = new DatabaseBufferManager();
        initPaths();
    }


    public Database(String name, String rootPath) {

        this.rootPath = rootPath;
        this.name = name;
        this.bufferManager = new DatabaseBufferManager();
        initPaths();
    }

    public void load() throws IOException, ExecutionException, InterruptedException, InconsistencyException {
        loaded = true;
        RandomAccessFile databaseFile = new RandomAccessFile(thisDbFile, "rw");
        int bufferHandle = bufferManager.loadAndLockBuffer(databaseFile);
        FourKBlockBuffer buffer = bufferManager.get(bufferHandle);
        deserializeOutOf(buffer.getBytes()).get();
        bufferManager.release(bufferHandle);
    }

    public void persist() {
        persistAll();
    }

    public void persistAll() {

    }

    public void persistDirectory() throws IOException {
        if (thisDbDirectory.isFile()) {
            throw new IOException("Directory already exist as a file!");
        }
        if (thisDbDirectory.isDirectory()) {

        } else {
            Files.createDirectories(thisDbDirectory.toPath());
        }
    }

    public void persistDatabase() throws IOException, ExecutionException, InterruptedException {
        if (thisDbFile.isDirectory()) {
            throw new IOException("File already exist as a directory!");
        } else {
            if (thisDbFile.isFile()) {

            } else {
                Files.createFile(thisDbFile.toPath());
            }
        }
        RandomAccessFile databaseFile = new RandomAccessFile(thisDbFile, "rw");
        int bufferHandle = bufferManager.loadAndLockBuffer(databaseFile);
        FourKBlockBuffer buffer = bufferManager.get(bufferHandle);
        Future<?> task = serializeTo(buffer.getBytes());
        task.get();
        buffer.persist();
        bufferManager.release(bufferHandle);
    }

    public void persistTables() {

    }

    @Override
    public int getSerializeLength() {
        return this.name.getBytes().length;
    }

    public Future<byte[]> serializeTo(byte[] byteBuffer) {
        Future<byte[]> future = pool.submit(() -> {
            int length = getSerializeLength();
            byte[] nameBytes = name.getBytes();
            for (int i = 0; i < nameBytes.length; ++i) {
                byteBuffer[i] = nameBytes[i];
            }
            return byteBuffer;
        });
        return future;
    }

    enum Phase {
        NAME,
    }

    @Override
    public Future<Boolean> deserializeOutOf(byte[] byteBuffer) {
        return pool.submit(() -> {
            // Check if name
            Phase phase = Phase.NAME;
            byte[] nameBytes = name.getBytes();
            for (int i = 0; i < getSerializeLength(); ++i) {
                switch (phase) {
                    case NAME:
                        System.out.print((char) byteBuffer[i]);
                        if (nameBytes[i] != byteBuffer[i]) {
                            throw new InconsistencyException("Provided database name mismatch with the name deserialized from the file.");
                        }
                        break;
                    default:
                        break;
                }
            }

            return true;
        });
    }

    public boolean isLoaded() {
        return loaded;
    }
}

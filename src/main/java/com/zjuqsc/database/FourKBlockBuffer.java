package com.zjuqsc.database;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class FourKBlockBuffer extends BlockBuffer {
    protected static final int size = 4096;
    private int index;
    private RandomAccessFile file;

    FourKBlockBuffer() {
        super();
    }

    FourKBlockBuffer(FilePointer filePointer) throws IndexOutOfBoundsException, IOException {
        this(filePointer.getFile(), 0, filePointer.getPosition());
    }

    FourKBlockBuffer(RandomAccessFile file, int index) throws IndexOutOfBoundsException, IOException {
        this(file, index, 0);
    }

    FourKBlockBuffer(RandomAccessFile file, int index, int offset) throws IndexOutOfBoundsException, IOException {

        this.file = file;
        this.index = index;

        FileChannel fileChannel = file.getChannel();

        ByteOutputStream outputStream = new ByteOutputStream(size);
        WritableByteChannel outByteChannel = Channels.newChannel(outputStream);
        fileChannel.transferTo(offset + size * index, size, outByteChannel);
        bytes = outputStream.toByteArray();

        throw new IndexOutOfBoundsException("The offset is too large!");

    }


    @Override
    public void persist() throws IOException {

    }

    @Override
    public RandomAccessFile getFile() {
        return file;
    }

    @Override
    public int getIndex() {
        return index;
    }


}

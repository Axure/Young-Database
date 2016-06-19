package com.zjuqsc.database;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class FourKBlockBuffer extends BlockBuffer {
    protected static final int size = 4096;
    private int index;
    private int offset;
    private RandomAccessFile file;

    private FourKBlockBuffer() {
        super();
    }

    FourKBlockBuffer(FilePointer filePointer) throws IndexOutOfBoundsException, IOException {
        this(filePointer.getFile(), 0, filePointer.getPosition());
    }

    FourKBlockBuffer(RandomAccessFile file, int index) throws IndexOutOfBoundsException, IOException {
        this(file, index, 0);
    }

    FourKBlockBuffer(RandomAccessFile file, int index, int offset) throws IndexOutOfBoundsException, IOException {
        super();
        this.file = file;
        this.index = index;
        this.offset = offset;

        FileChannel fileChannel = file.getChannel();

        ByteOutputStream outputStream = new ByteOutputStream(size);
        WritableByteChannel outByteChannel = Channels.newChannel(outputStream);
        fileChannel.transferTo(offset + size * index, size, outByteChannel);
        byte[] outBytes = outputStream.toByteArray();
        for (int i = 0; i < outBytes.length; ++i) {
            bytes[i] = outBytes[i];
        }

//        throw new IndexOutOfBoundsException("The offset is too large!"); // TODO: when?

    }


    @Override
    public void persist() throws IOException {
        FileChannel fileChannel = file.getChannel();
        final MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, offset + size * index, size);
        for (int i = 0; i < size; ++i) {
            mappedByteBuffer.put(bytes[i]);
        }
    }

    @Override
    public RandomAccessFile getFile() {
        return file;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getOffset() {
        return offset;
    }


}

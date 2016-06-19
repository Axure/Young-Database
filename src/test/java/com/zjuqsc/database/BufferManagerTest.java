package com.zjuqsc.database;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.RandomAccessFile;
import java.util.PriorityQueue;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class BufferManagerTest extends TestCase {
    @Test
    public void testPriorityQueue() {
        PriorityQueue<Integer> tja = new PriorityQueue<Integer>();
        tja.add(55);
        tja.add(99);
        tja.add(1);
        tja.add(102);
        tja.add(54);
        tja.add(51);

        assertEquals((int) tja.poll(), 1);
        assertEquals((int) tja.poll(), 51);
        assertEquals((int) tja.poll(), 54);
        assertEquals((int) tja.poll(), 55);
        assertEquals((int) tja.poll(), 99);
        assertEquals((int) tja.poll(), 102);
    }

    @Test
    public void testBufferManagerImpl() {

        BlockBufferMock blockBufferMock1 = new BlockBufferMock();
        BlockBufferMock blockBufferMock2 = new BlockBufferMock();
        BlockBufferMock blockBufferMock3 = new BlockBufferMock();
        BlockBufferMock blockBufferMock4 = new BlockBufferMock();
        BlockBufferMock blockBufferMock5 = new BlockBufferMock();
        BlockBufferMock blockBufferMock6 = new BlockBufferMock();
        DatabaseBufferManagerImpl databaseBufferManager = new DatabaseBufferManagerImpl(4);
        int handle1 = databaseBufferManager.loadBuffer(blockBufferMock1);
        databaseBufferManager.lock(handle1);
        int handle2 = databaseBufferManager.loadBuffer(blockBufferMock2);
        int handle3 = databaseBufferManager.loadBuffer(blockBufferMock3);
        int handle4 = databaseBufferManager.loadBuffer(blockBufferMock4);
        int handle5 = databaseBufferManager.loadBuffer(blockBufferMock5);
        int handle6 = databaseBufferManager.loadBuffer(blockBufferMock6);

        assertTrue(databaseBufferManager.get(handle1) != null);
        databaseBufferManager.release(handle1);
        assertTrue(databaseBufferManager.get(handle1) == null);

        System.out.println(handle1);
        System.out.println(handle2);
        System.out.println(handle3);
        System.out.println(handle4);
        System.out.println(handle5);
        System.out.println(handle6);
    }
}

class BlockBufferMock extends BlockBuffer {

    @Override
    public RandomAccessFile getFile() {
        return null;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public int getOffset() {
        return 0;
    }
}
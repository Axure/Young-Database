package com.zjuqsc.database;

import junit.framework.TestCase;
import org.junit.Test;

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

        assertEquals((int)tja.poll(), 1);
        assertEquals((int)tja.poll(), 51);
        assertEquals((int)tja.poll(), 54);
        assertEquals((int)tja.poll(), 55);
        assertEquals((int)tja.poll(), 99);
        assertEquals((int)tja.poll(), 102);
    }
}

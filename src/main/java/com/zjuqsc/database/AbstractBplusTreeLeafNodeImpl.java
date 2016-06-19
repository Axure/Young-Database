package com.zjuqsc.database;

import java.util.ArrayList;

/**
 * Created by zhenghu on 2016-06-20.
 */
public abstract class AbstractBplusTreeLeafNodeImpl<K extends Comparable<K>, V> extends AbstractBplusTreeNodeImpl<K, V> {

    protected ArrayList<V> values;

    public AbstractBplusTreeLeafNodeImpl(int width) {
        super(width);
        values = new ArrayList<>();
    }

    @Override
    public boolean isInternal() {
        return false;
    }

    @Override
    public V getValue(K key) {
        int position = newPosition(key);
        if (position == -1) {
            return null;
        } else {
            return values.get(position);
        }
    }
}

package com.zjuqsc.database;

import java.util.ArrayList;

/**
 * Created by zhenghu on 2016-06-20.
 */
public abstract class AbstractBplusTreeNodeImpl<K extends Comparable<K>, V> implements BplusTreeNode<K, V> {
    protected int width;
    protected ArrayList<K> keys;
//    protected AbstractBplusTreeNodeImpl<K, V> parent;


    public boolean isInternal() {
        return true;
    }

    @Override
    public abstract AbstractBplusTreeNodeImpl<K, V> getParent();

//    protected BplusTreeNode<K, V>[] children;

    public int getWidth() {
        return width;
    }

    public AbstractBplusTreeNodeImpl(int width) {
        this.width = width;
        this.keys = new ArrayList<>();
    }

    public AbstractBplusTreeNodeImpl<K, V> getPossibleChild(K key) {
        return getNthChild(newPosition(key));
    }

    @Override
    public abstract AbstractBplusTreeNodeImpl<K, V> getNthChild(int n);

    @Override
    public abstract AbstractBplusTreeNodeImpl<K, V>[] getChildren();

    @Override
    public V getValue(K key) {
        return null;
    }

    public K getNthKey(int n) {
        return keys.get(n);
    }

    public int newPosition(K key) {
//        int position = 0;
//        while (keys.get(position).compareTo(key) < 0) {
//            position += 1;
//        }
//        return position;
        int lowerBound = 0;
        int upperBound = keys.size() - 1;
        if (key.compareTo(getNthKey(lowerBound)) < 0
                || key.compareTo(getNthKey(upperBound)) > 0) return -1;

        int middle = (lowerBound + upperBound) / 2;

        do {
            K lowerKey = getNthKey(lowerBound);
            K upperKey = getNthKey(upperBound);
            K midKey = getNthKey(middle);
            if (key.compareTo(midKey) == 0) return middle;
            if (key.compareTo(midKey) < 0) {
                if (key.compareTo(lowerKey) == 0) return lowerBound;
                upperBound = middle;
            } else {
                if (key.compareTo(upperKey) == 0) return upperBound;
                lowerBound = middle;
            }
            middle = (lowerBound + upperBound) / 2;
        } while (true);
    }

    public void insertKey(K key) {

    }

    public void insertKeyAt(int position, K key) {

    }

}

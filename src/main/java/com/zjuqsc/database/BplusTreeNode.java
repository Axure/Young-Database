package com.zjuqsc.database;

import java.util.Map;

/**
 * Created by zhenghu on 2016-06-19.
 */
public interface BplusTreeNode<K extends Comparable<K>, V> extends Map.Entry<K, V> {

    int width();

    BplusTreeNode<K, V> getParent();

    BplusTreeNode<K, V> getPossibleChild(K key);

    BplusTreeNode<K, V> getNthChild(int n);

    BplusTreeNode<K, V>[] getChildren();

    /**
     * Returns the new position where the key should be inserted.
     *
     * @param key
     * @return
     */
    int newPosition(K key);

    V getValue(K key);

    int getWidth();
}

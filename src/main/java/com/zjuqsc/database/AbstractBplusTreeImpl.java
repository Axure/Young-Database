package com.zjuqsc.database;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhenghu on 2016-06-20.
 */
public class AbstractBplusTreeImpl<K extends Comparable<K>, V>
        implements BplusTree<K, V> {

    protected int size;
    protected int nodeWidth;

    protected AbstractBplusTreeNodeImpl<K, V> root;
    protected Set<K> keySet;
    protected Set<Entry<K, V>> entrySet;
    protected Set<V> values;

    @Override
    public int nodeWidth() {
        return nodeWidth;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        AbstractBplusTreeNodeImpl<K, V> currentNode = root;
        while (currentNode.isInternal()) {
            currentNode = currentNode.getPossibleChild((K) key);
        }
        return currentNode.getValue((K) key);
    }


    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entrySet();
    }

    public AbstractBplusTreeImpl(int nodeWidth) {
        this.nodeWidth = nodeWidth;
    }
}

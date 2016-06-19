package com.zjuqsc.database;

/**
 * Created by zhenghu on 2016-06-20.
 */
public abstract class FileBplusTreeNode<K extends Comparable<K>, V extends DatabaseSerializable> extends AbstractBplusTreeNodeImpl<K, V> {


    public FileBplusTreeNode(int width) {
        super(width);
    }

    @Override
    public FileBplusTreeNode<K, V>[] getChildren() {
        return null;
    }

    @Override
    public FileBplusTreeNode<K, V> getNthChild(int n) {
        return null;
    }
}

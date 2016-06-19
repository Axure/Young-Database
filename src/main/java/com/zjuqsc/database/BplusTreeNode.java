package com.zjuqsc.database;

/**
 * Created by zhenghu on 2016-06-19.
 */
public interface BplusTreeNode<T> {
    BplusTreeNode<T> getNthChild(int n);
    BplusTreeNode<T>[] getChildren();
    T getValue();
}

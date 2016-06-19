package com.zjuqsc.database;

/**
 * Created by zhenghu on 2016-06-19.
 */
public class IndexBplusTreeNode implements BplusTreeNode<IndexRecord> {
    protected FilePointer[] childrenLocations;
    protected static final int childrenCount = 500;
    protected IndexBplusTreeNode[] children;
    protected IndexRecord value;

    public IndexBplusTreeNode() {
        children = new IndexBplusTreeNode[childrenCount];
    }

    protected IndexBplusTreeNode(FilePointer filePointer) {

        this();
    }

    /**
     * Load a single children from disk.
     * @param n
     */
    protected void loadNthChildren(int n) {
        if (childrenLocations[n] != null) {
            children[n] = new IndexBplusTreeNode(childrenLocations[n]);
        }
    }

    /**
     * Load all children from disk.
     */
    protected void loadAllChildren() {
        for (int i = 0; i < childrenLocations.length; ++i) {
            loadNthChildren(i);
        }
    }

    /**
     * Get the nth child of this node.
     * @param n
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */
    @Override
    public IndexBplusTreeNode getNthChild(int n) throws ArrayIndexOutOfBoundsException {
        if (childrenLocations[n] != null) {
            loadNthChildren(n);
        } else {
            return null;
        }
        return children[n];
    }

    /**
     * This will load all the children from disk. Maybe very slow.
     * @return
     */
    @Override
    public IndexBplusTreeNode[] getChildren() {
        loadAllChildren();
        return children;
    }

    @Override
    public IndexRecord getValue() {
        return value;
    }
}

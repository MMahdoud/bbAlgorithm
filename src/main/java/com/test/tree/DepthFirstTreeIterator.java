package com.test.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class DepthFirstTreeIterator implements Iterator<MyNode> {
    private LinkedList<MyNode> list;

    public DepthFirstTreeIterator(HashMap<String, MyNode> tree, String identifier) {
        list = new LinkedList<MyNode>();

        if (tree.containsKey(identifier)) {
            this.buildList(tree, identifier);
        }
    }

    private void buildList(HashMap<String, MyNode> tree, String identifier) {
        list.add(tree.get(identifier));
        ArrayList<String> children = tree.get(identifier).getChildren();
        for (String child : children) {

            // Recursive call
            this.buildList(tree, child);
        }
    }

    public boolean hasNext() {
        return !list.isEmpty();
    }

    public MyNode next() {
        return list.poll();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    
}
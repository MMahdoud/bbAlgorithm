package com.test.tree;

import java.util.*;

public class BreadthFirstTreeIterator implements Iterator<MyNode> {

    private static final int ROOT = 0;

    private LinkedList<MyNode> list;
    private HashMap<Integer, ArrayList<String>> levels;

    public BreadthFirstTreeIterator(HashMap<String, MyNode> tree, String identifier) {
        list = new LinkedList<MyNode>();
        levels = new HashMap<Integer, ArrayList<String>>();

        if (tree.containsKey(identifier)) {
            this.buildList(tree, identifier, ROOT);

            for (Map.Entry<Integer, ArrayList<String>> entry : levels.entrySet()) {
                for (String child : entry.getValue()) {
                    list.add(tree.get(child));
                }
            }
        }
    }

    private void buildList(HashMap<String, MyNode> tree, String identifier, int level) {
        if (level == ROOT) {
            list.add(tree.get(identifier));
        }

        ArrayList<String> children = tree.get(identifier).getChildren();

        if (!levels.containsKey(level)) {
            levels.put(level, new ArrayList<String>());
        }
        for (String child : children) {
            levels.get(level).add(child);

            // Recursive call
            this.buildList(tree, child, level + 1);
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
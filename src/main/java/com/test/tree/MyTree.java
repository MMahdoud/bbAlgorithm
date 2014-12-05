package com.test.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MyTree {

    private final static int ROOT = 0;

    private HashMap<String, MyNode> nodes;
    private TraversalStrategy traversalStrategy;

    // Constructors
    public MyTree() {
        this(TraversalStrategy.DEPTH_FIRST);
    }

    public MyTree(TraversalStrategy traversalStrategy) {
        this.nodes = new HashMap<String, MyNode>();
        this.traversalStrategy = traversalStrategy;
    }

    // Properties
    public HashMap<String, MyNode> getNodes() {
        return nodes;
    }

    public TraversalStrategy getTraversalStrategy() {
        return traversalStrategy;
    }

    public void setTraversalStrategy(TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }

    // Public interface
    public MyNode addNode(String identifier) {
        return this.addNode(identifier, null);
    }

    public MyNode addNode(String identifier, String parent) {
        MyNode node = new MyNode(identifier);
        nodes.put(identifier, node);

        if (parent != null) {
            nodes.get(parent).addChild(identifier);
        }

        return node;
    }

    public void display(String identifier) {
        this.display(identifier, ROOT);
    }

    public void display(String identifier, int depth) {
        ArrayList<String> children = nodes.get(identifier).getChildren();

        if (depth == ROOT) {
            System.out.println(nodes.get(identifier).getIdentifier());
        } else {
            String tabs = String.format("%0" + depth + "d", 0).replace("0", "    "); // Four spaces
            System.out.println(tabs + nodes.get(identifier).getIdentifier());
        }
        depth++;
        for (String child : children) {

            // Recursive call
            this.display(child, depth);
        }
    }

    public Iterator<MyNode> iterator(String identifier) {
        return this.iterator(identifier, traversalStrategy);
    }

    public Iterator<MyNode> iterator(String identifier, TraversalStrategy traversalStrategy) {
        if(traversalStrategy == TraversalStrategy.BREADTH_FIRST)
            return new BreadthFirstTreeIterator(nodes, identifier);
        else
            return new DepthFirstTreeIterator(nodes, identifier);
    }
}
package com.test.tree;

import java.util.ArrayList;

public class MyNode {

    private String identifier;
    private ArrayList<String> children;

    // Constructor
    public MyNode(String identifier) {
        this.identifier = identifier;
        children = new ArrayList<String>();
    }

    // Properties
    public String getIdentifier() {
        return identifier;
    }

    public ArrayList<String> getChildren() {
        return children;
    }

    // Public interface
    public void addChild(String identifier) {
        children.add(identifier);
    }
}
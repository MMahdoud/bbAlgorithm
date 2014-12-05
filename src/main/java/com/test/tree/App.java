package com.test.tree;

import java.util.Iterator;

public class App {
    public static void main(String[] args) {

        MyTree myTree = new MyTree();

        /*
         * The second parameter for the addNode method is the identifier
         * for the node's parent. In the case of the root node, either
         * null is provided or no second parameter is provided.
         */
        myTree.addNode("Harry");
        myTree.addNode("Jane", "Harry");
        myTree.addNode("Bill", "Harry");
        myTree.addNode("Joe", "Jane");
        myTree.addNode("Diane", "Jane");
        myTree.addNode("George", "Diane");
        myTree.addNode("Mary", "Diane");
        myTree.addNode("Jill", "George");
        myTree.addNode("Carol", "Jill");
        myTree.addNode("Grace", "Bill");
        myTree.addNode("Mark", "Jane");

        myTree.display("Harry");

        System.out.println("n***** DEPTH-FIRST ITERATION *****");

        // Default traversal strategy is 'depth-first'
        Iterator<MyNode> depthIterator = myTree.iterator("Harry");

        while (depthIterator.hasNext()) {
            MyNode node = depthIterator.next();
            System.out.println(node.getIdentifier());
        }

        System.out.println("n***** BREADTH-FIRST ITERATION *****");

        Iterator<MyNode> breadthIterator = myTree.iterator("Harry", TraversalStrategy.BREADTH_FIRST);

        while (breadthIterator.hasNext()) {
            MyNode node = breadthIterator.next();
            System.out.println(node.getIdentifier());
        }
    }
}
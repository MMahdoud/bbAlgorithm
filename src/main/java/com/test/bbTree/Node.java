package com.test.bbTree;

import com.test.warehouse.CompoundMove;

//import CompounMove.*;

public class Node {
	
	private Node previousNode = null;
	private CompoundMove transition = null;
	private boolean visited = false;
	private int height, ChildId;
	
	public Node(Node previousNode, CompoundMove transition, int height, int id) {
		this.previousNode = previousNode;
		this.transition = transition;
		this.height = height;
		ChildId = id;
	}
	
	public Node() {
		// TODO Auto-generated constructor stub
	}

	public Node getPreviousNode() {
		return previousNode;
	}
	
	public CompoundMove getTransition() {
		return transition;
	}

	public int getNumberOfMoves() {
		// TODO Optimisierung vom Zahlen of Moves
		if (transition != null) return transition.stack.size() + previousNode.getNumberOfMoves();
		else return 0;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean isRoot() {
		if (transition == null) return true;
		else return false;
	}
	
	public int getHeight() {
		return height;
	}

	public int getChildId() {
		return ChildId;
	}

		@Override
	public String toString() {
		StringBuffer retString = new StringBuffer();
		retString.append("height: " + height + ", Child: " + ChildId);
		return retString.toString();
	}
}

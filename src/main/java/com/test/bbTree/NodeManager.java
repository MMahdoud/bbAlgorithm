package com.test.bbTree;
import java.util.ArrayList;
import java.util.List;

import com.test.treeViewer.treeViewer;
import com.test.warehouse.CompoundMove;

public class NodeManager {
	

	private treeViewer<Node> treeViewer = null;
	
	public List<Node> unprocessedNodes = new ArrayList<Node>();
	
	Node rootNode;
	
	private int calls = 0;
	
	
	
	public NodeManager(Node rootNode) {
		this.rootNode = rootNode;
		addNode(rootNode);
	}

	public void addNode(Node node){
		this.unprocessedNodes.add(node);
		if (treeViewer != null) treeViewer.addNode(node, node.getPreviousNode());
	}
	
	public boolean hasNext() {
		return !this.unprocessedNodes.isEmpty();
	}

	public Node getNextNode() {
		Node currentNode =  this.unprocessedNodes.remove(this.unprocessedNodes.size() - 1);
		if (treeViewer != null) treeViewer.setCurrentNode(currentNode);
		return currentNode;

	}

//	
//	
//	public static CompoundMove getPath(Node sourceNode, Node targetNode){
//		CompoundMove path = new CompoundMove();
//		
//		CompoundMove pathFromSourceToRoot = new CompoundMove();
//		Node currentNode = sourceNode;
//		while(currentNode != null){
//			if(currentNode.getTransition() != null){
//				pathFromSourceToRoot.add(currentNode.getTransition().reverse());
//			}
//			currentNode = currentNode.getPreviousNode();
//		}
//		
//		CompoundMove pathFromTargetToRoot = new CompoundMove();
//		currentNode = targetNode;
//		while(currentNode != null){
//			if(currentNode.getTransition() != null){
//				pathFromTargetToRoot.add(currentNode.getTransition().reverse());
//			}
//			currentNode = currentNode.getPreviousNode();
//		}
//		
//		path.add(pathFromSourceToRoot);
//		path.add(pathFromTargetToRoot.reverse());
////	System.out.println("\nPfadl�nge: "+path.size());
//		return path;
//	}
//	
	public CompoundMove getPath(Node sourceNode, Node targetNode){
		if(sourceNode == null || targetNode == null) return null;
		CompoundMove path = new CompoundMove();
		CompoundMove pathFromTargetToRoot = new CompoundMove();
		Node currentNodeSource = sourceNode;
		Node currentNodeTarget = targetNode;
		int pathLength = 0;
		calls++;
		while(!currentNodeSource.equals(currentNodeTarget)){
			int currentNodeSourceHeight = currentNodeSource.getHeight();
			int currentNodeTargetHeight = currentNodeTarget.getHeight();
			
			if(currentNodeSourceHeight >= currentNodeTargetHeight && !currentNodeSource.equals(this.rootNode)){
				path.add(currentNodeSource.getTransition().reverse());
				currentNodeSource = currentNodeSource.getPreviousNode();
				pathLength++;
			}
			if(currentNodeSourceHeight <= currentNodeTargetHeight && !currentNodeTarget.equals(this.rootNode)){
				pathFromTargetToRoot.add(currentNodeTarget.getTransition().reverse());
				currentNodeTarget = currentNodeTarget.getPreviousNode();
				pathLength++;
			}
		}
		
		path.add(pathFromTargetToRoot.reverse());
//		System.out.println("\nPfadl�nge: "+pathLength+", call: "+calls);
		return path;
	}

	public CompoundMove getSolution(Node node) {
		CompoundMove solution = getPath(this.rootNode, node);
		return solution;
	}

	
	
}

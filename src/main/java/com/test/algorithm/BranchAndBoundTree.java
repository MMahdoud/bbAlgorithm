package com.test.algorithm;

import java.util.List;
import java.util.Random;

import com.test.bbTree.*;
import com.test.warehouse.*;

public class BranchAndBoundTree {
	
	private Warehouse warehouse;
	public NodeManager nodeManager;
	private CompoundMove currentBestSolution = null;
	private int height = 0;


	public BranchAndBoundTree(Warehouse warehouse) {
		this.warehouse = warehouse;
		this.nodeManager = new NodeManager(new Node());
	}
	
	public void recursion(){
		/*need a method that checks termination*/
		Node currentNode = null, lastNode = null;
		
		while(this.nodeManager.hasNext()) {
			currentNode = this.nodeManager.getNextNode();
			updateCurrentState(lastNode, currentNode);
			if(worthToExpand(currentNode)){
				boolean foundSolution = !expand(currentNode);
				if(foundSolution){
					updateCurrentBestSolution(currentNode);
				}
			}
			lastNode = currentNode;
		}
	}

	private void updateCurrentState(Node lastNode, Node currentNode) {
		CompoundMove compoundMove = this.nodeManager.getPath(lastNode, currentNode);
//		System.out.println(compoundMove);
//		System.out.println(warehouse);
		applyCompoundMove(compoundMove);
//		System.out.println(warehouse);
	}

	private boolean worthToExpand(Node node) {
		if(this.currentBestSolution == null || getLowerBound(node) < this.currentBestSolution.size())
			return true;
		else {
			return false;
		}
	}

	private long getLowerBound(Node node) {
		long lowerBound = node.getNumberOfMoves() + this.warehouse.getNumberOfSheets();
		return lowerBound;
	}

	private void updateCurrentBestSolution(Node node) {
		if (this.currentBestSolution == null || node.getNumberOfMoves() < this.currentBestSolution.size())  {
			this.currentBestSolution =  this.nodeManager.getSolution(node);
			System.out.println("New Solution: "+this.currentBestSolution.size());
		}
	}

	private boolean expand(Node node) {
//		if (!node.isRoot()){
//			applyCompoundMove(node.getTransition(), warehouse);
//			warehouse.updateMinPrioSheetsQueue();
//		}
		List<Sheet> minPrioSheets = this.warehouse.getMinPrioSheetsQueue();
		if (minPrioSheets.size() > 0) {
			this.height++;
			int i = 0;
			for(Sheet sheet : minPrioSheets){
				CompoundMove transition = getNextCompoundMove(sheet);
				Node newNode = new Node(node, transition, this.height, i);
				this.nodeManager.addNode(newNode);
				i++;
			}
			
			return true;
		}
		else return false;
	}
	
	public CompoundMove getCurrentBestSolution() {
		return this.currentBestSolution;
	}
	
	
	
	public CompoundMove getNextCompoundMove(Sheet sheet) {
		Stack_of_sheets currentStack = sheet.getStack();
		CompoundMove returnCompoundMove = new CompoundMove();
		for (int i = currentStack.sheets.size() - 1; i >= 0; i--) {
			Sheet currentSheet = currentStack.sheets.get(i);
			if (currentSheet != sheet) {
				Stack_of_sheets target_stack = this.warehouse.stacks.get(getOtherIndex(this.warehouse.stacks.indexOf(currentStack)));
				Move move = new Move(currentStack, target_stack, currentSheet);
				returnCompoundMove.add(move);
			} else {
				Move move = new Move(currentStack, null, currentSheet);
				returnCompoundMove.add(move);
				break;
			}
		}
		return returnCompoundMove;
	}
	
	private void applyCompoundMove(CompoundMove cm) {
		if(cm == null) return;
		for(Move move : cm.stack){
			applyMove(move);
		}
		this.warehouse.updateMinPrioSheetsQueue();
	}
	
//	private Warehouse actualizeWarehouse(CompoundMove cm) {
//		if(cm == null) return null;
//		for(Move move : cm.stack){
//			applyMove(move);
//		}
//		this.warehouse.updateMinPrioSheetsQueue();
//		return warehouse;
//	}
	
	private void applyMove(Move move) {
		this.warehouse.move(move);
	}
	
	/**
	 * Gives an other index randomly which different to the given index choice.
	 */
	private int getOtherIndex(int index) {
		
		Random rand = new Random(1);
		int j = rand.nextInt(this.warehouse.stacks.size());
		
		while( j == index) {
			j = rand.nextInt(this.warehouse.stacks.size());
		}
		return j;
	}
}

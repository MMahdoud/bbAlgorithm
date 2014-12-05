
package com.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import com.test.warehouse.*;


/**
 * This class represents implementation of an algorithm based on Branch and Bound method.
 * 
 * @author Mahdoud
 *
 */
public class BranchAndBound {

	public Warehouse warehouse;
	private int currentBestNumberOfMoves = Integer.MAX_VALUE;
	private Solution currentBestSolution = null;
	private int numberOfSolutions;
	private Solution currentSolution;
	
	/**
	 * Constructor of Branch and Bound algorithm
	 * 
	 * @param warehouse the given warehouse
	 */
	public BranchAndBound(Warehouse warehouse) {
		this.warehouse = warehouse;
		this.currentSolution = new Solution();
	}

	/**
	 * This method produce just the given sheet
	 * @param sheet the given sheet
	 */
	public CompoundMove getNextCompoundMove(Sheet sheet) {
		Stack_of_sheets currentStack = sheet.getStack();
		CompoundMove returnCompoundMove = new CompoundMove();
		for (int i = currentStack.sheets.size() - 1; i >= 0; i--) {
			Sheet currentSheet = currentStack.sheets.get(i);
			if (currentSheet != sheet) {
				Stack_of_sheets target_stack = warehouse.stacks.get(getOtherIndex(warehouse.stacks.indexOf(currentStack)));
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
	
	
	
	
	private void stepNext(Sheet topMostSheet) {
		CompoundMove recursionStep = getNextCompoundMove(topMostSheet);
		applyCompoundMove(recursionStep, warehouse);
		currentSolution.add(recursionStep);
	}

	private void stepBack(){
		if(!currentSolution.isEmpty()) {
			CompoundMove cm = currentSolution.pop();
			CompoundMove reversedCompoundMove = cm.reverse();
			applyCompoundMove(reversedCompoundMove, warehouse);
		}
	}
	
	
	private void applyCompoundMove(CompoundMove cm, Warehouse warehouse) {
		for(Move move : cm.stack)
			applyMove(move, warehouse);
	}

	private void applyMove(Move move, Warehouse warehouse) {
		warehouse.move(move);
	}

	/**
	 * Gives an other index randomly which different to the given index choice.
	 */
	private int getOtherIndex(int index) {
		
		Random rand = new Random(1);
		int j = rand.nextInt(warehouse.stacks.size());
		
		while( j == index) {
			j = rand.nextInt(warehouse.stacks.size());
		}
		return j;
	}
	
	/**
	 *This method returns a best choice of target stack during restoring.
	 */
	public Stack_of_sheets getBestChoice(Stack_of_sheets stack, ArrayList<Sheet> list) {
		ArrayList<Stack_of_sheets> goodChoices = (ArrayList<Stack_of_sheets>) warehouse.stacks;
		
		if (!(list.size() == warehouse.getMinPrioSheetsQueue().size())) {
			goodChoices.remove(stack);
			for (Sheet sh : list) {
				goodChoices.remove(sh.getStack()); 
			}
			Random rand = new Random();
			int j = rand.nextInt(warehouse.stacks.size());
			return goodChoices.get(j);
			
		} else {
			
		}

		return null;
	}
	
	/**
	 * Print the history of the execution of restoreAndProduce. 
	 */
	public void printProducesHistory(Stack<CompoundMove> produces){
		for (CompoundMove cm : produces) {
			System.out.println("Size of coumpondMove: " + cm.stack.size());
			for (Move m : cm.stack) {
				if (m.target == null)
					System.out.println("Production of Sh("+ m.movedSheet.getPriority() +") in Stack: " + warehouse.stacks.indexOf(m.source) );
				else
					System.out.println("Sh("+ m.movedSheet.getPriority() +"),"
							+ " (" + warehouse.stacks.indexOf(m.source)
							+ " -> " + warehouse.stacks.indexOf(m.target) + ")");
			}
		}
	}
	
	public void callRecursion(){
		long startTime = System.currentTimeMillis();
		recursion();
		long endTime = System.currentTimeMillis();
		
		System.out.println("Best Solution: "+currentBestNumberOfMoves+", NoSol: "+numberOfSolutions);
		System.out.println("Time: "+(endTime-startTime)/1000 + " sec.");
		System.out.println("NrOfSheets: " + warehouse.getNumberOfSheets());
	}
	
	private void recursion(){
		
		List<Sheet> topMostMinPrioSheets = warehouse.getMinPrioSheetsQueue();
		if(topMostMinPrioSheets.isEmpty()) {
			if(currentSolution.getNumberOfMoves() < currentBestNumberOfMoves){
				currentBestNumberOfMoves = currentSolution.getNumberOfMoves();
				setCurrentBestSolution(currentSolution.clone());
				System.out.println("New Solution: "+currentBestNumberOfMoves);
			}
			numberOfSolutions++;
			return;
		}
		
		for(Sheet topMostSheet : topMostMinPrioSheets){
			stepNext(topMostSheet);
			
			if(getLowerBound() < currentBestNumberOfMoves){
				warehouse.updateMinPrioSheetsQueue();
				recursion();
			}
			
			stepBack();
			warehouse.updateMinPrioSheetsQueue();
		}	
	}

	private int getLowerBound() {
		return currentSolution.getNumberOfMoves() + warehouse.getNumberOfSheets();
	}
	
	public Solution getCurrentBestSolution() {
		return currentBestSolution;
	}
	
	public void setCurrentBestSolution(Solution currentBestSolution) {
		this.currentBestSolution = currentBestSolution;
	}
	
}

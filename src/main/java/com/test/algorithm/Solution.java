package com.test.algorithm;
import java.util.Stack;

import com.test.warehouse.*;


public class Solution {
	
	private Stack<CompoundMove> compoundMoves = new Stack<CompoundMove>();
	private int numberOfMoves;

	public boolean isEmpty() {
		return compoundMoves.isEmpty();
	}

	public CompoundMove pop() {
		numberOfMoves = numberOfMoves - this.compoundMoves.peek().stack.size();
		return this.compoundMoves.pop();	
	}

	public int getNumberOfMoves() {
		return 	numberOfMoves;

	}

	public void add(CompoundMove compoundMove) {
		this.compoundMoves.add(compoundMove);
		numberOfMoves = numberOfMoves + compoundMove.stack.size();
	}
	
	public Solution clone(){
		Solution retSolution = new Solution();
		for(CompoundMove compoundMove : this.compoundMoves)
			retSolution.add(compoundMove);
		return retSolution;
	}

	@Override
	public String toString() {
		StringBuffer retString = new StringBuffer();
		for(CompoundMove compoundMove : compoundMoves){
			retString.append(compoundMove);
		}
		return retString + "\n";
	}
	
	
	

}

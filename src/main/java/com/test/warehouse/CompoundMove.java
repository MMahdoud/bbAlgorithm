package com.test.warehouse;
import java.util.Collection;
import java.util.Stack;

/**
 * This class represents a list of movement of stacks as a history of all movements up to a certain production.
 * 
 * @author Mahdoud
 *
 */

public class CompoundMove {

	/**
	 * All movements before a certain production
	 */
	public Stack<Move> stack = new Stack<Move>();
	
	public CompoundMove() {
		//empty
	}
	
	public CompoundMove reverse() {
		CompoundMove reversedCompoundMove = new CompoundMove();
		for(Move move : this.stack){
			Move reversedMove = move.reverse();
			reversedCompoundMove.stack.add(0, reversedMove);
		}
		return reversedCompoundMove;
	}

	public void add(Move move) {
		this.stack.add(move);
	}
	
	public void add(int pos, Move move) {
		this.stack.add(pos,move);
	}
	
	public void add(CompoundMove compoundMove) {
		if (compoundMove == null) return;
		if (compoundMove.isEmpty()) return;
		this.stack.addAll(compoundMove.getMoves());
	}
	
	public void add(int pos, CompoundMove compoundMove) {
		if (compoundMove == null) return;
		if (compoundMove.isEmpty()) return;
		this.stack.addAll(pos, compoundMove.getMoves());
	}
	
	public CompoundMove clone() {
		CompoundMove retCompoundMove = new CompoundMove();
		retCompoundMove.stack.addAll(this.stack);
		return retCompoundMove;
	}

	public String toString() {
		StringBuffer retString = new StringBuffer();
		for(Move move : this.stack)
			retString.append(move);
		retString.append("\n");
		return retString.toString();
	}

	

	public boolean isEmpty() {
		return this.stack.isEmpty();
	}

	private Collection<? extends Move> getMoves() {
		return this.stack;
	}

	public long size() {
		return this.stack.size();
	}
}

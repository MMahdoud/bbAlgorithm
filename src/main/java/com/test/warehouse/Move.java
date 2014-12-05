package com.test.warehouse;
/**
 * This class represents a movement of a sheet between the source stack and target stack.
 * It comes to a production if the value of target is null, and to a store if the value of source is null.
 * 
 * @author Mahdoud
 *
 */
public class Move {
	public Stack_of_sheets source;
	public Stack_of_sheets target;
	public Sheet movedSheet;
	
	/**
	 * Constructor of the class Move.
	 */
	public Move(Stack_of_sheets source ,Stack_of_sheets target, Sheet movedSheet) {
		this.source = source;
		this.target = target;
		this.movedSheet = movedSheet;
	}
	
	public String toString(){
		StringBuffer retString = new StringBuffer();
		if(this.source == null) retString.append("-");
		else retString.append(this.source.getId());
		retString.append(" -> ");
		if(this.target == null) retString.append("-");
		else retString.append(this.target.getId());
		retString.append(" : "+this.movedSheet + "\n");
		return retString.toString();
	}
	
	
	public Move clone(){
		return new Move(this.source ,this.target, this.movedSheet) ;
	}

	public Move reverse() {
		Move reversedMove = new Move(this.target, this.source, this.movedSheet);
		return reversedMove;
	}
}

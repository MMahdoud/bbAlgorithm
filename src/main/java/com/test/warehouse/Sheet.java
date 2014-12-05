package com.test.warehouse;

/**
 * This class represents a sheet
 * 
 * @author Mahdoud
 * 
 */
public class Sheet implements Comparable<Sheet> {

	/**
	 * The priority of the sheet
	 */
	private int priority;
	
	/**
	 * The Stack where the sheet is on
	 */
	private Stack_of_sheets stack;
	
	/**
	 * The position of the sheet in the stack 
	 */
	private int depth; // it will needed later
	
	/**
	 * Constructor of the class Sheet with the given priority
	 * 
	 * @param priority
	 *            the priority of the sheet
	 */
	public Sheet(int priority) {
		this.setPriority(priority);
	}
	
	/**
	 * Constructor of the class Sheet with the given priority and given stack
	 * 
	 * @param priority the priority of the sheet
	 * @param stack where the sheet will be stored
	 */
	public Sheet(int priority, Stack_of_sheets stack) {
		this.setPriority(priority);
		this.setStack(stack);
	}

	/**
	 * Gets the priority of the sheet.
	 * 
	 * @return priority of the sheet
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Sets the priority of the sheet.
	 * 
	 * @param priority
	 *            the given priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Stack_of_sheets getStack() {
		return this.stack;
	}
	
	public void setStack(Stack_of_sheets stack) {
		this.stack = stack;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return String.valueOf(getPriority());
	}

	public int compareTo(Sheet o) {
		int other_priority = o.getPriority();
		return this.priority - other_priority;
	}
}

package com.test.warehouse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the area where stacks of sheets are stored.
 * 
 * @author Mahdoud
 * 
 */
/**
 * @author Mahdoud
 *
 */
/**
 * @author Mahdoud
 *
 */
public class Warehouse {
	/**
	 * A list of stacks.
	 */
	public List<Stack_of_sheets> stacks;
	
	/**
	 * Set of sheets with the minimal priority in the warehouse. 
	 * It is regarded in each stack, if there is a sheet with the minimal priority. 
	 * 
	 *  */
	private List<Sheet> minPrioSheetsQueue = new ArrayList<Sheet>();

	/**
	 * The number of stored sheets in the warehouse.
	 */
	private int numberOfSheets;

	

	

	/**
	 * Constructor of the class Warehouse.
	 */
	public Warehouse() {
		this.stacks = new ArrayList<Stack_of_sheets>();
	}

	/**
	 * Adds a new stack in the warehouse.
	 */
	public void addStack(int id) {
		this.stacks.add(new Stack_of_sheets(id));
	}

	/**
	 * Gets the number of the stacks in the warehouse.
	 * 
	 * @return the number of stacks in the warehouse.
	 */
	public int getNumStacks() {
		return this.stacks.size();
	}

	/**
	 * Finds the sheet with most minimal priority in the Warehouse.
	 * 
	 * @return sheet with minimal priority.
	 */
	public Sheet getMinPrioSheet() {
		
		if (!isEmpty()) {
			Sheet minPrioSheet = new Sheet(Integer.MAX_VALUE);
			for (Stack_of_sheets st : this.stacks) {
			
				if (!st.isEmpty() && st.getMinPrioSheet().getPriority() < minPrioSheet.getPriority()) {
					minPrioSheet = st.getMinPrioSheet();
				}
			}
			return minPrioSheet;
		}
		return null;
	}
	
	/**
	 * This method updates the minPrioSheetsQueue.
	 */
	public void updateMinPrioSheetsQueue() {
		this.minPrioSheetsQueue.clear();
		Sheet minPrioSheet = getMinPrioSheet();
		if(minPrioSheet==null) return;
		int minPrio = minPrioSheet.getPriority();
		for (Stack_of_sheets stack : this.stacks) {
			if (!stack.isEmpty()) {
				int s = stack.sheets.size() - 1;
				for (int i = s ; i >= 0; i--) {
					Sheet sh = stack.sheets.get(i);
					if (sh.getPriority() == minPrio) {
						this.minPrioSheetsQueue.add(sh);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Checks if the warehouse is empty.
	 * 
	 * @return true if the the warehouse is empty.
	 */
	public boolean isEmpty() {
		boolean bool = true;
		for (Stack_of_sheets st: this.stacks) {
			if (!st.isEmpty()) {
				bool = false;
				break;
			}
		}
		return bool;
	}
	
	
	/**
	 * To string method.
	 */
	@Override
	public String toString() {
		String retString = "";
		Iterator<Stack_of_sheets> iterator = this.stacks.iterator();
		while (iterator.hasNext()) {
			
			Stack_of_sheets s = iterator.next();
			retString = retString + "\n" + this.stacks.indexOf(s) + ": " +  s;
		}
		return retString;
	}
	
	/**
	 * Gets the minPriosheetsQueue.
	 * @return a new copy of the list of minPriosheetsQueue.
	 */
	public List<Sheet> getMinPrioSheetsQueue() {
		List<Sheet>  retList = new ArrayList<Sheet>();
		retList.addAll(this.minPrioSheetsQueue);
		return retList;
	}
	
	/**
	 * Gets the number of sheets in the warehouse.
	 * @return number of sheets in the warehouse.
	 */
	public int getNumberOfSheets() {
		return this.numberOfSheets;
	}

	/**
	 * 
	 * @param randStack
	 * @param sheet
	 */
	public void addSheet(int randStack, Sheet sheet) {
		Stack_of_sheets stack = this.stacks.get(randStack);
		move(null, stack, sheet);
		}

	/**
	 * Moves a sheet from stack to another, store a given sheet in the given stack
	 * or produce the sheet at the top of the given stack.
	 * If the source_stack is null that means this operation is a storing.
	 * If the target stack is null that means this operation is a production.
	 * Otherwise this operation is a restoring
	 * 
	 * @param source_stack the source stack
	 * @param target_stack the target stack
	 * @param sheet the moving sheet
	 */
	public void move(Stack_of_sheets source_stack, Stack_of_sheets target_stack, Sheet sheet) {
		//store the given sheet
		if (source_stack == null && target_stack != null) {
			sheet.setStack(target_stack);
			target_stack.sheets.push(sheet);
			this.numberOfSheets++;
		//produce a sheet
		} else if (source_stack != null && target_stack == null) {
			this.numberOfSheets--;
			source_stack.sheets.pop();
		}
		//move sheet from a stack to another
		else if (source_stack != null && target_stack != null) {
			source_stack.sheets.peek().setStack(target_stack);
			target_stack.sheets.push(source_stack.sheets.pop());
		} 
	}

	public void move(Move move) {
		move(move.source, move.target, move.movedSheet);
	}
	
}

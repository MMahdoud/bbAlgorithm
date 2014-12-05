package com.test.warehouse;
import java.util.Iterator;
import java.util.Stack;

/**
 * This class represents a stack of sheets
 * 
 * @author Mahdoud
 * 
 */
public class Stack_of_sheets {
	
	/**
	 * A stack of sheets
	 */
	public Stack<Sheet> sheets;
	private int id;

	/**
	 * Constructor of the class Stack
	 */
	public Stack_of_sheets(int id) {
		sheets = new Stack<Sheet>();
		this.id = id;
	}

	/**
	 * Adds a sheet with the given priority
	 * 
	 * @param number_of_sheets
	 *            the given number of sheets
	 * @param sheet
	 *            the priority of a sheet
	 */
	public void addSheet(Sheet sheet) {
		sheet.setStack(this);
		sheets.push(sheet);
	}
	
	/**
	 * Finds the sheet with most minimal priority in the stack
	 * 
	 * @return sheet with minimal priority
	 */
	public Sheet getMinPrioSheet() {
		try {
			if (!isEmpty()) {
				if (sheets.peek().getPriority() == 1)
					return sheets.peek();
				
				Sheet minPrioSheet = sheets.peek();
				for (int i = sheets.size() - 1; i >= 0; i--) {
					Sheet sh = sheets.get(i);
					if(sh.getPriority() == 1) 
						return sheets.get(i);
				if (sh.getPriority() < minPrioSheet.getPriority())
					minPrioSheet = sh;
				}
				return minPrioSheet;
			}
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * Checks if the stack is empty
	 * 
	 * @return true if the stack is empty
	 */
	public boolean isEmpty() {
		if( sheets.size() == 0) {
			return true;
		}
		return false;
	}

	public int getId() {
		return this.id;
	}

	/**
	 * To string method
	 */
	@Override
	public String toString() {
		String retString = "";
		Iterator<Sheet> iterator = sheets.iterator();
		while (iterator.hasNext()) {
			retString = retString + " " + iterator.next().toString();
		}
		return retString;
	}

	/**
	 * Hash code method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sheets == null) ? 0 : sheets.hashCode());
		return result;
	}
}

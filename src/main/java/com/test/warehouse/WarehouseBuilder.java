package com.test.warehouse;
import java.util.Random;

public class WarehouseBuilder {

	/**
	 * Generates a random warehouse with the given properties.
	 * 
	 * @param numStacks
	 *            the number of stacks that will be created
	 * @param numSheets
	 *            the number of stacks that will be created
	 * @param maxPrio
	 *            the maximal priority that can have a sheet (1,...,maxPrio)
	 * @return random warehouse
	 * @throws Exception
	 */
	public Warehouse getRandWarehouse(int numStacks, int numSheets, int maxPrio)
			throws Exception {

		Random rand = new Random(1);
		Warehouse my_warehouse = new Warehouse();

		for (int i = 0; i < numStacks; i++) {
			my_warehouse.addStack(i);
		}

		for (int i = 0; i < numSheets; i++) {
			int randStack = rand.nextInt(my_warehouse.getNumStacks());
			my_warehouse.addSheet(randStack, new Sheet(
					rand.nextInt(maxPrio) + 1));
		}
		
			my_warehouse.updateMinPrioSheetsQueue();

		return my_warehouse;
	}

}

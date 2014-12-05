package com.test.algorithm;

import java.util.List;

import com.test.bbTree.Node;
import com.test.tree.*;
import com.test.warehouse.*;

public class myTreeOfBB {
	
	public MyTree myTree;;
	private Warehouse warehouse;
	private Integer currentBestSolution = Integer.MAX_VALUE;
	
	private boolean expand(Node currentNode) {
		List<Sheet> minPrioSheets = warehouse.getMinPrioSheetsQueue();
		for(Sheet sheet : minPrioSheets){
			CompoundMove transition = getNextCompoundMove(sheet);
		//	myTree.addNode();
			applyCompoundMove(transition.reverse(), warehouse);
		}
		System.err.println(minPrioSheets.size());
		if (minPrioSheets.size() > 0) return true;
		else return false;
	}

	private void applyCompoundMove(CompoundMove reverse, Warehouse warehouse2) {
		// TODO Auto-generated method stub
		
	}

	private CompoundMove getNextCompoundMove(Sheet sheet) {
		// TODO Auto-generated method stub
		return null;
	}
}

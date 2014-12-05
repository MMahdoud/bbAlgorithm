package com.test.main;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.test.warehouse.*;
import com.test.algorithm.*;
import com.test.bbTree.Node;
import com.test.bbTree.NodeManager;
import com.test.model.*;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

/**
 * Hello world!
 * 
 */
public class App {

	public static int edgeCount = 0;

	public static void main(String[] args) {
		
		WarehouseBuilder wareHouseBuilder = new WarehouseBuilder();
		try {
			BranchAndBoundTree bbTree = new BranchAndBoundTree(wareHouseBuilder.getRandWarehouse(2, 4, 2));
			System.out.println(bbTree.nodeManager.unprocessedNodes);
			bbTree.recursion();
		} catch (Exception e1) {
			System.out.println("Exception...");
		}
		

//		// Instancing of a warehouse
//		// Warehouse my_warehouse = wareHouseBuilder.getRandWarehouse(5, 30, 5);
//		Warehouse my_warehouse;
//		try {
//			my_warehouse = wareHouseBuilder.getRandWarehouse(2, 4, 2);
//			// testing of Branch and Bound
//			BranchAndBound bb = new BranchAndBound(my_warehouse);
//			
//			// testing of Branch and Bound Tree
//			BranchAndBoundTree bb_Tree = new BranchAndBoundTree(my_warehouse);
//			System.out
//			.println("------------------- The initial warehouse -------------------");
//			System.out.println(my_warehouse);
//			System.out
//			.println("-------------------------------------------------------------");
//			// bb.callRecursion();
//			// System.out.println(bb.getCurrentBestSolution());
//			long startTime = System.currentTimeMillis();
//			bb_Tree.recursion();
//			long endTime = System.currentTimeMillis();
//			
//			JFrame frame = new JFrame("Simple Graph View");
//			
//			System.out.println("Time: " + (endTime - startTime) + " millis");
//			
//			System.out.println("\nCurrentBest: nMoves "
//					+ bb_Tree.getCurrentBestSolution().size() + "\n"
//					+ bb_Tree.getCurrentBestSolution());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	private static void visualizeTestGraph() {
		Graph<MyNode, MyLink> g = new SparseMultigraph<MyNode, MyLink>();
		g = new DirectedSparseMultigraph<MyNode, MyLink>();
		// Create some MyNode objects to use as vertices
		MyNode n1 = new MyNode(1), n2 = new MyNode(2), n3 = new MyNode(3), n4 = new MyNode(4), n5 = new MyNode(5); // note n1-n5 declared elsewhere.
		// Add some directed edges along with the vertices to the graph
		g.addEdge(new MyLink(48),n1, n2, EdgeType.DIRECTED); // This method
		g.addEdge(new MyLink(48),n2, n3, EdgeType.DIRECTED);
		g.addEdge(new MyLink(192), n3, n5, EdgeType.DIRECTED);
		g.addEdge(new MyLink(48), n5, n4, EdgeType.DIRECTED); // or we can use
		g.addEdge(new MyLink(48), n4, n2); // In a directed graph the
		g.addEdge(new MyLink(48), n3, n1); // first node is the source
		g.addEdge(new MyLink(48), n2, n5);// and the second the destination
		System.out.println(g.toString());
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Layout<Integer, String> layout = new CircleLayout(g);
		layout.setSize(new Dimension(500,500)); // sets the initial size of the space
		 // The BasicVisualizationServer<V,E> is parameterized by the edge types
		BasicVisualizationServer<Integer,String> vv =
		new BasicVisualizationServer<Integer,String>(layout);
		vv.setPreferredSize(new Dimension(600,600)); //Sets the viewing area size
		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

}
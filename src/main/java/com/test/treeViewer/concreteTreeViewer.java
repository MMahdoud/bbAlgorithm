package com.test.treeViewer;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

import com.test.model.MyLink;
import com.test.model.MyNode;
import com.test.warehouse.CompoundMove;
import com.test.warehouse.Warehouse;
import com.test.bbTree.Node;

public class concreteTreeViewer<V, E> implements treeViewer<V>{

	private DirectedSparseGraph<V,E> tree;
	private Warehouse warehouse;
	
	
	public concreteTreeViewer() {
		
		tree = new DirectedSparseGraph<V, E>();
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Layout<Integer, String> layout = new CircleLayout(tree);
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
		
		//DirectedSparseGraph
		// Jung kram aufbauen
		// Jpanel aufbauen 
		
//		 JFrame frame = new JFrame("Simple Graph View");
//		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		 frame.getContentPane().add(vv);
//		 frame.pack();
//		 frame.setVisible(true); 
		
//		public DirectedSparseGraph<Node, CompoundMove> bbTreeGraph;
//		// The Layout<V, E> is parameterized by the vertex and edge types
//		Layout<Node, CompoundMove> layout = new CircleLayout(bbTreeGraph);
//		layout.setSize(new Dimension(300,300)); // sets the initial size of the layout space
//		// The BasicVisualizationServer<V,E> is parameterized by the vertex and edge types
//		BasicVisualizationServer<Node, CompoundMove> vv = new BasicVisualizationServer<Node, CompoundMove>(layout);
//		vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
	}
	
	public void addNode(V node, V parent) {
		tree.addVertex(node);
		//TODO Null?
		tree.addEdge(null, parent, node);
	}

	public void setCurrentNode(V node) {
		// TODO Auto-generated method stub
	}
	
	

}



package com.test.treeViewer;

public interface treeViewer<V> {
	public void addNode(V node, V parent); //F�gt neuen Knoten zum BAum
	public void setCurrentNode(V node); // F�rbt den CurrentNOde Gr�n
}

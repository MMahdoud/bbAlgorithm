package com.test.model;

import com.test.main.App;

public class MyLink {
	double capacity; // should be private
	int id;

	public MyLink(double capacity) {
		this.id = App.edgeCount++; // This is defined in the outer class.
		this.capacity = capacity;
	}

	public String toString() { // Always good for debugging
		return "E" + id;
	}
}

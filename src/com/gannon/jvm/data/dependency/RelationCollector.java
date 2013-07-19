package com.gannon.jvm.data.dependency;

import java.util.ArrayList;
import java.util.Stack;


public class RelationCollector {
	// This list will have the tree objects created for dependency
	private ArrayList<Relation> listOfTrees = new ArrayList<Relation>();
	// This stack works same as operand Stack.
	private Stack<String> tempVarialbeStack = new Stack<String>();

	public RelationCollector() {
		super();
	}

	public ArrayList<Relation> getListOfTrees() {
		return listOfTrees;
	}

	public void setListOfTrees(ArrayList<Relation> listOfTrees) {
		this.listOfTrees = listOfTrees;
	}

	public Stack<String> getTempVariableStack() {
		return tempVarialbeStack;
	}

	public void setTempVariableStack(Stack<String> tempVarialbeStack) {
		this.tempVarialbeStack = tempVarialbeStack;
	}
}

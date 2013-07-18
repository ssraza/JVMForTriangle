package com.gannon.jvm.data.dependency;

import java.util.ArrayList;
import java.util.Stack;

public class DependencyDataHolder {
	// This list will have the tree objects created for dependency
	private ArrayList<BinTree> listOfTrees = new ArrayList<BinTree>();
	// This stack works same as operand Stack.
	private Stack<String> tempVarialbeStack = new Stack<String>();

	public DependencyDataHolder() {
		super();
	}

	public ArrayList<BinTree> getListOfTrees() {
		return listOfTrees;
	}

	public void setListOfTrees(ArrayList<BinTree> listOfTrees) {
		this.listOfTrees = listOfTrees;
	}

	public Stack<String> getTempVarialbeStack() {
		return tempVarialbeStack;
	}

	public void setTempVarialbeStack(Stack<String> tempVarialbeStack) {
		this.tempVarialbeStack = tempVarialbeStack;
	}
}

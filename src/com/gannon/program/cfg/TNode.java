package com.gannon.program.cfg;

import java.util.ArrayList;

/**
 * 
 * @author Bader
 */
public class TNode {
	private String name; // node name

	private ArrayList<String> setOfDominatorNodes;
	private boolean isSetOfDominatorNodesAdded = false;

	public TNode(String name) {
		this.name = name;
		setOfDominatorNodes = new ArrayList<String>();
	}

	public ArrayList<String> getSetOfDominatorNodes() {
		return setOfDominatorNodes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIsSetOfDominatorNodesAdded(boolean t) {
		isSetOfDominatorNodesAdded = t;
	}

	public boolean getIsSetOfDominatorNodesAdded() {
		return isSetOfDominatorNodesAdded;
	}

}

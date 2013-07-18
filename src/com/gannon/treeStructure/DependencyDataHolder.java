package com.gannon.treeStructure;

import java.util.ArrayList;
import java.util.HashMap;

public class DependencyDataHolder {
	// This list consists of root node name for trees like I1,I2,I3
	private ArrayList<String> rootNameList = new ArrayList<String>();
	// This list will have the tree objects created for dependency
	private ArrayList<Tree> listOfTrees = new ArrayList<Tree>();
	// This counts the number of root node variables
	private int tempVarCounter;
	// This counts the nodes, use to mark node by ID
	private int nodeIdCount;
	// This mapping is used to map rootname with the instruction index/pc.
	private HashMap<String, Integer> rootNamePCMapping = new HashMap<String, Integer>();
	
	public DependencyDataHolder(int tempVarCounter, int nodeId) {
		super();
		this.tempVarCounter = tempVarCounter;
		this.nodeIdCount = nodeId;
	}
	public ArrayList<String> getRootNameList() {
		return rootNameList;
	}
	public void addRootName(String rootName) {
		this.rootNameList.add(rootName);
	}
	public ArrayList<Tree> getListOfTrees() {
		return listOfTrees;
	}
	public void addTrees(Tree tree) {
		this.listOfTrees.add(tree);
	}
	public int getTempVarCounter() {
		return tempVarCounter;
	}
	public int getNodeIdCount() {
		return nodeIdCount;
	}
	public void setNodeIdCount(int nodeIdCount) {
		this.nodeIdCount = nodeIdCount;
	}
	public void setTempVarCounter(int tempVarCounter) {
		this.tempVarCounter = tempVarCounter;
	}
	public HashMap<String, Integer> getRootNamePCMapping() {
		return rootNamePCMapping;
	}
	public void setRootNamePCMapping(String rootName, Integer PC) {
		this.rootNamePCMapping.put(rootName, PC);
	}
	
	
	
}

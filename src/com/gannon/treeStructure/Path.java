package com.gannon.treeStructure;

import java.util.ArrayList;

public class Path {
	private int pathId;	
	private ArrayList<Node> nodes = new ArrayList<Node>();;

	
	public Path() {
		super();
	}

	public Path(int pathId) {
		super();
		this.pathId = pathId;
	}

	public int getPathId() {
		return pathId;
	}

	public void setPathId(int pathId) {
		this.pathId = pathId;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setListofNodes(ArrayList<Node> listofNodes) {
		this.nodes = listofNodes;
	}
	
	public void add(Node node){
		this.nodes.add(node);
	}
}

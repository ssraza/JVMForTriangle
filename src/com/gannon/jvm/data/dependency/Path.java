package com.gannon.jvm.data.dependency;

import java.util.ArrayList;

public class Path {
	private int pathId;
	private ArrayList<PNode> nodes = new ArrayList<PNode>();
	private ArrayList<Object> inputs = new ArrayList<Object>();

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

	public ArrayList<PNode> getNodes() {
		return nodes;
	}

	public void setListofNodes(ArrayList<PNode> listofNodes) {
		this.nodes = listofNodes;
	}

	public void add(PNode node){
		this.nodes.add(node);
	}

	public ArrayList<Object> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<Object> inputs) {
		this.inputs = inputs;
	}

	public ArrayList<Integer> getExecutedInsIDs() {
		ArrayList<Integer> ids=new ArrayList<Integer>();
		for(PNode node: nodes){
			ids.add(node.getInstructionLineNumber());
		}
		return ids;
	}
}

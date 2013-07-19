package com.gannon.jvm.progam.path;

import java.util.ArrayList;

public class Path {
	private int pathId;
	private ArrayList<Node> nodes = new ArrayList<Node>();
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

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setListofNodes(ArrayList<Node> listofNodes) {
		this.nodes = listofNodes;
	}

	public void add(Node node){
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
		for(Node node: nodes){
			ids.add(node.getInstructionLineNumber());
		}
		return ids;
	}
}

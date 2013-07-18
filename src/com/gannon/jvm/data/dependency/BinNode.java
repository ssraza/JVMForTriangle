package com.gannon.jvm.data.dependency;

//binary tree node
public class BinNode {
	private String id;// use id as temp variable names
	private BinNode leftBNode, rightBNode; // the nodes
	private String arithmaticOperation;

	public BinNode(String id) {
		super();
		this.id = id;
		this.leftBNode = null;
		this.rightBNode = null;
	}

	public String getId() {
		return id;
	}

	public BinNode getLeftBNode() {
		return leftBNode;
	}

	public void setLeftBNode(BinNode leftBNode) {
		this.leftBNode = leftBNode;
	}

	public BinNode getRightBNode() {
		return rightBNode;
	}

	public void setRightBNode(BinNode rightBNode) {
		this.rightBNode = rightBNode;
	}

	public String getArithmaticOperation() {
		return arithmaticOperation;
	}

	public void setArithmaticOperation(String arithmaticOperation) {
		this.arithmaticOperation = arithmaticOperation;
	}

	public void show() {
		// calls the show method of the AnyClass
		System.out.print("i"+id+"\n");
	}

}

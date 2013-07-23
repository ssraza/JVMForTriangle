package com.gannon.jvm.data.dependency;

import com.gannon.jvm.utilities.Utility;

//binary tree node
public class BinNode {
	private String localVariableName;
	private BinNode leftBNode, rightBNode;

	public BinNode(String id) {
		super();
		this.localVariableName = id;
		this.leftBNode = null;
		this.rightBNode = null;
	}

	public String getLocalVariableName() {
		return localVariableName;
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

	public void show() {
		// calls the show method of the AnyClass
		System.out.print("i" + localVariableName + "\n");
	}

	public void showIndent(String indent) {
		System.out.print(indent + "i" + localVariableName + "\n");
	}

	public boolean equals(Object node) {
		return (node instanceof BinNode) && localVariableName.equals(((BinNode) node).localVariableName);
	}

	public int hashCode() {
		return localVariableName.hashCode();
	}

	public boolean isParamter() {
		return Integer.parseInt(localVariableName) < Utility.MAX_PARAMETER_ID_ALLOWED;
	}

}

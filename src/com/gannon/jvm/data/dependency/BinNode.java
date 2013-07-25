package com.gannon.jvm.data.dependency;

import com.gannon.jvm.utilities.OpcodeUtility;

//binary tree node
public class BinNode {
	private String localVariableName;
	private BinNode leftBNode, rightBNode;

	public BinNode(String localVariableName) {
		super();
		this.localVariableName = localVariableName;
		this.leftBNode = null;
		this.rightBNode = null;
	}
	
	public void setLocalVariableName(String localVariableName) {
		this.localVariableName = localVariableName;
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

	public boolean equals(Object obj) {
		return (obj instanceof BinNode) && localVariableName.equals(((BinNode) obj).localVariableName);
	}

	public int hashCode() {
		return localVariableName.hashCode();
	}

	public boolean isParamter() {
		return Integer.parseInt(localVariableName) <OpcodeUtility.MAX_PARAMETER_ID_ALLOWED;
	}
	
	public void show() {
		// calls the show method of the AnyClass
		System.out.print("i" + localVariableName + "\n");
	}

	public void showIndent(String indent) {
		System.out.print(indent + "i" + localVariableName + "\n");
	}
	
	public String showIndentString(String indent) {
		return indent + "i" + localVariableName + "\n";
	}

}

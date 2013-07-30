package com.gannon.jvm.data.dependency;

import com.gannon.jvm.utilities.OpcodeUtility;

//binary tree node
public class BinNode {
	private String localVariableName;
	private Object localVariableValue;
	private BinNode leftBNode, rightBNode;

	public BinNode(String localVariableName) {
		super();
		this.localVariableName = localVariableName;
		this.leftBNode = null;
		this.rightBNode = null;
	}

	public BinNode(String localVariableName, Object localVariableValue) {
		super();
		this.localVariableName = localVariableName;
		this.localVariableValue = localVariableValue;
		this.leftBNode = null;
		this.rightBNode = null;
	}

	public void setVariableName(String localVariableName) {
		this.localVariableName = localVariableName;
	}

	public String getVariableName() {
		return localVariableName;
	}

	public Object getVariableValue() {
		return localVariableValue;
	}

	public void setVariableValue(Object localVariableValue) {
		this.localVariableValue = localVariableValue;
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
		return (obj instanceof BinNode)
				&& localVariableName.equals(((BinNode) obj).localVariableName);
	}

	public int hashCode() {
		return localVariableName.hashCode();
	}

	public boolean isParamter() {
		return Integer.parseInt(localVariableName) < OpcodeUtility.MAX_PARAMETER_ID_ALLOWED;
	}

	public void show() {
		// calls the show method of the AnyClass
		System.out.print("i" + localVariableName + "\n");
	}

	public void showIndent(String indent) {
		System.out.print(indent + "i" + localVariableName + "\n");
	}

	public String showIndentString(String indent) {
		return indent + "i" + localVariableName + " value="+ localVariableValue+"\n";
	}

}

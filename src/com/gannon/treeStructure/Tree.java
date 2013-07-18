package com.gannon.treeStructure;

public class Tree {
	private Node rootNode;
	private Node leftLeaf;
	private Node rightLeaf;
	private Object ArithmaticOperation;

	public Tree(Node rootNode, Node leftLeaf, Node rightLeaf,
			Object arithmaticOperation) {
		super();
		this.rootNode = rootNode;
		this.leftLeaf = leftLeaf;
		this.rightLeaf = rightLeaf;
		ArithmaticOperation = arithmaticOperation;
	}
	public Node getRootNode() {
		return rootNode;
	}
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}
	public Node getLeftLeaf() {
		return leftLeaf;
	}
	public void setLeftLeaf(Node leftLeaf) {
		this.leftLeaf = leftLeaf;
	}
	public Node getRightLeaf() {
		return rightLeaf;
	}
	public void setRightLeaf(Node rightLeaf) {
		this.rightLeaf = rightLeaf;
	}
	public Object getArithmaticOperation() {
		return ArithmaticOperation;
	}
	public void setArithmaticOperation(Object arithmaticOperation) {
		ArithmaticOperation = arithmaticOperation;
	}


}

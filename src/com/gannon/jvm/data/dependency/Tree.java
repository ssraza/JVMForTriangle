package com.gannon.jvm.data.dependency;

public class Tree {
	private PNode rootNode;
	private PNode leftLeaf;
	private PNode rightLeaf;
	private Object ArithmaticOperation;

	public Tree(PNode rootNode, PNode leftLeaf, PNode rightLeaf,
			Object arithmaticOperation) {
		super();
		this.rootNode = rootNode;
		this.leftLeaf = leftLeaf;
		this.rightLeaf = rightLeaf;
		ArithmaticOperation = arithmaticOperation;
	}
	public PNode getRootNode() {
		return rootNode;
	}
	public void setRootNode(PNode rootNode) {
		this.rootNode = rootNode;
	}
	public PNode getLeftLeaf() {
		return leftLeaf;
	}
	public void setLeftLeaf(PNode leftLeaf) {
		this.leftLeaf = leftLeaf;
	}
	public PNode getRightLeaf() {
		return rightLeaf;
	}
	public void setRightLeaf(PNode rightLeaf) {
		this.rightLeaf = rightLeaf;
	}
	public Object getArithmaticOperation() {
		return ArithmaticOperation;
	}
	public void setArithmaticOperation(Object arithmaticOperation) {
		ArithmaticOperation = arithmaticOperation;
	}


}

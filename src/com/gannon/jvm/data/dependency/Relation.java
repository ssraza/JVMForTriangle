package com.gannon.jvm.data.dependency;

//i1>i2 or i1+i2=i3
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.utilities.Utility;

public class Relation {
	private BinNode theBTRootNode;
	private BInstruction inst;

	public Relation(BinNode theBTRootNode, BInstruction inst) {
		super();
		this.theBTRootNode = theBTRootNode;
		this.inst = inst;
	}

	public BinNode insertToLeft(BinNode myLeftNewNode) {
		if (theBTRootNode != null) {
			theBTRootNode.setLeftBNode(myLeftNewNode);
		}
		return theBTRootNode;
	}

	public BinNode insertToRight(BinNode myRightNewNode) {
		if (theBTRootNode != null) {
			theBTRootNode.setRightBNode(myRightNewNode);
		}
		return theBTRootNode;
	}
	
	public BinNode getLeftNode(){
		if (theBTRootNode != null) {
			return theBTRootNode.getLeftBNode();
		}
		return null;
	}
	
	public BinNode getRightNode(){
		if (theBTRootNode != null) {
			return theBTRootNode.getRightBNode();
		}
		return null;
	}
	
	public void join(Relation aNewRelation){
		if(this!=null && aNewRelation!=null){
			if(this.getLeftNode().getLeftBNode()==null && this.getLeftNode().equals(aNewRelation) ){
				this.insertToLeft(aNewRelation.getTheBTRootNode());
			}
			if(this.getRightNode().getRightBNode()==null && this.getRightNode().equals(aNewRelation) ){
				this.insertToRight(aNewRelation.getTheBTRootNode());
			}
	
		}
	}
	

	// ------------------ InOrder traversal-------------------
	protected void inorder(BinNode theRootNode) {
		if (theRootNode != null) {
			inorder(theRootNode.getLeftBNode());
			theRootNode.show();
			inorder(theRootNode.getRightBNode());
		}
	}

	// calls the method to do in order
	public void inorderBST() {
		inorder(theBTRootNode);
	}

	public BinNode getTheBTRootNode() {
		return theBTRootNode;
	}

	public BinNode search(BinNode keyNode) {
		// if the root is null returns null
		if (theBTRootNode == null || keyNode == null) {
			return null;
		} else {
			String varId = keyNode.getId();
			// checks if they are equal
			if (varId.equals(theBTRootNode.getId())) {
				return theBTRootNode;
			} else if (varId.equals(theBTRootNode.getLeftBNode().getId())) {
				return theBTRootNode.getLeftBNode();
			} else if (varId.equals(theBTRootNode.getRightBNode().getId())) {
				return theBTRootNode.getRightBNode();
			} else {
				return null;
			}
		}
	}

	public BinNode search(String varId) {
		// if the root is null returns null
		if (theBTRootNode == null) {
			return null;
		} else {
			// checks if they are equal
			if (varId.equals(theBTRootNode.getId())) {
				return theBTRootNode;
			} else if (varId.equals(theBTRootNode.getLeftBNode().getId())) {
				return theBTRootNode.getLeftBNode();
			} else if (varId.equals(theBTRootNode.getRightBNode().getId())) {
				return theBTRootNode.getRightBNode();
			} else {
				return null;
			}
		}
	}

	public BinNode searchLeafNode(String varId) {
		// if the root is null returns null
		if (theBTRootNode == null) {
			return null;
		} else {
			if (varId.equals(theBTRootNode.getLeftBNode().getId())) {
				return theBTRootNode.getLeftBNode();
			} else if (varId.equals(theBTRootNode.getRightBNode().getId())) {
				return theBTRootNode.getRightBNode();
			} else {
				return null;
			}
		}
	}

	// check if the children of the relation are parameters
	public boolean isEndRelation() {
		return Integer.parseInt(theBTRootNode.getLeftBNode().getId()) < Utility.MAX_PARAMETER_ID_ALLOWED
				&& Integer.parseInt(theBTRootNode.getRightBNode().getId()) < Utility.MAX_PARAMETER_ID_ALLOWED;
	}

	public BInstruction getInst() {
		return inst;
	}

	public void setInst(BInstruction inst) {
		this.inst = inst;
	}

	public boolean equals(Object relation) {
		return relation instanceof Relation
				&& theBTRootNode.equals(((Relation) relation)
						.getTheBTRootNode())
				&& theBTRootNode.getLeftBNode().equals(
						((Relation) relation).getTheBTRootNode()
								.getLeftBNode())
				&& theBTRootNode.getRightBNode().equals(
						((Relation) relation).getTheBTRootNode()
								.getRightBNode());
	}

	public int hashCode() {
		return theBTRootNode.hashCode();
	}
}

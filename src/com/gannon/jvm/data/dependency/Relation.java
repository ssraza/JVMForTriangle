package com.gannon.jvm.data.dependency;

//i1>i2 or i1+i2=i3
import java.util.ArrayList;
import java.util.Arrays;

import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.utilities.Utility;

public class Relation {
	private BinNode theBTRootNode;
	private BInstruction inst;

	public Relation(BinNode theBTRootNode) {
		super();
		this.theBTRootNode = theBTRootNode;
	}

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

	public BinNode getLeftNode() {
		if (theBTRootNode != null) {
			return theBTRootNode.getLeftBNode();
		}
		return null;
	}

	public BinNode getRightNode() {
		if (theBTRootNode != null) {
			return theBTRootNode.getRightBNode();
		}
		return null;
	}

	public Relation join(Relation aNewRelation) {
		if (this == null) {
			return aNewRelation;
		}
		if (this != null && aNewRelation != null) {
			if (this.getLeftNode() != null && this.getLeftNode().equals(aNewRelation.getTheBTRootNode())) {
				this.insertToLeft(aNewRelation.getTheBTRootNode());
			}
			if (this.getRightNode() != null && this.getRightNode().equals(aNewRelation.getTheBTRootNode())) {
				this.insertToRight(aNewRelation.getTheBTRootNode());
			}
		}
		return this;
	}

	public boolean isPredicateRelation() {
		return (inst instanceof BPredicateInstruction);
	}

	public boolean isParamterRelation() {
		return Integer.parseInt(theBTRootNode.getLocalVariableName()) < Utility.MAX_PARAMETER_ID_ALLOWED;
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

	public void inorderRoot(BinNode theRootNode) {
		inorder(theRootNode);
	}

	// --------------------------------------------------------------

	// ------------------display nicely---------------------------
	public void niceDisplay() {
		traverse(0, theBTRootNode);
	}

	// level is a recursion level, 0 for root
	public void traverse(int level, BinNode theRootNode) {
		// build indent according to the recursion level
		char[] indent = new char[level];
		Arrays.fill(indent, ' ');

		if (theRootNode != null) {
			theRootNode.showIndent(new String(indent));
			traverse(level + 1, theRootNode.getLeftBNode());
			traverse(level + 1, theRootNode.getRightBNode());
		}
	}

	// -------------------------------------------------------------------

	public BinNode getTheBTRootNode() {
		return theBTRootNode;
	}

	// ----- Search for key name and returns ref.
	// to BNode or null if not found--------
	protected void search(BinNode theRootNode, BinNode keyNode, ArrayList<BinNode> results) {
		// if the root is null returns null
		if (theRootNode == null) {
			return;
		} else {
			// checks if they are equal
			if (theRootNode.equals(keyNode)) {
				results.add(theRootNode);
			} else {
				search(theRootNode.getLeftBNode(), keyNode, results);
				search(theRootNode.getRightBNode(), keyNode, results);
			}
		}
	}

	// check if the children of the relation are parameters
	public boolean isEndRelation() {
		return Integer.parseInt(theBTRootNode.getLeftBNode().getLocalVariableName()) < Utility.MAX_PARAMETER_ID_ALLOWED
				&& Integer.parseInt(theBTRootNode.getRightBNode().getLocalVariableName()) < Utility.MAX_PARAMETER_ID_ALLOWED;
	}

	public BInstruction getInst() {
		return inst;
	}

	public void setInst(BInstruction inst) {
		this.inst = inst;
	}

	public boolean equals(Object relation) {
		return relation instanceof Relation && theBTRootNode.equals(((Relation) relation).getTheBTRootNode())
				&& theBTRootNode.getLeftBNode().equals(((Relation) relation).getTheBTRootNode().getLeftBNode())
				&& theBTRootNode.getRightBNode().equals(((Relation) relation).getTheBTRootNode().getRightBNode());
	}

	public int hashCode() {
		return theBTRootNode.hashCode();
	}
}

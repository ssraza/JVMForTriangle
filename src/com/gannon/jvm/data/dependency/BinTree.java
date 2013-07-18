package com.gannon.jvm.data.dependency;

public class BinTree {
	BinNode theBTRootNode;

	public BinTree(BinNode theBTRootNode) // constructor
	{
		this.theBTRootNode = theBTRootNode;
	}

	protected BinNode insertToLeft(BinNode theRootNode, BinNode myLeftNewNode) {
		if (theRootNode != null) {
			theRootNode.setLeftBNode(myLeftNewNode);
		}
		return theRootNode;
	}

	protected BinNode insertToRight(BinNode theRootNode, BinNode myRightNewNode) {
		if (theRootNode != null) {
			theRootNode.setRightBNode(myRightNewNode);
		}
		return theRootNode;
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

}

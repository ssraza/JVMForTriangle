package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.BinTree;
import com.gannon.jvm.data.dependency.DependencyAnalyzable;
import com.gannon.jvm.data.dependency.DependencyDataHolder;
import com.gannon.jvm.utilities.Utility;

public class BIAdd extends BInstruction implements DependencyAnalyzable {

	public BIAdd() {
		super();
	}

	public BIAdd(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getPC();

		myOperandStack.push((Integer) myOperandStack.pop()
				+ (Integer) myOperandStack.pop());

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 96;
	}

	@Override
	public void analyzing(DependencyDataHolder dependency) {
		Stack<String> myOperandStack =dependency.getTempVarialbeStack();
		BinNode rightNode= new BinNode(myOperandStack.pop());
		BinNode leftNode= new BinNode(myOperandStack.pop());
		BinNode rootNode=new BinNode(Integer.toString(Utility.getNextID()));
		BinTree tree=new BinTree(rootNode);
		tree.insertToLeft(leftNode);
		tree.insertToRight(rightNode);

		myOperandStack.push(rootNode.getId());
		dependency.getListOfTrees().add(tree);
	}
}

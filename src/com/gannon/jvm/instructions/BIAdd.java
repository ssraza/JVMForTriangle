package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.RelationCollector;
import com.gannon.jvm.data.dependency.Relation;
import com.gannon.jvm.utilities.Utility;

public class BIAdd extends BInstruction  {

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
	public void analyzing(RelationCollector dependency) {
		Stack<String> myOperandStack =dependency.getTempVariableStack();
		BinNode rightNode= new BinNode(myOperandStack.pop());
		BinNode leftNode= new BinNode(myOperandStack.pop());
		BinNode rootNode=new BinNode(Integer.toString(Utility.getNextID()));
		Relation tree=new Relation(rootNode);
		tree.insertToLeft(leftNode);
		tree.insertToRight(rightNode);

		myOperandStack.push(rootNode.getId());
		dependency.getListOfTrees().add(tree);
		dependency.setTempVariableStack(myOperandStack);
	}
}

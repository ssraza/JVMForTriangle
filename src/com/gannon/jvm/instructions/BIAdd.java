package com.gannon.jvm.instructions;

import java.util.ArrayList;
import java.util.Stack;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Relation;
import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.data.dependency.Relations;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.Utility;

public class BIAdd extends BInstruction  {

	public BIAdd(int lineNumber) {
		super(lineNumber);
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
	public void analyzing(RelationFrame rFrame) {
		Stack<String> myOperandStack =rFrame.getTempVariableStack();
		BinNode rightNode= new BinNode(myOperandStack.pop());
		BinNode leftNode= new BinNode(myOperandStack.pop());
		BinNode rootNode=new BinNode(Integer.toString(Utility.getNextID()));
		Relation relation=new Relation(rootNode, this);
		relation.insertToLeft(leftNode);
		relation.insertToRight(rightNode);
		Relations relations=rFrame.getRelations();

		relations.combinRelations(relation);
		relations.addRelation(relation);

		myOperandStack.push(rootNode.getLocalVariableName());

		rFrame.setTempVariableStack(myOperandStack);
	}


	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();

		myOperandStack.push((Integer) myOperandStack.pop()
				+ (Integer) myOperandStack.pop());

		pathFrame.setOperandStack(myOperandStack);
		return null;
	}
}

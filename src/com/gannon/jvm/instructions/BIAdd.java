package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.OpcodeUtility;

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
	public void analyzing(DependencyFrame rFrame) {
		Stack<String> myOperandStack =rFrame.getTempVariableStack();
		BinNode rightNode= new BinNode(myOperandStack.pop());
		BinNode leftNode= new BinNode(myOperandStack.pop());
		BinNode rootNode=new BinNode(Integer.toString(OpcodeUtility.getNextID()));
		Dependency relation=new Dependency(rootNode, this);
		relation.insertToLeft(leftNode);
		relation.insertToRight(rightNode);
		Dependencies relations=rFrame.getRelations();

		relations.expendTheRelations(relation);
		relations.add(relation);

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

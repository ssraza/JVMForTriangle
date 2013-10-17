package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.OpcodeUtility;

public class BIAdd extends BInstruction  {

	public BIAdd(int lineNumber) {
		super(lineNumber);
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getLineNumber();

		myOperandStack.push(myOperandStack.pop()
				+ myOperandStack.pop());

		activeFrame.setLineNumber(++pc);
		return null;
	}

	@Override
	public int getOpcode() {
		return 96;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {
		Stack<Object>  valueStack= rFrame.getOperandStack();
		Integer secondValue = (Integer) valueStack.pop();
		Integer firstValue = (Integer) valueStack.pop();	
		Integer sum = secondValue+firstValue;
		valueStack.push(sum);
		
		Stack<String> myOperandStack =rFrame.getIntermediateVariableNameStack();
		BinNode rightNode= new BinNode(myOperandStack.pop(),secondValue);
		BinNode leftNode= new BinNode(myOperandStack.pop(),firstValue);
		BinNode rootNode=new BinNode(Integer.toString(OpcodeUtility.getNextID()),sum);
		myOperandStack.push(rootNode.getVariableName());
		
		Dependency relation=new Dependency(rootNode, this);		
		relation.insertToLeft(leftNode);
		relation.insertToRight(rightNode);
		
		Dependencies relations=rFrame.getRelations();
		relations.expendTheRelations(relation);
		relations.add(relation);
	}


	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();

		myOperandStack.push(myOperandStack.pop()
				+ myOperandStack.pop());

		pathFrame.setOperandStack(myOperandStack);
		return null;
	}
}

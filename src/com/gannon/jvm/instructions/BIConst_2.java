package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.OpcodeUtility;

public class BIConst_2 extends BInstruction {
	public BIConst_2(int lineNumber) {
		super(lineNumber);
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		Integer pc = activeFrame.getLineNumber();
		myOperandStack.push(2);
		activeFrame.setLineNumber(++pc);
		return null;
	}

	@Override
	public int getOpcode() {
		return 5;
	}


	public Integer getOperand() {
		return 2;
	}

	@Override
	public void analyzing(DependencyFrame fFrame) {
		
		Stack<Object> valueStack = fFrame.getOperandStack();
		valueStack.push(2);
		
		Stack<String> nameStack =fFrame.getIntermediateVariableNameStack();
		BinNode rootNode=new BinNode(Integer.toString(OpcodeUtility.getNextID()), 2);
		nameStack.push(rootNode.getVariableName());
		
		Dependency relation=new Dependency(rootNode, this);		
		
		Dependencies relations=fFrame.getRelations();
		relations.add(relation);

	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		myOperandStack.push(2);
		return null;
	}
}

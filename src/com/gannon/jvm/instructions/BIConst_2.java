package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

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
		Stack<String> myOperandStack = fFrame.getIntermediateVariableNameStack();
		myOperandStack.add(new Integer(getOperand()).toString());

	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		myOperandStack.push(2);
		return null;
	}
}

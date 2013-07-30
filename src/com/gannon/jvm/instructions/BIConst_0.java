package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BIConst_0 extends BInstruction {
	
	public BIConst_0(int lineNumber) {
		super(lineNumber);
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		Integer pc = activeFrame.getLineNumber();
		myOperandStack.push(0);
		activeFrame.setLineNumber(++pc);
		return null;
	}

	public int getOpcode() {
		return 3;
	}

	public Integer getOperand() {
		return 0;
	}

	@Override
	public void analyzing(DependencyFrame fFrame) {
		Stack<String> tempVariableStack = fFrame.getTempVariableStack();
		tempVariableStack.add(new Integer(getOperand()).toString());
		
		//copied from  execute(PathFrame pathFrame) 
//		Stack<Object> myOperandStack = fFrame.getOperandStack();
//		myOperandStack.push(0);
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		myOperandStack.push(0);
		return null;
	}

}

package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BIConst_m1 extends BInstruction {

	public BIConst_m1(int lineNumber) {
		super(lineNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		Integer pc = activeFrame.getLineNumber();
		myOperandStack.push(-1);
		activeFrame.setLineNumber(++pc);
		return null;
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		myOperandStack.push(-1);
		return null;
	}

	@Override
	public int getOpcode() {
		// TODO Auto-generated method stub
		return 02;
	}

	public Integer getOperand() {
		return -1;
	}
	@Override
	public void analyzing(DependencyFrame rFrame) {
		Stack<String> tempVariableStack = rFrame.getIntermediateVariableNameStack();
		tempVariableStack.add(new Integer(getOperand()).toString());
		
		//copied from  execute(PathFrame pathFrame) 
		Stack<Object> valueStack = rFrame.getOperandStack();
		valueStack.push(-1);
		
	}

}

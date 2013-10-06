package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BIConst_4 extends BInstruction {
	public BIConst_4(int lineNumber) {
		super(lineNumber);
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getLineNumber();
		myOperandStack.push(4);
		activeFrame.setLineNumber(++pc);
		return null;
	}

	@Override
	public int getOpcode() {
		return 7;
	}

	public Integer getOperand() {
		return 4;
	}

	@Override
	public void analyzing(DependencyFrame fFrame) {
		Stack<String> varibleNameStack = fFrame.getIntermediateVariableNameStack();
		varibleNameStack.add(new Integer(getOperand()).toString());
		
		//copied from  execute(PathFrame pathFrame) 
		Stack<Object> myOperandStack = fFrame.getOperandStack();
		myOperandStack.push(4);
	}
	
	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		myOperandStack.push(4);

		return null;
	}

}

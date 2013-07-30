package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BIConst_1 extends BInstruction {

	public BIConst_1(int lineNumber) {
		super(lineNumber);
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		Integer pc = activeFrame.getLineNumber();
		myOperandStack.push(1);
		activeFrame.setLineNumber(++pc);
		return null;
	}

	public int getOpcode() {
		return 04;
	}

	public Integer getOperand() {
		return 1;
	}

	@Override
	public void analyzing(DependencyFrame fFrame) {
		Stack<String> varibleNameStack = fFrame.getIntermediateVariableStack();
		varibleNameStack.add(new Integer(getOperand()).toString());
		
		//copied from  execute(PathFrame pathFrame) 
		Stack<Object> myOperandStack = fFrame.getValueStack();
		myOperandStack.push(1);

	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		myOperandStack.push(1);
		return null;
	}

}

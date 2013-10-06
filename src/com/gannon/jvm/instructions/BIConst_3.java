package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BIConst_3 extends BInstruction {

	public BIConst_3(int lineNumber) {
		super(lineNumber);
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getLineNumber();
		myOperandStack.push(3);

		activeFrame.setLineNumber(++pc);
		return null;
	}

	@Override
	public int getOpcode() {
		return 6;
	}

	public Integer getOperand() {
		return 3;
	}

	@Override
	public void analyzing(DependencyFrame fFrame) {
		Stack<String> varibleNameStack = fFrame.getIntermediateVariableNameStack();
		varibleNameStack.add(new Integer(getOperand()).toString());
		
		//copied from  execute(PathFrame pathFrame) 
		Stack<Object> myOperandStack = fFrame.getOperandStack();
		myOperandStack.push(3);
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		myOperandStack.push(3);

		return null;
	}

}

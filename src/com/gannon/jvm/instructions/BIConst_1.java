package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BIConst_1 extends BInstruction {

	public BIConst_1(int lineNumber) {
		super(lineNumber);
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		Integer pc = activeFrame.getPC();
		myOperandStack.push(1);
		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 04;
	}

	public Integer getOperand() {
		return 1;
	}

	@Override
	public void analyzing(RelationFrame fFrame) {
		Stack<String> myOperandStack = fFrame.getTempVariableStack();
		myOperandStack.add(new Integer(getOperand()).toString());
		fFrame.setTempVariableStack(myOperandStack);

	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		myOperandStack.push(1);
		pathFrame.setOperandStack(myOperandStack);
		return null;
	}

}

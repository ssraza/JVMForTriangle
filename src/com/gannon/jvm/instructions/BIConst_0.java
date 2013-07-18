package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;

public class BIConst_0 extends BInstruction {

	public BIConst_0(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		Integer pc = activeFrame.getPC();
		myOperandStack.push(0);
		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 3;
	}

	public Integer getOperand() {
		return 0;
	}

}

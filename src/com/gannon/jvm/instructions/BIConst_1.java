package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;

public class BIConst_1 extends BInstruction {

	public BIConst_1() {
		super();
	}

	public BIConst_1(int lineNumber) {
		setLineNumber(lineNumber);
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

}

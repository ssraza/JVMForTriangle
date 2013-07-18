package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;

public class BIAdd extends BInstruction {

	public BIAdd() {
		super();
	}

	public BIAdd(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getPC();

		myOperandStack.push((Integer) myOperandStack.pop()
				+ (Integer) myOperandStack.pop());

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 96;
	}
}

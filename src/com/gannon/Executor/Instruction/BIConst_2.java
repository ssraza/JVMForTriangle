package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;

public class BIConst_2 extends BInstruction {
	public BIConst_2() {
		super();
	}

	public BIConst_2(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public String toString() {
		return super.toString();
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		Integer pc = activeFrame.getPC();
		myOperandStack.push(2);
		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 5;
	}

	public String getOpcodeCommand() {
		return "iconst_2";
	}

	public int getOperand() {
		return 2;
	}
}

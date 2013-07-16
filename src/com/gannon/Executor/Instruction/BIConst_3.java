package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;

public class BIConst_3 extends BInstruction {

	public BIConst_3() {
		super();
	}

	public BIConst_3(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public String toString() {
		return super.toString();
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getPC();
		myOperandStack.push(3);

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 6;
	}

	public String getOpcodeCommand() {
		return "iconst_3";
	}

	public int getOperand() {
		return 3;
	}

}

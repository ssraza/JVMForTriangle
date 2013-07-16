package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;

public class BIConst_4 extends BInstruction {
	public BIConst_4() {
		super();
	}

	public BIConst_4(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public String toString() {
		return super.toString();
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getPC();
		myOperandStack.push(4);
		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 7;
	}

	public String getOpcodeCommand() {
		return "iconst_4";
	}

	public int getOperand() {
		return 4;
	}

}

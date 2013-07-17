package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.GannonJVM.BFrame;

public class BIConst_0 extends BInstruction {

	public BIConst_0(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public String toString() {
		return super.toString();
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

	public String getOpcodeCommand() {
		return "iconst_0";
	}

	public Integer getOperand() {
		return 0;
	}

}

package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;

public class BIAdd extends BInstruction {

	public BIAdd() {
		super();
	}

	public BIAdd(int lineNumber) {
		setLineNumber(lineNumber);
		System.out.println("BIAdd extends BInstruction ");
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

	public String getOpcodeCommand() {
		return "iadd";
	}

	public String toString() {
		return super.toString();
	}

	public String getOperand() {
		return "-1";
	}

}

package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;
import com.gannon.jvm.JVMStackSingleton;

public class BIReturn extends BInstruction {
	public BIReturn() {
		super();
	}

	public BIReturn(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public String toString() {
		return super.toString();
	}

	// public Integer execute(BFrame activeFrame, BJVMStack jStack) {
	// jStack.pop();
	// BFrame buttonFrame=jStack.peek();
	// buttonFrame.getOperandStack().push(activeFrame.getOperandStack().pop());
	//
	// }

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		Integer pc = activeFrame.getPC();
		//-1 means the end of the instructions??
		activeFrame.setPC(-1);

		return (Integer) myOperandStack.pop();

	}

	public int getOpcode() {
		return 172;
	}

	public String getOpcodeCommand() {
		return "ireturn";
	}

}

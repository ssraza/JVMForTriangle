package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.GannonJVM.BFrame;
import com.gannon.Executor.GannonJVM.BLocalVarTable;
import com.gannon.Executor.GannonJVM.JVMStackSingleton;

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

		JVMStackSingleton.getInstance().popActivekFrame();
		if (JVMStackSingleton.getInstance().size() != 0){
			JVMStackSingleton.getInstance().peekActivekFrame().getOperandStack().push((Integer) myOperandStack.lastElement());
		}
		return (Integer) myOperandStack.pop();

	}

	public int getOpcode() {
		return 172;
	}

	public String getOpcodeCommand() {
		return "ireturn";
	}

}

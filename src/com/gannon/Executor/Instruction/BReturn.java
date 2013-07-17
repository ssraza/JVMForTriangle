package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.GannonJVM.BFrame;
import com.gannon.Executor.GannonJVM.BLocalVarTable;
import com.gannon.Executor.GannonJVM.JVMStackSingleton;

public class BReturn extends BInstruction {

	public BReturn() {
		super();
	}

	public BReturn(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public String toString() {
		return super.toString();
	}

	public Object execute(BFrame activeFrame) {
		String opCodeString = getOpcodeCommand();
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Integer pc = activeFrame.getPC();
		activeFrame.setPC(++pc);
		JVMStackSingleton.getInstance().removeMethodFrame();
		return null;// return a null letting main know to execute next method

	}

	public int getOpcode() {
		return 177;
	}

	public String getOpcodeCommand() {
		return "return";
	}

	public String getOperand() {
		return "-1";
	}

}

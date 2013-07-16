package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;
import com.gannon.Executor.JVMExecutionObjects.JVMStackSingleton;
import com.gannon.Main.Main;

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
		Main.setReturnType("void");
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

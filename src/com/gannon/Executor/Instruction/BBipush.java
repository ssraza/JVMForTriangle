package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;

public class BBipush extends BInstruction {

	private int operand1;

	public BBipush(int operand1, int lineNumber) {
		setLineNumber(lineNumber);
		this.operand1 = operand1;
	}

	public BBipush(int operand1) {
		super();
		this.operand1 = operand1;
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Integer pc = activeFrame.getPC();

		myOperandStack.push(this.operand1);
		activeFrame.setVarTable(myLocalVariableTable);
		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 16;
	}

	public String getOpcodeCommand() {
		return "bipush" + " " + operand1;
	}

	public String toString() {
		return super.toString() + " " + operand1;
	}

	public int getOperand() {
		return operand1;
	}

}

package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;

public class BILoad extends BInstruction {
	private int operand1;

	public BILoad(int operand1) {
		super();
		this.operand1 = operand1;
	}

	public BILoad(int operand1, int lineNumber) {
		setLineNumber(lineNumber);
		this.operand1 = operand1;
	}

	public String toString() {
		return super.toString() + " " + operand1;
	}

    //
	public Object execute(BFrame activeFrame) {
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Stack<Integer> operandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getPC();
		// push local value to top of stack
		operandStack.push((Integer) myLocalVariableTable.getLocalVariable(operand1));

		//point to next instruction
		activeFrame.setPC((++pc));
		return null;
	}

	public int getOpcode() {
		return 21;
	}

	public String getOpcodeCommand() {
		return "iload" + " " + operand1;
	}

	public int getOperand() {
		return operand1;
	}
}

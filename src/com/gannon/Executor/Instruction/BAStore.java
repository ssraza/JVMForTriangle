package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;

public class BAStore extends BInstruction {
	private int operand1;

	public BAStore(int operand1, int lineNumber) {
		setLineNumber(lineNumber);
		this.operand1 = operand1;
	}

	public BAStore(int operand1) {
		super();
		this.operand1 = operand1;
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Integer pc = activeFrame.getPC();

		Integer value = myOperandStack.pop(); // get the top of the operand
												// stack
		myLocalVariableTable.add(operand1, value);// add the value on
													// localVariableTable
													// at the position defined
													// by operand1.
		activeFrame.setVarTable(myLocalVariableTable);
		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;

	}

	public int getOpcode() {
		return 58;
	}

	public String getOpcodeCommand() {
		return "astore" + " " + operand1;
	}

	public String toString() {
		return super.toString() + " " + operand1;
	}

	public int getOperand() {
		return operand1;
	}

}
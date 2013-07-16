package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;

public class BALoad extends BInstruction {
	private int operand1;
	public boolean debug = true;

	public BALoad(int operand1, int linNumber) {
		setLineNumber(linNumber);
		this.operand1 = operand1;
	}

	public BALoad(int operand1) {
		this.operand1 = operand1;
	}

	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Integer pc = activeFrame.getPC();

		myOperandStack.push(myLocalVariableTable.getLocalVariable(operand1));
		activeFrame.setPC(++pc);
		return null;
	}

	public String toString() {
		return super.toString() + " " + operand1;
	}

	public int getOpcode() {
		return 25;
	}

	public String getOpcodeCommand() {
		return "aload" + " " + operand1;
	}

	public int getOperand() {
		return operand1;
	}

}

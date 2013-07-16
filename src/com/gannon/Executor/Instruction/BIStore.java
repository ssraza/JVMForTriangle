package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;

public class BIStore extends BInstruction {

	private int operand1;

	public BIStore(int operand1) {
		super();
		this.operand1 = operand1;
	}

	public BIStore(int operand1, int lineNumber) {
		setLineNumber(lineNumber);
		this.operand1 = operand1;
	}

	public String toString() {
		return super.toString() + " " + operand1;
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();

		Integer pc = activeFrame.getPC();
		// myLocalVariableTable.add(myOperandStack.elementAt(instruction.getOperand1()));//pop
		// TOS and store in frame at operand1 location
		myLocalVariableTable.setElementAt(this.operand1, myOperandStack.pop());
		activeFrame.setVarTable(myLocalVariableTable);
		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 54;
	}

	public String getOpcodeCommand() {
		return "istore" + " " + operand1;
	}

	public int getOperand() {
		return operand1;
	}

}
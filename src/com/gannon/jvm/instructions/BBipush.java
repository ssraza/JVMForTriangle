package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;

//push one-byte signed integer
//http://www.vmth.ucdavis.edu/incoming/Jasmin/ref-_bipush.html
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

	public Integer getOperand() {
		return operand1;
	}

}

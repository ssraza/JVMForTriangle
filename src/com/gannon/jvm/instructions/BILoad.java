package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;
import com.gannon.jvm.data.dependency.RelationCollector;

public class BILoad extends BInstruction {
	private int operand1;

	public BILoad(int operand1, int lineNumber) {
		setLineNumber(lineNumber);
		this.operand1 = operand1;
	}

	public String toString() {
		return super.toString() + " " + getOperand();
	}

	//
	public Object execute(BFrame activeFrame) {
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Stack<Integer> operandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getPC();
		// push local value to top of stack
		operandStack.push((Integer) myLocalVariableTable
				.getLocalVariable(operand1));

		// point to next instruction
		activeFrame.setPC((++pc));
		return null;
	}

	public int getOpcode() {
		return 21;
	}

	public Integer getOperand() {
		return operand1;
	}

	@Override
	public void analyzing(RelationCollector dependency) {
		Stack<String> tempVariableStack = dependency.getTempVariableStack();
		tempVariableStack.push(getOperand().toString());
		dependency.setTempVariableStack(tempVariableStack);
	}
}

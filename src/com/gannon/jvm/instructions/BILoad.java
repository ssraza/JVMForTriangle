package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;

public class BILoad extends BInstruction {
	private int operand1;

	public BILoad(int operand1, int lineNumber) {
		super(lineNumber);
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
		activeFrame.setOperandStack(operandStack);
		return null;
	}

	public int getOpcode() {
		return 21;
	}

	public Integer getOperand() {
		return operand1;
	}

	@Override
	public void analyzing(RelationFrame rFrame) {
		Stack<String> tempVariableStack = rFrame.getTempVariableStack();
		tempVariableStack.push(getOperand().toString());
		rFrame.setTempVariableStack(tempVariableStack);
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();
		Stack<Integer> operandStack = pathFrame.getOperandStack();

		// push local value to top of stack
		operandStack.push((Integer) myLocalVariableTable
				.getLocalVariable(operand1));
		return null;
	}
}

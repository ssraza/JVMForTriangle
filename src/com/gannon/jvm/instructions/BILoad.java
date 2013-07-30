package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
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

		Integer pc = activeFrame.getLineNumber();
		// push local value to top of stack
		operandStack.push((Integer) myLocalVariableTable
				.getLocalVariable(operand1));

		// point to next instruction
		activeFrame.setLineNumber((++pc));
		return null;
	}

	public int getOpcode() {
		return 21;
	}

	public Integer getOperand() {
		return operand1;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {
		Stack<String> tempVariableStack = rFrame.getTempVariableStack();
		tempVariableStack.push(getOperand().toString());
		
		//same as execution, we need the value as well
		BLocalVarTable myLocalVariableTable = rFrame.getLocalVariableTable();
		Stack<Object> operandStack = rFrame.getOperandStack();

		// push local value to top of stack
	    //operandStack.push((Integer) myLocalVariableTable.getLocalVariable(operand1));
	}

	@Override
	//iload 1, must start from 1
	//remember to add additional number to index 0
	public Object execute(PathFrame pathFrame) {
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();
		Stack<Integer> operandStack = pathFrame.getOperandStack();

		// push local value to top of stack
		operandStack.push((Integer) myLocalVariableTable
				.getLocalVariable(operand1));
		return null;
	}
}

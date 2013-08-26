package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;

public class BALoad extends BInstruction {
	private int operand1;
	public boolean debug = true;

	public BALoad(int operand1, int lineNumber) {
		super(lineNumber);
		this.operand1 = operand1;
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getLocalVariableTable();
		Integer pc = activeFrame.getLineNumber();

		myOperandStack.push(myLocalVariableTable.getLocalVariable(operand1));
		activeFrame.setLineNumber(++pc);
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + " " + operand1;
	}

	@Override
	public int getOpcode() {
		return 25;
	}

	public int getOperand() {
		return operand1;
	}

	@Override
	public void analyzing(DependencyFrame dependency) {
		Stack<String> myOperandStack =dependency.getIntermediateVariableNameStack();
		myOperandStack.add(new Integer(getOperand()).toString());
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Object> myOperandStack = pathFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();

		myOperandStack.push(myLocalVariableTable.getLocalVariable(operand1));
		return null;
	}
}

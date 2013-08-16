package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;

public class BAStore extends BInstruction {
	private int operand1;

	public BAStore(int operand1, int lineNumber) {
		super(lineNumber);
		this.operand1 = operand1;
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getLocalVariableTable();
		Integer pc = activeFrame.getLineNumber();

		Integer value = myOperandStack.pop(); // get the top of the operand
												// stack
		myLocalVariableTable.add(operand1, value);// add the value on
													// localVariableTable
													// at the position defined
													// by operand1.
		activeFrame.setLineNumber(++pc);
		return null;
	}

	@Override
	public int getOpcode() {
		return 58;
	}

	@Override
	public String toString() {
		return super.toString() + " " + operand1;
	}

	public Object getOperand() {
		return operand1;
	}
	
	@Override
	public void analyzing(DependencyFrame dependency) {
		Stack<String> myOperandStack = dependency.getIntermediateVariableStack();
		
		//pop TOS 
		myOperandStack.pop();
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();

		Integer value = myOperandStack.pop(); // get the top of the operand
												// stack
		myLocalVariableTable.add(operand1, value);// add the value on
													// localVariableTable
													// at the position defined
													// by operand1.
		return null;
	}

}

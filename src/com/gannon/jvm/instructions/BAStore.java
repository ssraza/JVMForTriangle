package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;

public class BAStore extends BInstruction {
	private int operand1;

	public BAStore(int operand1, int lineNumber) {
		super(lineNumber);
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

	public String toString() {
		return super.toString() + " " + operand1;
	}

	public Object getOperand() {
		return operand1;
	}
	
	@Override
	public void analyzing(RelationFrame dependency) {
//		Stack<String> myOperandStack = dependency.getTempVarialbeStack();
//		myOperandStack.pop();
//		dependency.setTempVarialbeStack(myOperandStack);	
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
		pathFrame.setLocalVariableTable(myLocalVariableTable);
		pathFrame.setOperandStack(myOperandStack);
		return null;
	}

}

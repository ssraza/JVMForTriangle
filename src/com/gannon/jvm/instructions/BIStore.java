package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;

public class BIStore extends BInstruction {

	private int operand1;

	public BIStore(int operand1, int lineNumber) {
		super(lineNumber);
		this.operand1 = operand1;
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

	public Integer getOperand() {
		return operand1;
	}

	public String toString() {
		return super.toString() + " " + Integer.toString(getOperand());
	}

	@Override
	public void analyzing(RelationFrame dependency) {
//		Stack<String> myOperandStack = dependency.getTempVariableStack();
//		myOperandStack.pop();
//		dependency.setTempVariableStack(myOperandStack);
	}
}

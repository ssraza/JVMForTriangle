package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;

public class BIStore extends BInstruction {

	private int operand1;

	public BIStore(int operand1, int lineNumber) {
		super(lineNumber);
		this.operand1 = operand1;
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getLocalVariableTable();
		
		Integer pc = activeFrame.getLineNumber();
		// myLocalVariableTable.add(myOperandStack.elementAt(instruction.getOperand1()));//pop
		// TOS and store in frame at operand1 location
		myLocalVariableTable.setElementAt(this.operand1, myOperandStack.pop());
		activeFrame.setLineNumber(++pc);
		return null;
	}

	@Override
	public int getOpcode() {
		return 54;
	}

	public Integer getOperand() {
		return operand1;
	}

	@Override
	public String toString() {
		return super.toString() + " " + Integer.toString(getOperand());
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {
		
		Stack<String> nameStack = rFrame.getIntermediateVariableNameStack();
		nameStack.push(getOperand().toString());
		
		Stack<Object> valueStack = rFrame.getOperandStack();
	    valueStack.push(nameStack.pop());

	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();

		// myLocalVariableTable.add(myOperandStack.elementAt(instruction.getOperand1()));//pop
		// TOS and store in frame at operand1 location
		myLocalVariableTable.setElementAt(this.operand1, myOperandStack.pop());
		return null;
	}
}

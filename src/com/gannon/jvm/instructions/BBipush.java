package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;

//push one-byte signed integer
//http://www.vmth.ucdavis.edu/incoming/Jasmin/ref-_bipush.html
public class BBipush extends BInstruction {

	private int operand1;

	public BBipush(int operand1, int lineNumber) {
		super(lineNumber);
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

	public String toString() {
		return super.toString() + " " + operand1;
	}

	public Integer getOperand() {
		return operand1;
	}

	@Override
	public void analyzing(RelationFrame dependency) {
		Stack<String> myOperandStack = dependency.getTempVariableStack();
		myOperandStack.add(new Integer(getOperand()).toString());
		dependency.setTempVariableStack(myOperandStack);

	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();

		myOperandStack.push(this.operand1);
		pathFrame.setLocalVariableTable(myLocalVariableTable);
		pathFrame.setOperandStack(myOperandStack);
		return null;
	}

}

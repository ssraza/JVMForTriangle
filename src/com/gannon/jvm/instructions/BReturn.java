package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;

public class BReturn extends BInstruction {

	public BReturn(int lineNumber) {
		super(lineNumber);
	}

	public String toString() {
		return super.toString();
	}

	public Object execute(BFrame activeFrame) {
		String opCodeString = getOpcodeCommand();
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Integer pc = activeFrame.getPC();
		activeFrame.setPC(++pc);
		return null;// return a null letting main know to execute next method
	}

	public int getOpcode() {
		return 177;
	}

	public String getOpcodeCommand() {
		return "return";
	}

	public String getOperand() {
		return "-1";
	}

	@Override
	public void analyzing(DependencyFrame dependency) {
		Stack<String> myOperandStack = dependency.getTempVariableStack();
		myOperandStack.pop();
		dependency.setTempVariableStack(myOperandStack);
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		String opCodeString = getOpcodeCommand();
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();
		return null;// return a null letting main know to execute next method
	}

}

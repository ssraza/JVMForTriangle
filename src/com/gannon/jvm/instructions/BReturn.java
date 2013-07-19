package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;
import com.gannon.jvm.JVMStackSingleton;
import com.gannon.jvm.data.dependency.RelationCollector;

public class BReturn extends BInstruction {

	public BReturn(int lineNumber) {
		setLineNumber(lineNumber);
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
		JVMStackSingleton.getInstance().popActivekFrame();
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
	public void analyzing(RelationCollector dependency) {
		Stack<String> myOperandStack = dependency.getTempVariableStack();
		myOperandStack.pop();
		dependency.setTempVariableStack(myOperandStack);
	}

}

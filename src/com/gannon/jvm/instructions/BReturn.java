package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.OpcodeUtility;

public class BReturn extends Return {

	public BReturn(int lineNumber) {
		super(lineNumber);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Integer pc = activeFrame.getLineNumber();
		activeFrame.setLineNumber(OpcodeUtility.END_INSTRUCTION_FLAG);
		return null;// return a null letting main know to execute next method
	}

	@Override
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
		Stack<String> myOperandStack = dependency.getIntermediateVariableNameStack();
		myOperandStack.pop();
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		return null;// return a null letting main know to execute next method
	}

}

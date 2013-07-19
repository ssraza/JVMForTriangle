package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.data.dependency.RelationCollector;

public class BIConst_3 extends BInstruction {

	public BIConst_3(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getPC();
		myOperandStack.push(3);

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 6;
	}

	public Integer getOperand() {
		return 3;
	}

	@Override
	public void analyzing(RelationCollector dependency) {
		Stack<String> myOperandStack = dependency.getTempVariableStack();
		myOperandStack.add(new Integer(getOperand()).toString());
		dependency.setTempVariableStack(myOperandStack);
	}

}

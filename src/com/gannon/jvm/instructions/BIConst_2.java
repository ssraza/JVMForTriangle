package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.BFrame;

public class BIConst_2 extends BInstruction {
	public BIConst_2(int lineNumber) {
		super(lineNumber);
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		Integer pc = activeFrame.getPC();
		myOperandStack.push(2);
		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
		return null;
	}

	public int getOpcode() {
		return 5;
	}


	public Integer getOperand() {
		return 2;
	}

	@Override
	public void analyzing(RelationFrame fFrame) {
		Stack<String> myOperandStack = fFrame.getTempVariableStack();
		myOperandStack.add(new Integer(getOperand()).toString());
		fFrame.setTempVariableStack(myOperandStack);

	}
}

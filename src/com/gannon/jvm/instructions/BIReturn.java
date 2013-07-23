package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.Utility;

public class BIReturn extends BInstruction {

	public BIReturn(int lineNumber) {
		super(lineNumber);
	}

	public String toString() {
		return super.toString();
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		//-1 means the end of the instructions??
		activeFrame.setPC(Utility.END_INSTRUCTION_FLAG);

		return (Integer) myOperandStack.pop();
	}

	public int getOpcode() {
		return 172;
	}

	@Override
	public void analyzing(RelationFrame rFrame) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();

		return (Integer) myOperandStack.pop();
	}
}

package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.OpcodeUtility;

public class BIReturn extends Return {

	public BIReturn(int lineNumber) {
		super(lineNumber);
	}

	public String toString() {
		return super.toString();
	}

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		//-1 means the end of the instructions??
		activeFrame.setLineNumber(OpcodeUtility.END_INSTRUCTION_FLAG);
		System.out.println(activeFrame.getOperandStack());
		System.out.println(activeFrame.getVarTable());
		Integer returnedValue = (Integer) myOperandStack.pop();
		return returnedValue;
	}

	public int getOpcode() {
		return 172;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {
		System.out.println(rFrame.getIntermediateVariableStack());
		Stack<String> intermediateStack = rFrame.getIntermediateVariableStack();
		intermediateStack.pop();
		
		Stack<Object> myOperandStack = rFrame.getValueStack(); 
		myOperandStack.pop();
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();

		return (Integer) myOperandStack.pop();
	}
}

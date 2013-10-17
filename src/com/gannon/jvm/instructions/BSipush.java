package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

//push one-byte signed integer
//http://www.vmth.ucdavis.edu/incoming/Jasmin/ref-_bipush.html
public class BSipush extends BInstruction {

	private int operand1;

	public BSipush(int operand1, int lineNumber) {
		super(lineNumber);
		this.operand1 = operand1;
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		Integer pc = activeFrame.getLineNumber();

		myOperandStack.push(this.operand1);
		activeFrame.setLineNumber(++pc);
		return null;
	}

	@Override
	public int getOpcode() {
		return 17;
	}

	@Override
	public String toString() {
		return super.toString() + " " + operand1;
	}

	public Integer getOperand() {
		return operand1;
	}

	@Override
	public void analyzing(DependencyFrame dependency) {
		Stack<String> myOperandStack = dependency.getIntermediateVariableNameStack();
		myOperandStack.add(new Integer(getOperand()).toString());
}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Integer> myOperandStack = pathFrame.getOperandStack();
		myOperandStack.push(this.operand1);
		return null;
	}

}

package com.gannon.jvm.instructions;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BIAStore extends BInstruction{
	private int operand1;
	
	public BIAStore(int lineNumber) {
		super(lineNumber);
		// TODO Auto-generated constructor stub
	}
	public BIAStore(int operand1, int lineNumber) {
		super(lineNumber);
		this.operand1 = operand1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(BFrame activeFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOpcode() {
		// TODO Auto-generated method stub
		return 79;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + operand1;
	}

	public Integer getOperand() {
		return operand1;
	}

}

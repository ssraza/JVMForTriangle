package com.gannon.jvm.instructions;

import com.gannon.asm.components.BLabel;

public abstract class BPredicateInstruction extends BInstruction{
	private BLabel operand;
	public BPredicateInstruction(BLabel operand, int lineNumber) {
		super(lineNumber);
		this.operand=operand;
		// TODO Auto-generated constructor stub
	}
	public BLabel getOperand() {
		return operand;
	}
	public void setOperand(BLabel operand) {
		this.operand = operand;
	}

}

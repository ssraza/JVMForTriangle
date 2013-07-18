package com.gannon.jvm.data.dependency;

import com.gannon.jvm.instructions.BInstruction;

public class PredicateNode extends PNode{
	private Boolean runTimePredicateValue;
	private Boolean expectedPredicateValue;

	public PredicateNode(BInstruction ins) {
		super(ins);
	}

	public Boolean getRunTimePredicateValue() {
		return runTimePredicateValue;
	}

	public void setRunTimePredicateValue(Boolean runTimePredicateValue) {
		this.runTimePredicateValue = runTimePredicateValue;
	}

	public Boolean getExpectedPredicateValue() {
		return expectedPredicateValue;
	}

	public void setExpectedPredicateValue(Boolean expectedPredicateValue) {
		this.expectedPredicateValue = expectedPredicateValue;
	}
}
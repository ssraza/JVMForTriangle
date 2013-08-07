package com.gannon.bytecode.controlflowgraph;

public class CEdgeValue {
	private boolean expectedPredicateResult=false;

	public CEdgeValue() {
		super();
	}

	public boolean isExpectedPredicateResult() {
		return expectedPredicateResult;
	}

	public void setExpectedPredicateResult(boolean expectedPredicateResult) {
		this.expectedPredicateResult = expectedPredicateResult;
	}

}

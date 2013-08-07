package com.gannon.bytecode.controlflowgraph;

import com.gannon.jvm.utilities.ConstantsUtility;

public class CEdgeValue {
	private int expectedPredicateResult=ConstantsUtility.UNDEFINED_EXPECTED_VALUE;

	public CEdgeValue() {
		super();
	}

	public int getExpectedPredicateResult() {
		return expectedPredicateResult;
	}

	public void setExpectedPredicateResult(int expectedPredicateResult) {
		this.expectedPredicateResult = expectedPredicateResult;
	}

}

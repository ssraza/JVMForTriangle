package com.gannon.jvm.progam.path;

import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.utilities.ConstantsUtility;

public class PredicateNode extends Node {
	private int actualPredicateResult=ConstantsUtility.UNDEFINED_EXPECTED_VALUE;
	private int expectedPredicateResult=ConstantsUtility.UNDEFINED_EXPECTED_VALUE;
	//ignore executing instruction if the flag is set to true
	private boolean isIgnore=false;

	public PredicateNode(BInstruction ins) {
		super(ins);
	}

	public int getAcutalPredicateResult() {
		return actualPredicateResult;
	}

	public void setActualPredicateResult(int acutalPredicateResult) {
		this.actualPredicateResult = acutalPredicateResult;
	}

	public int getExpectedPredicateResult() {
		return expectedPredicateResult;
	}

	public void setExpectedPredicateResult(int expectedPredicateResult) {
		this.expectedPredicateResult = expectedPredicateResult;
	}

	public boolean hasPassed() {
		return actualPredicateResult==expectedPredicateResult;
	}

	public boolean isIgnore() {
		return isIgnore;
	}

	public void setIgnore(boolean isIgnore) {
		this.isIgnore = isIgnore;
	}

	@Override
	public String toString() {
		return getInstruction()+"ActualPredicateResult= " + actualPredicateResult + ";ExpectedPredicateResult= " + expectedPredicateResult;
	}
}

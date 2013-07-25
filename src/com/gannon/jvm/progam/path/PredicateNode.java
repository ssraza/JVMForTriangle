package com.gannon.jvm.progam.path;

import com.gannon.jvm.instructions.BInstruction;

public class PredicateNode extends Node {
	private Boolean actualPredicateResult=null;
	private Boolean expectedPredicateResult=null;
	//ignore executing instruction if the flag is set to true
	private boolean isIgnore=false;

	public PredicateNode(BInstruction ins) {
		super(ins);
	}

	public Boolean getAcutalPredicateResult() {
		return actualPredicateResult;
	}

	public void setActualPredicateResult(Boolean acutalPredicateResult) {
		this.actualPredicateResult = acutalPredicateResult;
	}

	public Boolean getExpectedPredicateResult() {
		return expectedPredicateResult;
	}

	public void setExpectedPredicateResult(Boolean expectedPredicateResult) {
		this.expectedPredicateResult = expectedPredicateResult;
	}

	public boolean hasPassed() {
		return actualPredicateResult.equals(expectedPredicateResult);
	}

	public boolean isIgnore() {
		return isIgnore;
	}

	public void setIgnore(boolean isIgnore) {
		this.isIgnore = isIgnore;
	}

	public String toString() {
		return getInstruction()+"ActualPredicateResult= " + actualPredicateResult + ";ExpectedPredicateResult= " + expectedPredicateResult;
	}
}

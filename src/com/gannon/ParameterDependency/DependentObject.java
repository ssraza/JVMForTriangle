package com.gannon.ParameterDependency;

public class DependentObject {
	private Object firstParameter;
	private Object secondParameter;
	private Object finalResult;
	private String arithmaticOperation;
	
	public DependentObject(Object firstParameter, Object secondParameter,
			Object finalResult, String arithmaticOperation) {
		super();
		this.firstParameter = firstParameter;
		this.secondParameter = secondParameter;
		this.finalResult = finalResult;
		this.arithmaticOperation = arithmaticOperation;
	}
	
	public Object getFirstParameter() {
		return firstParameter;
	}
	public void setFirstParameter(Object firstParameter) {
		this.firstParameter = firstParameter;
	}
	public Object getSecondParameter() {
		return secondParameter;
	}
	public void setSecondParameter(Object secondParameter) {
		this.secondParameter = secondParameter;
	}
	public Object getFinalResult() {
		return finalResult;
	}
	public void setFinalResult(Object finalResult) {
		this.finalResult = finalResult;
	}
	public String getArithmaticOperation() {
		return arithmaticOperation;
	}
	public void setArithmaticOperation(String arithmaticOperation) {
		this.arithmaticOperation = arithmaticOperation;
	}

}

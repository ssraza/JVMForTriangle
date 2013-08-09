package com.gannon.rule;

import java.util.ArrayList;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;

public class RuleIFcmpeq extends Rule {
	private int distance = 0;
	private boolean expectedPredicateResult;
	
	public RuleIFcmpeq(boolean expectedPredicateResult, InputObject currentInput, Dependencies dependecies,
			BinNode leftNode, BinNode rightNode, ArrayList<InputObject> generatedNewInputs) {
		super(currentInput, dependecies, leftNode, rightNode, generatedNewInputs);
		this.expectedPredicateResult = expectedPredicateResult;
		this.distance = distance();
	} 

	@Override
	public void dataGeneration() {
		if (expectedPredicateResult) {
			// we want to force: left value = right values
			if (isLeftGreaterThanRight()) {
				// decrease left, true or false indicate increasing or
				// decreasing
				updateCurrentInput(this.leftNode, this.inputData, false, distance);
				// increase right
				updateCurrentInput(this.rightNode, this.inputData, true, distance);
			} else if (isRightGreaterThanLeft()) {
				// increase left
				updateCurrentInput(this.leftNode, this.inputData, true, distance);
				// decrease right
				updateCurrentInput(this.rightNode, this.inputData, false, distance);
			}
		} else {
			// Generate new data so that left value != right values
			this.distance = getRandomInt();
			// increase left
			updateCurrentInput(this.leftNode, this.inputData, true, distance);
			// decrease left
			updateCurrentInput(this.leftNode, this.inputData, false, distance);
			// increase right
			updateCurrentInput(this.rightNode, this.inputData, true, distance);
			// decrease right
			updateCurrentInput(this.rightNode, this.inputData, false, distance);
		}

	}

	private int distance() {
		return (Integer) leftNode.getVariableValue() - (Integer) rightNode.getVariableValue();
	}

	private boolean isLeftGreaterThanRight() {
		return distance() > 0 ? true : false;
	}

	private boolean isRightGreaterThanLeft() {
		return distance() < 0 ? true : false;
	}

}

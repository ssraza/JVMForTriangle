package com.gannon.rule;

import java.util.ArrayList;
import java.util.HashMap;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.input.Input;
import com.gannon.jvm.input.InputCollection;

public class RuleIFcmpge extends Rule {
	private int distance = 0;
	private boolean expectedPredicateResult;
	
	public RuleIFcmpge(boolean expectedPredicateResult, Input inputData, Dependencies dependecies, BinNode leftNode, BinNode rightNode,
			InputCollection inputs) {
		super(inputData, dependecies, leftNode, rightNode, inputs);
		this.expectedPredicateResult = expectedPredicateResult;
		this.distance = distance();
	} 

	@Override
	public void dataGeneration() {
		// make sure left > right, we can set it as 1. Here we use a random
		// number instead
		int geDistance = Math.abs(this.distance) + getRandomInt();
		if (expectedPredicateResult) {
			// increase left
			updateCurrentInput(this.leftNode, this.inputData, true, geDistance);

			// decrease right
			updateCurrentInput(this.rightNode, this.inputData, false, geDistance);
		} else {
			// decrease left
			updateCurrentInput(this.leftNode, this.inputData, false, geDistance);
			// increase right
			updateCurrentInput(this.rightNode, this.inputData, true, geDistance);
		}

	}

	private int distance() {
		return (Integer) leftNode.getVariableValue() - (Integer) rightNode.getVariableValue();
	}

}

package com.gannon.rule;

import java.util.ArrayList;
import java.util.HashMap;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;

public class RuleIFcmpne extends Rule {
	private int distance = 0;
	boolean expectedPredicateResult;

	public RuleIFcmpne(boolean expectedPredicateResult, InputObject inputData, Dependencies dependecies, BinNode leftNode, BinNode rightNode,
			ArrayList<InputObject> newDataList) {
		super(inputData, dependecies, leftNode, rightNode, newDataList);
		this.expectedPredicateResult = expectedPredicateResult;
		this.distance = distance();
	}

	@Override
	public void dataGeneration() {
		if (expectedPredicateResult) {
			// current is == and we want to make left != right
			// increase left
			updateCurrentInput(this.leftNode, this.inputData, true, getRandomInt());
			// decrease left
			updateCurrentInput(this.leftNode, this.inputData, false, getRandomInt());
			// increase right
			updateCurrentInput(this.rightNode, this.inputData, true, getRandomInt());
			// decrease right
			updateCurrentInput(this.rightNode, this.inputData, false, getRandomInt());
		}
		// ==
		else {
			if (isLeftGreaterThanRight()) {
				// decrease left
				updateCurrentInput(this.leftNode, this.inputData, false, distance);
				// increase right
				updateCurrentInput(this.rightNode, this.inputData, true, distance);
			} else if (isRightGreaterThanLeft()) {
				// increase left
				updateCurrentInput(this.rightNode, this.inputData, false, distance);
				// decrease right
				updateCurrentInput(this.leftNode, this.inputData, true, distance);
			}
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

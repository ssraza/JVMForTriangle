package com.gannon.rule;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.input.Input;
import com.gannon.jvm.data.input.InputCollection;

public class RuleIAdd extends Rule {
	private int distance = 0;
	private boolean increaseFlag;

	public RuleIAdd(boolean increaseFlag, Input inputData, Dependencies dependecies, BinNode leftNode,
			BinNode rightNode, InputCollection inputs, int distance) {
		super(inputData, dependecies, leftNode, rightNode, inputs);
		this.increaseFlag = increaseFlag;
		this.distance = distance;
	}

	// false = decrease, true = increase

	@Override
	public void dataGeneration() {
		if (this.increaseFlag) {
			// increase left
			updateCurrentInput(this.leftNode, this.inputData, true, distance);
			// increase right
			updateCurrentInput(this.rightNode, this.inputData, true, distance);
		} else {
			// decrease left
			updateCurrentInput(this.leftNode, this.inputData, false, distance);
			// decrease right
			updateCurrentInput(this.rightNode, this.inputData, false, distance);
		}
	}
}

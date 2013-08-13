package com.gannon.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.input.Input;
import com.gannon.jvm.data.input.InputCollection;
import com.gannon.jvm.data.input.Parameter;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.utilities.ConstantsUtility;

public abstract class Rule {
	// protected boolean expectedPredicateResult;
	Input inputData;
	Dependencies dependecies;
	BinNode leftNode;
	BinNode rightNode;

	InputCollection inputs = new InputCollection(1);

	public InputCollection getInputCollection() {
		return inputs;
	}

	public Rule(Input inputData, Dependencies dependecies, BinNode leftNode, BinNode rightNode, InputCollection inputs) {
		super();
		// this.expectedPredicateResult = result;
		this.inputData = inputData;
		this.dependecies = dependecies;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		this.inputs = inputs;
	}

	public abstract void dataGeneration();

	/**
	 *
	 * @param input
	 * @param increaseFlag
	 *            true increase, false decrease
	 * @param distance
	 *            TODO
	 * @param Node
	 * @return
	 *
	 *         This is a recursive function
	 */

	protected InputCollection updateCurrentInput(BinNode node, Input input, Boolean increaseFlag, int distance) {
		if (node.isParamter()) {
			String name = node.getVariableName();
			int index = Integer.valueOf(name);

			Parameter p=input.getParameterByIndex(index);
			Input newInput=null;
			if (increaseFlag) {
				newInput=input.updateParameterValue(index, (Integer)p.getValue() + Math.abs(distance));
			} else {
				newInput=input.updateParameterValue(index,  (Integer)p.getValue() - Math.abs(distance));
			}
			inputs.add(newInput);

		} else {
			// recursively find next node
			Dependency d = this.dependecies.findRelation(node);
			BInstruction i = d.getInst();
			if (i instanceof BIAdd) {
				RuleIAdd addObj = new RuleIAdd(increaseFlag, inputData, this.dependecies, d.getLeftNode(),
						d.getRightNode(), this.inputs, Math.abs(distance));
				addObj.dataGeneration();
			}
		}
		return inputs;
	} 
}

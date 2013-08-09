package com.gannon.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BIAdd;

public abstract class Rule {
	//protected boolean expectedPredicateResult;
	InputObject inputData;
	Dependencies dependecies;
	BinNode leftNode;
	BinNode rightNode;

	ArrayList<InputObject> generatedInputs;

	public ArrayList<InputObject> getNewDataList() {
		return generatedInputs;
	}

	public Rule( InputObject inputData, Dependencies dependecies, BinNode leftNode, BinNode rightNode,
			ArrayList<InputObject> newDataList) {
		super();
		//this.expectedPredicateResult = result;
		this.inputData = inputData;
		this.dependecies = dependecies;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		this.generatedInputs = newDataList;
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

	protected ArrayList<InputObject> updateCurrentInput(BinNode node, InputObject input, Boolean increaseFlag,
			int distance) {
		if (node.isParamter()) {
			HashMap<String, Integer> newInputData = new HashMap<String, Integer>();
			Iterator it = this.inputData.getInputDataTable().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				newInputData.put(pairs.getKey().toString(), (Integer) pairs.getValue());
			}
			InputObject newData = new InputObject(newInputData);

			String name = "i" + node.getVariableName();
			int value = 0;
			if (newData.isContainsKey(name)) {
				if (increaseFlag) {
					value = newData.getInputDataByKey(name) + Math.abs(distance);
				} else {
					value = newData.getInputDataByKey(name) - Math.abs(distance);
				}
				newData.setInputDataByKey(name, value);
				generatedInputs.add(newData);
			}
		} else {
			// recursively find next node
			Dependency d = this.dependecies.findRelation(node);
			BInstruction i = d.getInst();
			if (i instanceof BIAdd) {
				RuleIAdd addObj = new RuleIAdd(increaseFlag, input, this.dependecies, d.getLeftNode(),
						d.getRightNode(), this.generatedInputs, Math.abs(distance));
				addObj.dataGeneration();
			}
		}
		return generatedInputs;
	}

	protected int getRandomInt() {
		int i = 0;
		Random rand = new Random();
		i = rand.nextInt(200);
		return i;
	}

}

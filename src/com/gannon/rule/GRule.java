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

public abstract class GRule {
	boolean result;
	int distance;
	InputObject inputData;
	Dependencies dependecies;
	BinNode LeftNode;
	BinNode RightNode;

	ArrayList<InputObject> newDataList;

	public ArrayList<InputObject> getNewDataList() {
		return newDataList;
	}

	public GRule(boolean result, InputObject inputData, Dependencies dependecies, BinNode leftNode, BinNode rightNode,
			ArrayList<InputObject> newDataList) {
		super();
		this.result = result;
		this.inputData = inputData;
		this.dependecies = dependecies;
		LeftNode = leftNode;
		RightNode = rightNode;
		this.newDataList = newDataList;
		this.distance = (Integer) this.LeftNode.getVariableValue() - (Integer) this.RightNode.getVariableValue();
	}

	public abstract void dataGeneration();

	/**
	 * 
	 * @param Node
	 * @param currentInput
	 * @param increaseFlag
	 *            true increase, false decrease
	 * @return
	 */
	
	//__(+)  (add node)
	//_ /\
	// _1 2  (parameter node)
	//

	protected ArrayList<InputObject> ChangeInputData(BinNode node, InputObject currentInput, Boolean increaseFlag) {
		if (!node.isParamter()) {
			Dependency d = this.dependecies.findRelation(node);
			BInstruction i = d.getInst();
			if (i instanceof BIAdd) {
				RuleIAdd addObj = new RuleIAdd(increaseFlag, currentInput, this.dependecies, d.getLeftNode(),
						d.getRightNode(), this.newDataList, Math.abs(this.distance));
				addObj.dataGeneration();
			}
		} else {
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
					value = newData.getInputDataByKey(name) + Math.abs(this.distance);
				} else {
					value = newData.getInputDataByKey(name) - Math.abs(this.distance);
				}
				newData.setInputDataByKey(name, value);
				newDataList.add(newData);
			}

		}
		return newDataList;
	}

	protected int getRandomInt() {
		int i = 0;
		Random rand = new Random();
		i = rand.nextInt(200);
		return i;
	}

}

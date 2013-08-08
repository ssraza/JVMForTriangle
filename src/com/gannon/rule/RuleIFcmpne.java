package com.gannon.rule;

import java.util.ArrayList;
import java.util.HashMap;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;

public class RuleIFcmpne extends Rule{
	
	public RuleIFcmpne(boolean result, InputObject inputData,
			Dependencies dependecies, BinNode leftNode, BinNode rightNode,
			ArrayList<InputObject> newDataList) {
		super(result, inputData, dependecies, leftNode, rightNode, newDataList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dataGeneration() {
		// TODO Auto-generated method stub
		//!=
		if(this.result){
			this.distance = getRandomInt();
			//increase left
			ChangeInputData(this.LeftNode,this.inputData,true);
			//decrease left
			ChangeInputData(this.LeftNode,this.inputData,false);
			//increase right
			ChangeInputData(this.RightNode,this.inputData,true);
			//decrease right
			ChangeInputData(this.RightNode,this.inputData,false);
		}
		//==
		else{
			if(this.distance > 0){
				//decrease left
				ChangeInputData(this.LeftNode,this.inputData,false);
				//increase right
				ChangeInputData(this.RightNode,this.inputData,true);
			}
			else if(this.distance <0){
				//increase left
				ChangeInputData(this.RightNode,this.inputData,false);
				//decrease right
				ChangeInputData(this.LeftNode,this.inputData,true);
			}
		}
	}
}

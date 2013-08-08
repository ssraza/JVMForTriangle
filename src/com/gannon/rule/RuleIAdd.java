package com.gannon.rule;

import java.util.ArrayList;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;

public class RuleIAdd extends Rule{
	
	public RuleIAdd(boolean result, InputObject inputData,
			Dependencies dependecies, BinNode leftNode, BinNode rightNode,
			ArrayList<InputObject> newDataList,int distance) {
		super(result, inputData, dependecies, leftNode, rightNode, newDataList);
		// TODO Auto-generated constructor stub
		this.distance = distance;
	}

	// false = decrease, true = increase

	@Override
	public void dataGeneration() {
		// TODO Auto-generated method stub
		
		if(this.result){
			//increase left
			ChangeInputData(this.LeftNode,this.inputData,true);
			//increase right
			ChangeInputData(this.RightNode,this.inputData,true);
		}
		else{
			
			//decrease left
			ChangeInputData(this.LeftNode,this.inputData,false);
			//decrease right
			ChangeInputData(this.RightNode,this.inputData,false);
		}
		
	}
	

}

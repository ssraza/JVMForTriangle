package com.gannon.rule;

import java.util.ArrayList;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;

public class RuleIAdd extends Rule{
	private int distance = 0;
	private boolean increaseFlag;
	
	public RuleIAdd(boolean increaseFlag, InputObject inputData,
			Dependencies dependecies, BinNode leftNode, BinNode rightNode,
			ArrayList<InputObject> newDataList,int distance) {
		super( inputData, dependecies, leftNode, rightNode, newDataList);
		// TODO Auto-generated constructor stub
		this.increaseFlag = increaseFlag;
		this.distance = distance;
	}

	// false = decrease, true = increase

	@Override
	public void dataGeneration() {
		// TODO Auto-generated method stub
		
		if(this.increaseFlag){
			//increase left
			updateCurrentInput(this.leftNode,this.inputData,true, distance);
			//increase right
			updateCurrentInput(this.rightNode,this.inputData,true, distance);
		}
		else{
			
			//decrease left
			updateCurrentInput(this.leftNode,this.inputData,false, distance);
			//decrease right
			updateCurrentInput(this.rightNode,this.inputData,false, distance);
		}
		
	}
	

}

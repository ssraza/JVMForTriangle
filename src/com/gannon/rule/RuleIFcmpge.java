package com.gannon.rule;

import java.util.ArrayList;
import java.util.HashMap;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;

public class RuleIFcmpge extends Rule{

	


	public RuleIFcmpge(boolean result, InputObject inputData,
			Dependencies dependecies, BinNode leftNode, BinNode rightNode,
			ArrayList<InputObject> newDataList) {
		super(result, inputData, dependecies, leftNode, rightNode, newDataList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dataGeneration() {
		// TODO Auto-generated method stub
		System.out.println("Distance original:"+distance);
		this.distance = Math.abs(this.distance) + getRandomInt();
		System.out.println("Distance:"+distance);
		if(this.result){
			//increase left 
			ChangeInputData(this.LeftNode,this.inputData,true);
			
			//decrease right
			ChangeInputData(this.RightNode,this.inputData,false);
		}
		else{			
			//decrease left
			ChangeInputData(this.LeftNode,this.inputData,false);
			//increase right
			ChangeInputData(this.RightNode,this.inputData,true);
		}
		
	}
	
}

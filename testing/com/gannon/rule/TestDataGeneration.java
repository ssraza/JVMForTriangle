package com.gannon.rule;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.GannonPredicateTreeBuilderJVM;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;

public class TestDataGeneration {

	@Test
	public void test() {
		GannonPredicateTreeBuilderJVM jvm = new GannonPredicateTreeBuilderJVM();
		jvm.run(TrianglePathBuilderUtility.createPathID1());
		Dependencies dependencies = jvm.getRelationFrame().getRelations();
		
		HashMap<String, Integer> inputTable = new HashMap<String, Integer>();
		inputTable.put("i1", 4);
		inputTable.put("i2", 8);
		inputTable.put("i3", 10);
		
		BinNode leftNode = new BinNode("1");
		leftNode.setVariableValue(4);
		BinNode rightNode = new BinNode("1000");
		rightNode.setVariableValue(18);
		
		InputObject input = new InputObject(inputTable);
		ArrayList<InputObject> newDataList = new ArrayList<InputObject>();
		
		System.out.println("----Input Data:----");
		input.printData();
		System.out.println("cmpge");
		Rule rule = new RuleIFcmpge(true, input, dependencies, leftNode, rightNode, newDataList);
		rule.dataGeneration();
		
		ArrayList<InputObject> result = rule.getNewDataList();
		System.out.println("----New Data------");
		for(int i=0;i<result.size();i++){
			InputObject obj = result.get(i);
			obj.printData();
		}
		
	}
	
}

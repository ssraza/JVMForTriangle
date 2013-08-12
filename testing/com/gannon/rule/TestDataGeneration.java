package com.gannon.rule;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.GannonPredicateTreeBuilderJVM;
import com.gannon.jvm.input.Input;
import com.gannon.jvm.input.InputCollection;
import com.gannon.jvm.input.Parameter;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;

public class TestDataGeneration {

	@Test
	public void testCmpge() {
		GannonPredicateTreeBuilderJVM jvm = new GannonPredicateTreeBuilderJVM();
		jvm.run(TrianglePathBuilderUtility.createPathID1());
		Dependencies dependencies = jvm.getRelationFrame().getRelations();
		
//		HashMap<String, Integer> inputTable = new HashMap<String, Integer>();
//		inputTable.put("i1", 4);
//		inputTable.put("i2", 8);
//		inputTable.put("i3", 10);
//		
		BinNode leftNode = new BinNode("1");
		leftNode.setVariableValue(4);
		BinNode rightNode = new BinNode("1000");
		rightNode.setVariableValue(18);
//		
//		InputObject input1 = new InputObject(inputTable);
//		ArrayList<InputObject> newDataList1 = new ArrayList<InputObject>();
//		
//		System.out.println("----Input Data:----");
//		input1.printData();
		System.out.println("cmpge");
		
		Input input = new Input(0);
		Parameter p1 = new Parameter(1);
		p1.setValue(4);
		
		Parameter p2 = new Parameter(2);
		p2.setValue(8);
		
		Parameter p3 = new Parameter(3);
		p3.setValue(10);
		input.add(p1);
		input.add(p2);
		input.add(p3);
		
		InputCollection newDataList = new InputCollection(1);
		Rule rule = new RuleIFcmpge(true, input, dependencies, leftNode, rightNode, newDataList);
		rule.dataGeneration();
		
		InputCollection result = rule.getInputCollection();
		System.out.println("----New Data------");
		for(int i=0;i<result.getInputs().size();i++){
			Input obj = result.getInputs().get(i);
			System.out.println(obj);
		}
		
	}
	
//	public void testCmpne() {
//		GannonPredicateTreeBuilderJVM jvm = new GannonPredicateTreeBuilderJVM();
//		jvm.run(TrianglePathBuilderUtility.createPathID1());
//		Dependencies dependencies = jvm.getRelationFrame().getRelations();
//		
//		HashMap<String, Integer> inputTable = new HashMap<String, Integer>();
//		inputTable.put("i1", 4);
//		inputTable.put("i2", 8);
//		inputTable.put("i3", 10);
//		
//		BinNode leftNode = new BinNode("1");
//		leftNode.setVariableValue(4);
//		BinNode rightNode = new BinNode("2");
//		rightNode.setVariableValue(18);
//		
//		InputObject input = new InputObject(inputTable);
//		ArrayList<InputObject> newDataList = new ArrayList<InputObject>();
//		
//		System.out.println("----Input Data:----");
//		input.printData();
//		System.out.println("cmpne");
//		Rule rule = new RuleIFcmpne(false, input, dependencies, leftNode, rightNode, newDataList);
//		rule.dataGeneration();
//		
//		ArrayList<InputObject> result = rule.getNewDataList();
//		System.out.println("----New Data------");
//		for(int i=0;i<result.size();i++){
//			InputObject obj = result.get(i);
//			obj.printData();
//		}
//		
//	}
	
}

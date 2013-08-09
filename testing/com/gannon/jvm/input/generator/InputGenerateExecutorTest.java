package com.gannon.jvm.input.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.input.generator.InputGenerateExecutor;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;
import com.gannon.rule.InputObject;

public class InputGenerateExecutorTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	@Test
	public void testPathID1() {
		
		TestPath testPath = TrianglePathBuilderUtility.createPathID1();
		ArrayList<Object> inputs = InputObjectToArrayList(randomGeneration(3));
		System.out.println(inputs.size());
		BLocalVarTable localVariableTable = new BLocalVarTable(inputs);
		PathFrame pathFrame = new PathFrame(testPath,localVariableTable);
		
		InputGenerateExecutor executor= new InputGenerateExecutor(inputs);
		Object result = executor.execute(pathFrame);
	}

	private static InputObject randomGeneration(int numOfParam){
		System.out.println("randomGeneration:");
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		Random rand = new Random();
//		for(int i=1;i<=numOfParam;i++){
//			int value = rand.nextInt(200);
//			String name= "i"+i;
//			map.put(name, value);
//			System.out.print(name+":"+value+" ");
//			
//		}
		map.put("i1", 12361110);
		map.put("i2",12344969);
		map.put("i3", 124456);
		InputObject newObj = new InputObject(map);
		System.out.println();
		return newObj;
	}
	
	private static ArrayList<Object> InputObjectToArrayList(InputObject obj){
		HashMap<String,Integer> map = obj.getInputDataTable();
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);
		input.add(-1);
		input.add(-1);
		input.add(-1);
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			String name = pairs.getKey().toString();
			int index = Integer.parseInt(name.substring(1));
			input.set(index, pairs.getValue());
		}
		return input;
	}
	
}

package com.gannon.jvm.execution.path;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;
import com.gannon.rule.InputObject;

public class PathExecutorTest {

	@Test
	public static void main(String args[]) {
		
		TestPath testPath = TrianglePathBuilderUtility.createPathID3();
		ArrayList<Object> inputs = InputObjectToArrayList(randomGeneration(3));
		System.out.println(inputs.size());
		BLocalVarTable localVariableTable = new BLocalVarTable(inputs);
		PathFrame pathFrame = new PathFrame(testPath,localVariableTable);
		
		DatageneratePathExecutor executor= new DatageneratePathExecutor(inputs);
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
		map.put("i1", 12360000);
		map.put("i2",123459630);
		map.put("i3", 12456);
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

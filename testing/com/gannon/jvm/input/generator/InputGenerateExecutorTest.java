package com.gannon.jvm.input.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.jvm.data.input.Input;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;

public class InputGenerateExecutorTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	private static ArrayList<Object> setInput(int numOfParam){
		ArrayList<Object> inputs = new ArrayList<Object>();
		inputs.add(-1);
		inputs.add(111);
		inputs.add(6565);
		inputs.add(667);
		return inputs;
	}

	@Test
	public void testPathID1UsingInputObj() {

		TestPath testPath = TrianglePathBuilderUtility.createPathID1();
		Input inputs=new Input(1,setInput(3));

		BLocalVarTable localVariableTable = new BLocalVarTable(inputs);
		InputGenerationFrame pathFrame = new InputGenerationFrame(testPath,localVariableTable, 5);

		InputGenerateExecutor executor= new InputGenerateExecutor(inputs);
		Object result = executor.execute(pathFrame);
	}


}

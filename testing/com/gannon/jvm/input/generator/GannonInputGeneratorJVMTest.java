package com.gannon.jvm.input.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.input.Input;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;

public class GannonInputGeneratorJVMTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	private static ArrayList<Object> hardCodeInput(){
		ArrayList<Object> inputs = new ArrayList<Object>();
		inputs.add(-1);
		inputs.add(111);
		inputs.add(6565);
		inputs.add(667);
		return inputs;
	}

	@Test
	public void testPathID_1UsingInputObj() {

		TestPath testPath = TrianglePathBuilderUtility.createPathID1();
		Input input=new Input(1,hardCodeInput());

		//path information needed are saved in test path, bClass, bMethod
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod bMethod = myclass.getMethod("triangleType");
		testPath.setbMethod(bMethod);
		testPath.setbClass(myclass);

		GannonInputGeneratorJVM executor= new GannonInputGeneratorJVM();
		Set<Input> results= executor.run(testPath, input,100);
		//System.out.print(resutls);
		assertEquals(100,results.size());
		for(Input element:results){
			assertEquals(((Input)element).getParameterByIndex(1).getValue(),((Input)element).getParameterByIndex(2).getValue());
			assertEquals(((Input)element).getParameterByIndex(1).getValue(),((Input)element).getParameterByIndex(3).getValue());
		}
	}

	@Test
	public void testPathID_3() {

		TestPath testPath = TrianglePathBuilderUtility.createPathID3();
		Input input=new Input(1,hardCodeInput());

		//path information needed are saved in test path, bClass, bMethod
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod bMethod = myclass.getMethod("triangleType");
		testPath.setbMethod(bMethod);
		testPath.setbClass(myclass);

		GannonInputGeneratorJVM executor= new GannonInputGeneratorJVM();
		int numberOfInputsNeeded=100;
		Set<Input> results= executor.run(testPath, input,numberOfInputsNeeded);
		//System.out.print(results);
		assertEquals(numberOfInputsNeeded,results.size());
		for(Input element:results){
			assertEquals(((Input)element).getParameterByIndex(1).getValue(),((Input)element).getParameterByIndex(3).getValue());
		}
	}

	@Test
	public void testPathID_4() {
		TestPath testPath = TrianglePathBuilderUtility.createPathID4();
		Input input=new Input(1,hardCodeInput());

		//path information needed are saved in test path, bClass, bMethod
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod bMethod = myclass.getMethod("triangleType");
		testPath.setbMethod(bMethod);
		testPath.setbClass(myclass);

		GannonInputGeneratorJVM executor= new GannonInputGeneratorJVM();
		int numberOfInputsNeeded=100;
		Set<Input> results= executor.run(testPath, input,numberOfInputsNeeded);
		//System.out.print(results);
		assertEquals(numberOfInputsNeeded,results.size());
		for(Input element:results){
			assertEquals(((Input)element).getParameterByIndex(2).getValue(),((Input)element).getParameterByIndex(3).getValue());
		}
	}

	@Test
	public void testPathID_5() {
		TestPath testPath = TrianglePathBuilderUtility.createPathID5();
		Input input=new Input(1,hardCodeInput());

		//path information needed are saved in test path, bClass, bMethod
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod bMethod = myclass.getMethod("triangleType");
		testPath.setbMethod(bMethod);
		testPath.setbClass(myclass);

		GannonInputGeneratorJVM executor= new GannonInputGeneratorJVM();
		int numberOfInputsNeeded=100;
		Set<Input> results= executor.run(testPath, input,numberOfInputsNeeded);
		//System.out.print(results);
		assertEquals(numberOfInputsNeeded,results.size());
		for(Input element:results){
			assertEquals(((Input)element).getParameterByIndex(2).getValue(),((Input)element).getParameterByIndex(1).getValue());
		}
	}
	
	@Test
	public void testPathID_8() {
		TestPath testPath = TrianglePathBuilderUtility.createPathID8();
		Input input=new Input(1,hardCodeInput());

		//path information needed are saved in test path, bClass, bMethod
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod bMethod = myclass.getMethod("triangleType");
		testPath.setbMethod(bMethod);
		testPath.setbClass(myclass);

		GannonInputGeneratorJVM executor= new GannonInputGeneratorJVM();
		int numberOfInputsNeeded=100;
		Set<Input> results= executor.run(testPath, input,numberOfInputsNeeded);
		//System.out.print(results);
		assertEquals(numberOfInputsNeeded,results.size());
		for(Input element:results){
			Integer v1=(Integer) ((Input)element).getParameterByIndex(1).getValue();
			Integer v2=(Integer) ((Input)element).getParameterByIndex(2).getValue();
			Integer v3=(Integer) ((Input)element).getParameterByIndex(3).getValue();
			assertTrue(v1+v2>v3);
			assertTrue(v1+v3>v2);
			assertTrue(v3+v2>v1);
			
		}
	}
	
	@Test
	public void testPathID_10() {
		TestPath testPath = TrianglePathBuilderUtility.createPathID10();
		Input input=new Input(1,hardCodeInput());

		//path information needed are saved in test path, bClass, bMethod
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod bMethod = myclass.getMethod("triangleType");
		testPath.setbMethod(bMethod);
		testPath.setbClass(myclass);

		GannonInputGeneratorJVM executor= new GannonInputGeneratorJVM();
		int numberOfInputsNeeded=100;
		Set<Input> results= executor.run(testPath, input,numberOfInputsNeeded);
		System.out.print(results);
		assertEquals(numberOfInputsNeeded,results.size());
		for(Input element:results){
			Integer v1=(Integer) ((Input)element).getParameterByIndex(1).getValue();
			Integer v2=(Integer) ((Input)element).getParameterByIndex(2).getValue();
			Integer v3=(Integer) ((Input)element).getParameterByIndex(3).getValue();
			assertTrue(v1>=v3+v2);
		}
	}

}

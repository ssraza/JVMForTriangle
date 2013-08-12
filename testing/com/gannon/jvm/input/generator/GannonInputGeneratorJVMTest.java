package com.gannon.jvm.input.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	public void testPathID1UsingInputObj() {

		TestPath testPath = TrianglePathBuilderUtility.createPathID1();
		Input input=new Input(1,hardCodeInput());

		//path information needed are saved in test path, bClass, bMethod
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod bMethod = myclass.getMethod("triangleType");
		testPath.setbMethod(bMethod);
		testPath.setbClass(myclass);

		GannonInputGeneratorJVM executor= new GannonInputGeneratorJVM();
		Object result = executor.run(testPath, input,10);
	}


}

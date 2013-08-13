package com.gannon.jvm.data.input;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.jvm.data.input.Input;
import com.gannon.jvm.data.input.Parameter;

public class InputTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	@Test
	public void test() {
		// create an input
		Parameter p1 = new Parameter(1, 3);
		Parameter p2 = new Parameter(2, 4);
		Input input = new Input(1);
		input.add(p1);
		input.add(p2);
		
		Input newInput=input.updateParameterValue(1, 50);
		assertFalse(input.getParameterByIndex(1).getValue()==newInput.getParameterByIndex(1).getValue());
	}


	@Test
	public void testEquals() {
		// create an input
		Parameter p1 = new Parameter(1, 3);
		Parameter p2 = new Parameter(2, 4);
		Input input = new Input(1);
		input.add(p1);
		input.add(p2);
		
		
		// create an input
		Input input2 = new Input(1);
		input2.add(p1);
		input2.add(p2);
		
		assertEquals(input,input2);
		
	}
	
	
	@Test
	public void testEquals2() {
		// create an input
		Parameter p1 = new Parameter(1, 3);
		Parameter p2 = new Parameter(2, 4);
		Input input = new Input(1);
		input.add(p1);
		input.add(p2);
		
		
		// create an input
		Input input2 = new Input(1);
		Parameter p12 = new Parameter(1, 3);
		Parameter p22 = new Parameter(3, 4);
		input.add(p12);
		input.add(p22);
		
		assertNotEquals(input,input2);
		
	}
}

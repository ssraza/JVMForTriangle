package com.gannon.jvm.input.generator;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.jvm.input.Input;
import com.gannon.jvm.input.Parameter;

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

}

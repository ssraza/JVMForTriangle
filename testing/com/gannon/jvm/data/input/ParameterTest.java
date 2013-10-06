package com.gannon.jvm.data.input;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class ParameterTest {
	
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	@Test
	public void testEqual() {
		Parameter p1 = new Parameter(1, 3);
		Parameter p2 = new Parameter(1, 3);
		
		assertEquals(p1,p2);
	}
	
	@Test
	public void testNotEqual() {
		Parameter p1 = new Parameter(1, 3);
		Parameter p2 = new Parameter(2, 3);
		
		assertNotEquals(p1,p2);
	}

}

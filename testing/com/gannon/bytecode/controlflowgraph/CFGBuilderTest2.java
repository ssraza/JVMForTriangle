package com.gannon.bytecode.controlflowgraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;

public class CFGBuilderTest2 {
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	@Test
	public void testComputeLeadingLineFlags() {
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		assertEquals("triangleType", m.getName());

		CFGBuilder cfg = new CFGBuilder(m);
		boolean[] actualFlags = cfg.computeLeadingLineFlags();
		boolean[] expectedFlags = new boolean[] { false, false, false, false,
				false, true, false, false, false, false, true, false, false,
				false, false, true, false, false, true, false, false, true,
				false, true, false, false, true, false, false, true, false,
				false, true, false, true, false, true, false, true, };
		assertTrue(Arrays.equals(expectedFlags,actualFlags));
	}

}

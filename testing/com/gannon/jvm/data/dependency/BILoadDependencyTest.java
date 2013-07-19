package com.gannon.jvm.data.dependency;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.JVMStackSingleton;
import com.gannon.jvm.instructions.BILoad;

public class BILoadDependencyTest {

	@Test
	public void testDependency() {
		
		RelationCollector dependency = new RelationCollector();
		BILoad iLoad = new BILoad(4);
		iLoad.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("4");
		assertEquals(expectedStack, resultStack);
	}

}

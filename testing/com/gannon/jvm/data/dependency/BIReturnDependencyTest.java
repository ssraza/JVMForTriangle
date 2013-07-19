package com.gannon.jvm.data.dependency;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.instructions.BBipush;

public class BIReturnDependencyTest {

	@Test
	public void testDependency() {
		RelationCollector dependency = new RelationCollector();
		dependency.getTempVariableStack().push("6");
		BBipush bipush = new BBipush(10);
		bipush.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		String result = resultStack.peek();
		assertEquals(null, result);
	}

}

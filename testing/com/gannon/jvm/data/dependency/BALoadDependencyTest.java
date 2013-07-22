package com.gannon.jvm.data.dependency;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.instructions.BALoad;

public class BALoadDependencyTest {

	@Test
	public void testDependency() {
		RelationFrame dependency = new RelationFrame();
		BALoad aLoad = new BALoad(4,0);
		aLoad.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("4");
		assertEquals(expectedStack, resultStack);
	}

}

package com.gannon.jvm.data.dependency;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.instructions.BBipush;

public class BBiPushDependencyTest {

	@Test
	public void testDependency() {
		RelationFrame dependency=new RelationFrame("com.gannon.asm.classgenerator.Triangle", "triangleType");
		BBipush bipush = new BBipush(10,0);
		bipush.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("10");
		assertEquals(expectedStack, resultStack);
	}

}

package com.gannon.jvm.data.dependency;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.instructions.BILoad;

public class BILoadDependencyTest {

	@Test
	public void testDependency() {
		
		RelationFrame dependency=new RelationFrame("com.gannon.asm.classgenerator.Triangle", "triangleType");
		BILoad iLoad = new BILoad(4,0); 
		iLoad.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("4");
		assertEquals(expectedStack, resultStack);
	}

}

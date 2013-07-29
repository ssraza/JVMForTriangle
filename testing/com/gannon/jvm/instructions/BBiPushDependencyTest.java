package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.progam.path.TestPath;

public class BBiPushDependencyTest {

	@Test
	public void testDependency() {
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		BBipush bipush = new BBipush(10,0);
		bipush.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("10");
		assertEquals(expectedStack, resultStack);
	}

}

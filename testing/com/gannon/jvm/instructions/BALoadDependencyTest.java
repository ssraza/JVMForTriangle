package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.progam.path.TestPath;

public class BALoadDependencyTest {

	@Test
	public void testDependency() {
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		BALoad aLoad = new BALoad(4,0);
		aLoad.analyzing(dependency);
		Stack<String> resultStack = dependency.getIntermediateVariableNameStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("4");
		assertEquals(expectedStack, resultStack);
	}

}

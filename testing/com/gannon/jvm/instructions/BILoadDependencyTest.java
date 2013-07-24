package com.gannon.jvm.instructions;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.instructions.BILoad;
import com.gannon.jvm.progam.path.TestPath;

public class BILoadDependencyTest {

	@Test
	public void testDependency() {
		
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		BILoad iLoad = new BILoad(4,0); 
		iLoad.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("4");
		assertEquals(expectedStack, resultStack);
	}

}

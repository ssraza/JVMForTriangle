package com.gannon.jvm.instructions;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.instructions.BBipush;
import com.gannon.jvm.progam.path.TestPath;

public class BReturnDependencyTest {

	@Test
	public void testDependency() {
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		dependency.getTempVariableStack().push("6");
		BReturn retrn = new BReturn(10);
		retrn.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		//String result = resultStack.peek();
		assertEquals(new Stack<>(), resultStack);
	}

}

package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.progam.path.TestPath;

public class BIConstDependencyTest {

	@Test
	public void testDependencyIConst_1() {
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		BIConst_1 iConst1 = new BIConst_1(0);
		iConst1.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("1");
		assertEquals(expectedStack, resultStack);
	}
	
	@Test
	public void testDependencyIConst_2() {
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		BIConst_2 iConst2 = new BIConst_2(0);
		iConst2.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("2");
		assertEquals(expectedStack, resultStack);
	}
	
	@Test
	public void testDependencyIConst_3() {
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		BIConst_3 iConst3 = new BIConst_3(0);
		iConst3.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("3");
		assertEquals(expectedStack, resultStack);
	}
	
	@Test
	public void testDependencyIConst_4() {
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		BIConst_4 iConst4 = new BIConst_4(0);
		iConst4.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("4");
		assertEquals(expectedStack, resultStack);
	}
	
	@Test
	public void testDependencyIConst_5() {
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		BIConst_5 iConst5 = new BIConst_5(0);
		iConst5.analyzing(dependency);
		Stack<String> resultStack = dependency.getTempVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("5");
		assertEquals(expectedStack, resultStack);
	}
}

package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.progam.path.TestPath;

public class BIAddDependencyTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	@Test
	public void testDependcy() {
		Stack<String> operandStack = new Stack<String>();
		operandStack.add("5");
		operandStack.add("9");

		DependencyFrame dependency = new DependencyFrame();
		dependency.setTempVariableStack(operandStack);
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();

		BIAdd iadd = new BIAdd(2);
		iadd.analyzing(dependency);
		Dependency actualTree = dependency.getRelations().getRelation(4);
		//for testing only:void auto-generated ID
		actualTree.getTheBTRootNode().setLocalVariableName("999999");
		//actualTree.niceDisplay();

		BinNode rightNode = new BinNode("9");
		BinNode leftNode = new BinNode("5");
		BinNode rootNode = new BinNode("999999");
		Dependency expectedTree = new Dependency(rootNode, iadd);
		expectedTree.insertToLeft(leftNode);
		expectedTree.insertToRight(rightNode);
		//expectedTree.niceDisplay();

		assertEquals(expectedTree, actualTree);
	}

	@Test
	public void testDependcyLevel2() {
		// set a stack
		Stack<String> operandStack = new Stack<String>();
		operandStack.add("1");
		operandStack.add("101");

		// set a frame
		DependencyFrame dependency = new DependencyFrame();
		dependency.setTempVariableStack(operandStack);
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();

		// a new relation
		BinNode rightNode1 = new BinNode("1");
		BinNode leftNode1 = new BinNode("2");
		BinNode rootNode1 = new BinNode("101");
		Dependency tree9 = new Dependency(rootNode1, new BILoad(2, 5));
		tree9.insertToLeft(leftNode1);
		tree9.insertToRight(rightNode1);
		dependency.getRelations().add(tree9);

		// execution here
		BIAdd iadd = new BIAdd(2);
		iadd.analyzing(dependency);
		// 0: not used, 1-3:inputs, 4: tree9; 5: contains actual results which combines two relations
		Dependency actualTree = dependency.getRelations().getRelation(5);
		//for testing only:void auto-generated ID
		actualTree.getTheBTRootNode().setLocalVariableName("999999");
		
		//
		actualTree.niceDisplay();

		BinNode rightNode = new BinNode("101");
		BinNode leftNode = new BinNode("1");
		BinNode rootNode = new BinNode("100");
		Dependency expectedTree = new Dependency(rootNode, iadd);
		expectedTree.insertToLeft(leftNode);
		expectedTree.insertToRight(rightNode);
		// expectedTree.niceDisplay();

		// assertEquals(expectedTree, actualTree);
	}



}

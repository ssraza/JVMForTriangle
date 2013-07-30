/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.asm.components.BLocalVariable;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;

public class BIAddTest {

	public BIAddTest() {
	}
	
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	/**
	 * Test of execute method, of class BIAdd.
	 */
	@Test
	public void testExecute() {
		System.out.println("execute");
		// init local Variable table
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(5);
		operandStack.add(9);

		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		BIAdd bIAdd = new BIAdd(5);

		// Before calling the execute method,  LocalVariableTable will have 7 on its 0th position.
		// operand stack will have 5 and 9,where 9 is top of the stack.
		// Expectation is, operand stack should load he summation of two values on top of the stack,
		// Should be copied to TOP of the stack of operand. the later two values must be removed from the operand stack

		bIAdd.execute(activeFrame);

		Stack<Integer> afterExe = activeFrame.getOperandStack();

		Stack<Integer> expectedOperandStack = new Stack<Integer>();
		expectedOperandStack.push(new Integer(14));
		assertEquals(expectedOperandStack, afterExe);
	}

	/**
	 * Test of getOpcode method, of class BIAdd.
	 */
	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BIAdd instance = new BIAdd(4);
		int expResult = 96;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getOpcodeCommand method, of class BIAdd.
	 */
	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BIAdd instance = new BIAdd(3);
		String expResult = "iadd";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}
	


	@Test
	public void testDependcy() {
		Stack<String> operandStack = new Stack<String>();
		operandStack.add("5");
		operandStack.add("9");

		DependencyFrame dependency = new DependencyFrame();
		dependency.setTempVariableStack(operandStack);
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		
		BLocalVariable localvar1 = new BLocalVariable("i", "I", null, 0); 
		method.addLocalVariableTable(localvar1);
		BLocalVariable localvar2 = new BLocalVariable("j", "I", null, 1); 
		method.addLocalVariableTable(localvar2);
		BLocalVariable localvar3 = new BLocalVariable("k", "I", null, 2); 
		method.addLocalVariableTable(localvar3);
		BLocalVariable localvar4 = new BLocalVariable("l", "I", null, 3); 
		method.addLocalVariableTable(localvar4);
		BLocalVariable localvar5 = new BLocalVariable("m", "I", null, 4); 
		method.addLocalVariableTable(localvar5);
		BLocalVariable localvar6 = new BLocalVariable("n", "I", null, 5); 
		method.addLocalVariableTable(localvar6);
		
		
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();

		BIAdd iadd = new BIAdd(2);
		iadd.analyzing(dependency);
		Dependency actualTree = dependency.getRelations().getRelation(7);
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
		
		BLocalVariable localvar1 = new BLocalVariable("i", "I", null, 0); 
		method.addLocalVariableTable(localvar1);
		BLocalVariable localvar2 = new BLocalVariable("j", "I", null, 1); 
		method.addLocalVariableTable(localvar2);
		BLocalVariable localvar3 = new BLocalVariable("k", "I", null, 2); 
		method.addLocalVariableTable(localvar3);
		BLocalVariable localvar4 = new BLocalVariable("l", "I", null, 3); 
		method.addLocalVariableTable(localvar4);
		BLocalVariable localvar5 = new BLocalVariable("m", "I", null, 4); 
		method.addLocalVariableTable(localvar5);
		BLocalVariable localvar6 = new BLocalVariable("n", "I", null, 5); 
		method.addLocalVariableTable(localvar6);
		
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
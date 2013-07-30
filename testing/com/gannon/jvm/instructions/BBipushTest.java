package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;

public class BBipushTest {

	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BBipush instance = new BBipush(10,16);
		int expResult = 16;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BBipush instance = new BBipush(10,10);
		
		String expResult = "bipush";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFramePosition1() {
		System.out.println("execute");
		BBipush biPush = new BBipush(16,10);
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(10); // add value 10 to index 0 of operand stack

		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		
		// Before calling the execute method,  operand stack will have 10 on its 0th position.
		// Expectation is, BBipush(16) should load 16 on the top of the operand stack. i.e. on 1st position in this case
		
		biPush.execute(activeFrame);
		
		Stack<Integer> result = activeFrame.getOperandStack();
		Stack<Integer> expectedResult = new Stack<Integer>();
		
		expectedResult.push(10);
		expectedResult.push(16);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void testExecuteBFramePosition0() {
		System.out.println("execute");
		BBipush biPush = new BBipush(8,10);
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();

		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		
		// Before calling the execute method,  operand stack and localvariable table are empty. 
		// Expectation is, BBipush(8) should load 16 on the top of the operand stack. i.e. on 0th position in this case
		
		biPush.execute(activeFrame);
		
		Stack<Integer> result = activeFrame.getOperandStack();
		Stack<Integer> expectedResult = new Stack<Integer>();
		
		//expectedResult.push(10);
		expectedResult.push(8);
		assertEquals(expectedResult, result);
	}
	

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
		Stack<String> resultStack = dependency.getIntermediateVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("10");
		assertEquals(expectedStack, resultStack);
	}

}

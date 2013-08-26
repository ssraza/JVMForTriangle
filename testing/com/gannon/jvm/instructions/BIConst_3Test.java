package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;

public class BIConst_3Test {

	@Test
	public void testExecute() {
		BIConst_3 iconst3 = new BIConst_3(5);
		Stack<Integer> operandStack = new  Stack<Integer>();
		BLocalVarTable varTable = new BLocalVarTable();
		BFrame activeFrame = new BFrame(operandStack, varTable, 0);
		
		// Before calling the execute method,  LocalVariableTable and operand stack will be empty
		// Expectation is, BIConst_3 will load 3 on top of the operand stack,
		
		iconst3.execute(activeFrame);
		
		Stack<Integer> resultOprndStack = new  Stack<Integer>();
		resultOprndStack = activeFrame.getOperandStack();
		
		Stack<Integer> expectedOprndStack = new  Stack<Integer>();
		expectedOprndStack.push(3);
		
		assertEquals(expectedOprndStack, resultOprndStack);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BIConst_3 instance = new BIConst_3(4);

		String expResult = "iconst_3";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFrame() {
		System.out.println("getOpcode");
		BIConst_3 instance = new BIConst_3(6);
		int expResult = 6;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
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
		Stack<String> resultStack = dependency.getIntermediateVariableNameStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("3");
		assertEquals(expectedStack, resultStack);
	}

}

package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;

public class BIConst_5Test {

	@Test
	public void testExecute() {
		BIConst_5 iconst5 = new BIConst_5(7);
		Stack<Integer> operandStack = new  Stack<Integer>();
		BLocalVarTable varTable = new BLocalVarTable();
		BFrame activeFrame = new BFrame(operandStack, varTable, 0);
		
		// Before calling the execute method,  LocalVariableTable and operand stack will be empty
		// Expectation is, BIConst_5 will load 5 on top of the operand stack,
		
		iconst5.execute(activeFrame);
		
		Stack<Integer> resultOprndStack = new  Stack<Integer>();
		resultOprndStack = activeFrame.getOperandStack();
		
		Stack<Integer> expectedOprndStack = new  Stack<Integer>();
		expectedOprndStack.push(5);
		
		assertEquals(expectedOprndStack, resultOprndStack);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BIConst_5 instance = new BIConst_5(8);

		String expResult = "iconst_5";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFrame() {
		System.out.println("getOpcode");
		BIConst_5 instance = new BIConst_5(9);
		int expResult = 8;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
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
		Stack<String> resultStack = dependency.getIntermediateVariableNameStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("5");
		assertEquals(expectedStack, resultStack);
	}

}

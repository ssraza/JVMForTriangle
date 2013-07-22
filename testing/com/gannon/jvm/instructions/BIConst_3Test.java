package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.execution.BFrame;
import com.gannon.jvm.execution.BLocalVarTable;
import com.gannon.jvm.instructions.BIConst_3;

public class BIConst_3Test {

	@Test
	public void testExecute() {
		BIConst_3 iconst3 = new BIConst_3(5);
		Stack<Integer> operandStack = new  Stack<Integer>();
		BLocalVarTable varTable = new BLocalVarTable();
		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		
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

}

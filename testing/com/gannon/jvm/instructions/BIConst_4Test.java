package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.execution.BFrame;
import com.gannon.jvm.execution.BLocalVarTable;
import com.gannon.jvm.instructions.BIConst_4;

public class BIConst_4Test {

	@Test
	public void testExecute() {
		BIConst_4 iconst4 = new BIConst_4(6);
		Stack<Integer> operandStack = new  Stack<Integer>();
		BLocalVarTable varTable = new BLocalVarTable();
		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		
		// Before calling the execute method,  LocalVariableTable and operand stack will be empty
		// Expectation is, BIConst_4 will load 4 on top of the operand stack,
		
		iconst4.execute(activeFrame);
		
		Stack<Integer> resultOprndStack = new  Stack<Integer>();
		resultOprndStack = activeFrame.getOperandStack();
		
		Stack<Integer> expectedOprndStack = new  Stack<Integer>();
		expectedOprndStack.push(4);
		
		assertEquals(expectedOprndStack, resultOprndStack);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BIConst_4 instance = new BIConst_4(7);

		String expResult = "iconst_4";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFrame() {
		System.out.println("getOpcode");
		BIConst_4 instance = new BIConst_4(4);
		int expResult = 7;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

}

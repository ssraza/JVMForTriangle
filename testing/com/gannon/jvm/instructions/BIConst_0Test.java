package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.execution.BFrame;
import com.gannon.jvm.execution.BLocalVarTable;
import com.gannon.jvm.instructions.BIConst_0;

public class BIConst_0Test {

	@Test
	public void testExecute() {
		BIConst_0 iconst0 = new BIConst_0(5);
		Stack<Integer> operandStack = new  Stack<Integer>();
		BLocalVarTable varTable = new BLocalVarTable();
		BFrame activeFrame = new BFrame(0, varTable, operandStack);

		// Before calling the execute method,  LocalVariableTable and operand stack will be empty
		// Expectation is, BIConst_0 will load 0 on top of the operand stack,

		iconst0.execute(activeFrame);

		Stack<Integer> resultOprndStack = new  Stack<Integer>();
		resultOprndStack = activeFrame.getOperandStack();

		Stack<Integer> expectedOprndStack = new  Stack<Integer>();
		expectedOprndStack.push(0);

		assertEquals(expectedOprndStack, resultOprndStack);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BIConst_0 instance = new BIConst_0(7);

		String expResult = "iconst_0";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFrame() {
		System.out.println("getOpcode");
		BIConst_0 instance = new BIConst_0(6);
		int expResult = 3;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

}

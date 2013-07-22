package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.execution.BFrame;
import com.gannon.jvm.execution.BLocalVarTable;
import com.gannon.jvm.instructions.BAStore;
import com.gannon.jvm.instructions.BILoad;

public class BILoadTest {

	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BAStore instance = new BAStore(1,10);
		int expResult = 58;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BAStore instance = new BAStore(1,12);

		String expResult = "astore";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFramePostion2() {
		System.out.println("execute");
		BILoad bILoad = new BILoad(2,5);// Initialize BILoad, pass 0 as operand value
		// init local Variable table
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(10); // add value 10 to index 0 of operand stack
		operandStack.add(1);
		operandStack.add(9);
		varTable.add(7);
		varTable.add(2);
		varTable.add(5);

		BFrame activeFrame = new BFrame(0, varTable, operandStack);

		// Before calling the execute method,  LocalVariableTable will have 7 on its 0th position, 2 on 1st and 5 on 2nd.
		// Expectation is, BILoad(2) should load what ever is there on 2nd position of local variable table,
		// to the TOP of the stack of operand.

		bILoad.execute(activeFrame);

		Stack<Object> resultOperandStack = activeFrame.getOperandStack();

		Object result = resultOperandStack.lastElement();

		assertEquals(result, new Integer(5));
	}

	@Test
	public void testExecuteBFramePostion1() {
		System.out.println("execute");
		BILoad bILoad = new BILoad(1,5);// Initialize BILoad, pass 0 as operand value
		// init local Variable table
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(10); // add value 10 to index 0 of operand stack
		operandStack.add(1);
		operandStack.add(9);
		varTable.add(7);
		varTable.add(2);
		varTable.add(5);

		BFrame activeFrame = new BFrame(0, varTable, operandStack);

		// Before calling the execute method,  LocalVariableTable will have 7 on its 0th position, 2 on 1st and 5 on 2nd.
		// Expectation is, BILoad(1) should load what ever is there on 1st position of local variable table,
		// to the TOP of the stack of operand.

		bILoad.execute(activeFrame);

		Stack<Object> resultOperandStack = activeFrame.getOperandStack();

		Object result = resultOperandStack.lastElement();

		assertEquals(result, new Integer(2));
	}

	@Test
	public void testExecuteBFramePostion3() {
		// create local variable table
		// varTable index starts from 0
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(7);
		varTable.add(2);
		varTable.add(5);
		varTable.add(9);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		//create a frame
		BFrame activeFrame = new BFrame(0, varTable, operandStack);

		//invoke iload 3
		BILoad bILoad = new BILoad(3,6);
		bILoad.execute(activeFrame);

		//check the stack after exeuction
		Stack<Object> resultOperandStack = activeFrame.getOperandStack();
		Integer actualResult = (Integer)resultOperandStack.peek();

		//compare results
		assertEquals(actualResult, new Integer(9));
	}
}

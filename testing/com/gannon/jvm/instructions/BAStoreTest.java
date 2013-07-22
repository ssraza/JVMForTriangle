package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.instructions.BAStore;

public class BAStoreTest {

	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BAStore instance = new BAStore(0,1);
		int expResult = 58;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BAStore instance = new BAStore(0,1);
		
		String expResult = "astore";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFramePostion0() {
		System.out.println("execute");
		BAStore bAStore = new BAStore(0,0);// Initialize BIStore, pass 2 as operand value
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

		// Before calling the execute method, LocalVariableTable will have 7 on its 0th, 
		// 2 on its first and 5 on its 2nd position. OperandStack will have 10,1 and 9. 9 will be the top of the stack.
		// Expectation is, BAStore(0) should load what ever is there on top of operand stack to the 0th position of ,
		// LocalVariableTable. 
		
		
		bAStore.execute(activeFrame);

		BLocalVarTable resultVarTable = activeFrame.getVarTable();
		
		Object result = resultVarTable.getLocalVariable(0); //get the value from 0th position of resulted Local variable table
		
		assertEquals(result, new Integer(9));
	}
	
	@Test
	public void testExecuteBFramePostion2() {
		System.out.println("execute");
		BAStore bAStore = new BAStore(2,0);// Initialize BIStore, pass 2 as operand value
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

		// Before calling the execute method, LocalVariableTable will have 7 on its 0th, 
		// 2 on its first and 5 on its 2nd position. OperandStack will have 10,1 and 9. 9 will be the top of the stack.
		// Expectation is, BAStore(2) should load what ever is there on top of operand stack to the 2nd position of ,
		// LocalVariableTable. 
		
		
		bAStore.execute(activeFrame);

		BLocalVarTable resultVarTable = activeFrame.getVarTable();
		
		Object result = resultVarTable.getLocalVariable(2);//get the value from 2nd position of resulted Local variable table
		
		assertEquals(result, new Integer(9));
	}


}

package test.opcode;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.Executor.GannonJVM.BFrame;
import com.gannon.Executor.GannonJVM.BLocalVarTable;
import com.gannon.Executor.GannonJVM.JVMStackSingleton;
import com.gannon.Executor.Instruction.BIReturn;

public class BIReturnTest {

	@Test
	public void testExecuteOneMethodOnStack() {
		BIReturn bIReturn = new BIReturn();

		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(new Integer(1)); // add value 1 to index 0

		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		
		JVMStackSingleton.getInstance().pushFrame(activeFrame);
		
		// Before calling the execute method,  operand stack of active frame has 1 on its TOS. JVMStack has only one method.
		// Expectation is, BIReturn should return what ever is there on TOS of operand of active method frame
		// and unload(POP) that active method frame form JVMStack.

		Integer retrunValue = (Integer) bIReturn.execute(activeFrame);

		Integer resultedJVMStack = (Integer)JVMStackSingleton.getInstance().size();
		assertEquals(resultedJVMStack, new Integer(0));
		assertEquals(retrunValue, new Integer(1));
	}
	
	@Test
	public void testExecuteTwoMethodsOnStack() {
		BIReturn bIReturn = new BIReturn();

		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(new Integer(10)); // add value 1 to index 0

		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		
		JVMStackSingleton.getInstance().pushFrame(activeFrame);
		
		BLocalVarTable secondVarTable = new BLocalVarTable();
		Stack<Integer> secondOperandStack = new Stack<Integer>();
		operandStack.add(new Integer(7)); // add value 1 to index 0

		BFrame secondActiveFrame = new BFrame(0, varTable, operandStack);
		
		JVMStackSingleton.getInstance().pushFrame(secondActiveFrame);
		
		// Before calling the execute method,  operand stack of active frame has 7 on its TOS. JVMStack has only two method frame on its stack.
		// the Method frame on TOP of the JVM stack will be called Active Method Frame.
		// Expectation is, BIReturn should return what ever is there on TOS of operand of active method frame
		// and unload(POP) that active method frame form JVMStack. JVMStack will then consider the TOS Method Frame as active method Frame.

		Integer retrunValue = (Integer) bIReturn.execute(activeFrame);
		assertEquals((Integer)JVMStackSingleton.getInstance().size(),new Integer(1));
		assertEquals(retrunValue, new Integer(7));
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BIReturn instance = new BIReturn();

		String expResult = "ireturn";
		String result = instance.getOpcodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFrame() {
		System.out.println("getOpcode");
		BIReturn instance = new BIReturn();
		int expResult = 172;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

}

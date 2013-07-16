package test.opcode;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.Executor.Instruction.BIReturn;
import com.gannon.Executor.Instruction.BReturn;
import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;
import com.gannon.Executor.JVMExecutionObjects.JVMStackSingleton;

public class BReturnTest {

	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BReturn instance = new BReturn();
		int expResult = 177;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BReturn instance = new BReturn();

		String expResult = "return";
		String result = instance.getOpcodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteOneMethodOnStack() {
		BIReturn bIReturn = new BIReturn();

		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(new Integer(1)); // add value 1 to index 0

		BFrame activeFrame = new BFrame(varTable, 0, operandStack);
		
		JVMStackSingleton.getInstance().addMethodFrame(activeFrame);
		
		// Before calling the execute method,  operand stack of active frame has 1 on its TOS. JVMStack has only one method.
		// Expectation is, BIReturn should return null
		// and unload(POP) that active method frame form JVMStack.
		
		bIReturn.execute(activeFrame);
		Integer resultedJVMStack = (Integer)JVMStackSingleton.getInstance().getFrameListSize();
		assertEquals(resultedJVMStack, new Integer(0));
	}
	
	@Test
	public void testExecuteTwoMethodsOnStack() {
		BIReturn bIReturn = new BIReturn();

		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(new Integer(10)); // add value 1 to index 0

		BFrame activeFrame = new BFrame(varTable, 0, operandStack);
		
		JVMStackSingleton.getInstance().addMethodFrame(activeFrame);
		
		BLocalVarTable secondVarTable = new BLocalVarTable();
		Stack<Integer> secondOperandStack = new Stack<Integer>();
		operandStack.add(new Integer(7)); // add value 1 to index 0

		BFrame secondActiveFrame = new BFrame(varTable, 0, operandStack);
		
		JVMStackSingleton.getInstance().addMethodFrame(secondActiveFrame);
		
		// Before calling the execute method,  operand stack of active frame has 7 on its TOS. JVMStack has only two method frame on its stack.
		// the Method frame on TOP of the JVM stack will be called Active Method Frame.
		// Expectation is, BIReturn should return null
		// and unload(POP) that active method frame form JVMStack. JVMStack will then consider the TOS Method Frame as active method Frame.

		bIReturn.execute(activeFrame);
		assertEquals((Integer)JVMStackSingleton.getInstance().size(),new Integer(1));

	}

}

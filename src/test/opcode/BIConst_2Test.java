package test.opcode;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.Executor.Instruction.BIConst_2;
import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;

public class BIConst_2Test {

	@Test
	public void testExecute() {
		BIConst_2 iconst2 = new BIConst_2();
		Stack<Integer> operandStack = new  Stack<Integer>();
		BLocalVarTable varTable = new BLocalVarTable();
		BFrame activeFrame = new BFrame(varTable, 0, operandStack);
		
		// Before calling the execute method,  LocalVariableTable and operand stack will be empty
		// Expectation is, BIConst_2 will load 2 on top of the operand stack,
		
		iconst2.execute(activeFrame);
		
		Stack<Integer> resultOprndStack = new  Stack<Integer>();
		resultOprndStack = activeFrame.getOperandStack();
		
		Stack<Integer> expectedOprndStack = new  Stack<Integer>();
		expectedOprndStack.push(2);
		
		assertEquals(expectedOprndStack, resultOprndStack);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BIConst_2 instance = new BIConst_2();

		String expResult = "iconst_2";
		String result = instance.getOpcodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFrame() {
		System.out.println("getOpcode");
		BIConst_2 instance = new BIConst_2();
		int expResult = 5;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

}

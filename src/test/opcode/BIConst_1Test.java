package test.opcode;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.Executor.Instruction.BIConst_1;
import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;

public class BIConst_1Test {

	@Test
	public void testExecute() {
		BIConst_1 iconst1 = new BIConst_1();
		Stack<Integer> operandStack = new  Stack<Integer>();
		BLocalVarTable varTable = new BLocalVarTable();
		BFrame activeFrame = new BFrame(varTable, 0, operandStack);
		
		// Before calling the execute method,  LocalVariableTable and operand stack will be empty
		// Expectation is, BIConst_1 will load 1 on top of the operand stack,
		
		iconst1.execute(activeFrame);
		
		Stack<Integer> resultOprndStack = new  Stack<Integer>();
		resultOprndStack = activeFrame.getOperandStack();
		
		Stack<Integer> expectedOprndStack = new  Stack<Integer>();
		expectedOprndStack.push(1);
		
		assertEquals(expectedOprndStack, resultOprndStack);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BIConst_1 instance = new BIConst_1();

		String expResult = "iconst_1";
		String result = instance.getOpcodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFrame() {
		System.out.println("getOpcode");
		BIConst_1 instance = new BIConst_1();
		int expResult = 4;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

}

package test.opcode;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.Executor.GannonJVM.BFrame;
import com.gannon.Executor.GannonJVM.BLocalVarTable;
import com.gannon.Executor.Instruction.BBipush;

public class BBipushTest {

	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BBipush instance = new BBipush(10);
		int expResult = 16;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BBipush instance = new BBipush(10);
		
		String expResult = "bipush 10";
		String result = instance.getOpcodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFramePosition1() {
		System.out.println("execute");
		BBipush biPush = new BBipush(16);
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(10); // add value 10 to index 0 of operand stack

		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		
		// Before calling the execute method,  operand stack will have 10 on its 0th position.
		// Expectation is, BBipush(16) should load 16 on the top of the operand stack. i.e. on 1st position in this case
		
		biPush.execute(activeFrame);
		
		Stack<Integer> result = activeFrame.getOperandStack();
		Stack<Integer> expectedResult = new Stack<Integer>();
		
		expectedResult.push(10);
		expectedResult.push(16);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void testExecuteBFramePosition0() {
		System.out.println("execute");
		BBipush biPush = new BBipush(8);
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();

		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		
		// Before calling the execute method,  operand stack and localvariable table are empty. 
		// Expectation is, BBipush(8) should load 16 on the top of the operand stack. i.e. on 0th position in this case
		
		biPush.execute(activeFrame);
		
		Stack<Integer> result = activeFrame.getOperandStack();
		Stack<Integer> expectedResult = new Stack<Integer>();
		
		//expectedResult.push(10);
		expectedResult.push(8);
		assertEquals(expectedResult, result);
	}

}

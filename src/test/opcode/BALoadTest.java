package test.opcode;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.Executor.Instruction.BALoad;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;

public class BALoadTest {

	@Test
	public void testPoistion0() {
		// load variable form local temp variable table at index of 0
		BInstruction bload = new BALoad(0);

		// init local Variable table
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		varTable.add(new Integer(7)); // add value 7 to index 0
		
		BFrame activeFrame = new BFrame(varTable, 0, operandStack);
		
		// Before calling the execute method,  LocalVariableTable will have 7 on its 0th position.
		// Expectation is, BALoad(0) should load what ever is there on 0th position of local variable table,
		// to the TOP of the stack of operand. 
		
		bload.execute(activeFrame);
		Stack<Integer> afterExe = activeFrame.getOperandStack();

		// System.out.println(afterExe);

		Stack<Integer> expectedOperandStack = new Stack<Integer>();
		expectedOperandStack.push(new Integer(7));

		assertEquals(expectedOperandStack, afterExe);

	}

	@Test
	public void testPoistion1() {
		// load variable form local temp variable table at index of 0
		BInstruction bload = new BALoad(1);

		// init local Variable table
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		varTable.add(new Integer(7)); // add value 7 to index 0
		varTable.add(new Integer(8)); // add value 8 to index 1

		BFrame activeFrame = new BFrame(varTable, 0, operandStack);
		bload.execute(activeFrame);
		
		// Before calling the execute method,  LocalVariableTable will have 7 on its 0th position. and 8 on its 1st.
		// Expectation is, BLoad(1) should load what ever is there on 1st position of local variable table,
		// Should be copied to TOP of the stack of operand. 
		
		Stack<Integer> afterExe = activeFrame.getOperandStack();

		System.out.println(afterExe);

		Stack<Integer> expectedOperandStack = new Stack<Integer>();
		expectedOperandStack.push(new Integer(8));

		assertEquals(expectedOperandStack, afterExe);

	}

}

package test.opcode;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Stack;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.Executor.Instruction.BIFicmpne;
import com.gannon.Executor.Instruction.BInvokeVirtual;
import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;

public class BIFicmpneTest {

	@Test
	public void testExecutePositive() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		
		BIFicmpne ifNotEqual = new BIFicmpne(label3);
		Stack<Integer> operandStack = new  Stack<Integer>();
		operandStack.push(5);
		operandStack.push(6);
		
		BLocalVarTable varTable = new BLocalVarTable();
		
		HashMap<String, Integer> labelMapping = new HashMap<String, Integer>();
		labelMapping.put(label1.toString(), 5);
		labelMapping.put(label2.toString(), 8);
		labelMapping.put(label3.toString(), 12);						
		
		BFrame activeFrame = new BFrame(varTable, 0, operandStack);
		activeFrame.setLabelMap(labelMapping);
		// Before calling the execute method,  operand stack will have 5 at 0th position and 6 at 1st position.
		// Expectation is, BIFicmpne will update program counter to 12. 
		
		ifNotEqual.execute(activeFrame);
		
		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer)12, resultedPC);
	}

	
	@Test
	public void testExecuteNegative() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		
		BIFicmpne ifNotEqual = new BIFicmpne(label3);
		Stack<Integer> operandStack = new  Stack<Integer>();
		operandStack.push(5);
		operandStack.push(6);
		
		BLocalVarTable varTable = new BLocalVarTable();
		
		HashMap<String, Integer> labelMapping = new HashMap<String, Integer>();
		labelMapping.put(label1.toString(), 5);
		labelMapping.put(label2.toString(), 8);
		labelMapping.put(label3.toString(), 1);						
					
		
		BFrame activeFrame = new BFrame(varTable, 0, operandStack);
		activeFrame.setLabelMap(labelMapping);
		// Before calling the execute method,  operand stack will have 5 at 0th position and 6 at 1st position.
		// Expectation is, program counter will stay at 1. 
		
		ifNotEqual.execute(activeFrame);
		
		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer)1, resultedPC);
	}

	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BIFicmpne instance = new BIFicmpne();
		int expResult = 160;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		Label label = new Label();
		BIFicmpne instance = new BIFicmpne(label);
		String expResult = "if_cmpne "+label.toString();
		String result = instance.getOpcodeCommand();
		assertEquals(expResult, result);
	}


}
package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Stack;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.asm.component.BBlock;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;
import com.gannon.jvm.instructions.BIFicmpeq;

public class BIFicmpeqTest {

	@Test
	public void testExecutePositive() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		BIFicmpeq ifEqual = new BIFicmpeq(label2);
	
		Stack<Integer> operandStack = new  Stack<Integer>();
		operandStack.push(5);
		operandStack.push(5);
		
		BLocalVarTable varTable = new BLocalVarTable();
		
		HashMap<String, Integer> labelMapping = new HashMap<String, Integer>();
		labelMapping.put(label1.toString(), 5);
		labelMapping.put(label2.toString(), 8);
		labelMapping.put(label3.toString(), 12);				
		
		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		activeFrame.setLabelMap(labelMapping);
		// Before calling the execute method,  operand stack will have 5 at 0th position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 8. 
		
		ifEqual.execute(activeFrame);
		
		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer)8, resultedPC);
	}
	
	@Test
	public void testExecuteNegative() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		BIFicmpeq ifEqual = new BIFicmpeq(label2);
	
		Stack<Integer> operandStack = new  Stack<Integer>();
		operandStack.push(5);
		operandStack.push(5);
		
		BLocalVarTable varTable = new BLocalVarTable();
		
		HashMap<String, Integer> labelMapping = new HashMap<String, Integer>();
		labelMapping.put(label1.toString(), 5);
		labelMapping.put(label2.toString(), 8);
		labelMapping.put(label3.toString(), 12);				
					
		
		BFrame activeFrame = new BFrame(10, varTable, operandStack);
		activeFrame.setLabelMap(labelMapping);
		// Before calling the execute method,  operand stack will have 5 at 0th position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 11. 
		
		ifEqual.execute(activeFrame);
		
		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer)11, resultedPC);
	}
	
	@Test
	public void testExecuteReturnPositive() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		BIFicmpeq ifEqual = new BIFicmpeq(label2);
	
		Stack<Integer> operandStack = new  Stack<Integer>();
		operandStack.push(5);
		operandStack.push(5);
		
		BLocalVarTable varTable = new BLocalVarTable();
		
		HashMap<String, Integer> labelMapping = new HashMap<String, Integer>();
		labelMapping.put(label1.toString(), 5);
		labelMapping.put(label2.toString(), 8);
		labelMapping.put(label3.toString(), 12);				
		
		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		activeFrame.setLabelMap(labelMapping);
		// Before calling the execute method,  operand stack will have 5 at 0th position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 8. 
		
		BFrame returnFrame = (BFrame) ifEqual.execute(activeFrame);
		
		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer)8, resultedPC);
	}
	
	@Test
	public void testExecuteReturnNegativeTest() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		BIFicmpeq ifEqual = new BIFicmpeq(label2);
	
		Stack<Integer> operandStack = new  Stack<Integer>();
		operandStack.push(5);
		operandStack.push(5);
		
		BLocalVarTable varTable = new BLocalVarTable();
		
		HashMap<String, Integer> labelMapping = new HashMap<String, Integer>();
		labelMapping.put(label1.toString(), 5);
		labelMapping.put(label2.toString(), 8);
		labelMapping.put(label3.toString(), 12);				
					
		
		BFrame activeFrame = new BFrame(10, varTable, operandStack);
		activeFrame.setLabelMap(labelMapping);
		// Before calling the execute method,  operand stack will have 5 at 0th position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 11. 
		
		ifEqual.execute(activeFrame);
		
		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer)11, resultedPC);
	}
	

	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BIFicmpeq instance = new BIFicmpeq();
		int expResult = 159;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		Label label = new Label();
		BIFicmpeq instance = new BIFicmpeq(label);
		String expResult = "if_cmpeq "+label.toString();
		String result = instance.getOpcodeCommand();
		assertEquals(expResult, result);
	}

}

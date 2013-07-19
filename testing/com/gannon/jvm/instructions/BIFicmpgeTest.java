package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Stack;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;
import com.gannon.jvm.instructions.BIConst_1;
import com.gannon.jvm.instructions.BIFicmpge;

public class BIFicmpgeTest {

	@Test
	public void testExecutePositive() {
		
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		
		BIFicmpge ifGreaterNequal = new BIFicmpge(label1);
		
		Stack<Integer> operandStack = new  Stack<Integer>();
		operandStack.push(5);
		operandStack.push(6);
		
		BLocalVarTable varTable = new BLocalVarTable();
		
		HashMap<String, Integer> labelMapping = new HashMap<String, Integer>();
		labelMapping.put(label1.toString(), 5);
		labelMapping.put(label2.toString(), 8);
		labelMapping.put(label3.toString(), 12);		
		
		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		activeFrame.setLabelMap(labelMapping);
		// Before calling the execute method,  operand stack will have 5 at 0th position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 5. 
		
		ifGreaterNequal.execute(activeFrame);
		
		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer)5, resultedPC);
	}

	@Test
	public void testExecuteNegative() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		
		BIFicmpge ifGreaterNequal = new BIFicmpge(label1);
		
		Stack<Integer> operandStack = new  Stack<Integer>();
		operandStack.push(5);
		operandStack.push(6);
		
		BLocalVarTable varTable = new BLocalVarTable();
		
		HashMap<String, Integer> labelMapping = new HashMap<String, Integer>();
		labelMapping.put(label1.toString(), 5);
		labelMapping.put(label2.toString(), 8);
		labelMapping.put(label3.toString(), 12);			
		
		BFrame activeFrame = new BFrame(0, varTable, operandStack);
		activeFrame.setLabelMap(labelMapping);
		// Before calling the execute method,  operand stack will have 5 at 0th position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 1. 
		
		ifGreaterNequal.execute(activeFrame);
		
		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer)1, resultedPC);
	}
	
	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BIFicmpge instance = new BIFicmpge();
		int expResult = 162;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		Label label = new Label();
		
		BIFicmpge instance = new BIFicmpge(label);
		String expResult = "if_cmpge "+label.toString();
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}
}

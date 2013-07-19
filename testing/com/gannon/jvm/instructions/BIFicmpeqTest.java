package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BLabel;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;

public class BIFicmpeqTest {

	@Test
	public void testExecutePositive() {
		Label newLabel1 = new Label();
		Label newLabel2 = new Label();
		Label newLabel3 = new Label();

		BMethod method = new BMethod();

		method.setName("<init>");
		ArrayList<BBlock> labelList = new ArrayList<BBlock>();
		
		BLabel label1 = new BLabel(newLabel1, 1);
		BBlock block1 = new BBlock(label1);
		ArrayList<BInstruction> Instr1 = new ArrayList<BInstruction>();
		Instr1.add(new BALoad(0));
		Instr1.add(new BInvokeSpecial("java/lang/Object", "init", "()V"));
		block1.setInstructions(Instr1);
		labelList.add(block1);

		BLabel label2 = new BLabel(newLabel2, 2);
		BBlock block2 = new BBlock(label2);
		ArrayList<BInstruction> Instr2 = new ArrayList<BInstruction>();
		Instr2.add(new BALoad(0));
		Instr2.add(new BIConst_1());
		Instr2.add(new BPutStatic("com/gannon/ASM/BytecodeReader/Hello", "i",
				"I"));
		block2.setInstructions(Instr2);
		labelList.add(block2);

		BLabel label3 = new BLabel(newLabel3, 3);
		BBlock block3 = new BBlock(label3);
		ArrayList<BInstruction> Instr3 = new ArrayList<BInstruction>();
		Instr3.add(new BALoad(0));
		Instr3.add(new BIConst_0());
		Instr3.add(new BPutStatic("com/gannon/ASM/BytecodeReader/Hello", "j",
				"I"));
		label2.setInstructions(Instr3);
		labelList.add(label2);

		BBlock label3 = new BBlock();
		label3.setNewLableName("L3");
		ArrayList<BInstruction> Instr4 = new ArrayList<BInstruction>();
		Instr4.add(new BReturn());
		label3.setInstructions(Instr4);
		labelList.add(label3);

		method.setBlockList(labelList);

		methodObjectList.add(method);

		BIFicmpeq ifEqual = new BIFicmpeq(label2);

		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.push(5);
		operandStack.push(5);

		BLocalVarTable varTable = new BLocalVarTable();

		BFrame activeFrame = new BFrame(0, varTable, operandStack);

		// Before calling the execute method, operand stack will have 5 at 0th
		// position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 8.

		ifEqual.execute(activeFrame);

		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer) 8, resultedPC);
	}

	@Test
	public void testExecuteNegative() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		BIFicmpeq ifEqual = new BIFicmpeq(label2);

		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.push(5);
		operandStack.push(5);

		BLocalVarTable varTable = new BLocalVarTable();

		BFrame activeFrame = new BFrame(10, varTable, operandStack);
		// Before calling the execute method, operand stack will have 5 at 0th
		// position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 11.

		ifEqual.execute(activeFrame);

		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer) 11, resultedPC);
	}

	@Test
	public void testExecuteReturnPositive() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		BIFicmpeq ifEqual = new BIFicmpeq(label2);

		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.push(5);
		operandStack.push(5);

		BLocalVarTable varTable = new BLocalVarTable();

		BFrame activeFrame = new BFrame(0, varTable, operandStack);

		// Before calling the execute method, operand stack will have 5 at 0th
		// position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 8.

		BFrame returnFrame = (BFrame) ifEqual.execute(activeFrame);

		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer) 8, resultedPC);
	}

	@Test
	public void testExecuteReturnNegativeTest() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		BIFicmpeq ifEqual = new BIFicmpeq(label2);

		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.push(5);
		operandStack.push(5);

		BLocalVarTable varTable = new BLocalVarTable();

		BFrame activeFrame = new BFrame(10, varTable, operandStack);
		// Before calling the execute method, operand stack will have 5 at 0th
		// position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 11.

		ifEqual.execute(activeFrame);

		Integer resultedPC = activeFrame.getPC();
		assertEquals((Integer) 11, resultedPC);
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
		String expResult = "if_cmpeq";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}

}

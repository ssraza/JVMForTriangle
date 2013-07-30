package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BLabel;
import com.gannon.asm.components.BLocalVariable;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.BinPredicateNode;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;

public class BIFicmpgeTest {

	@Test
	public void testExecuteGraterAndEqualPositive() {
		
		//Create a Class to hold the method
		BClass bClass = new BClass("TestClass");

		///  Create Method with blocks and instructions
		BMethod method = new BMethod();
		ArrayList<BBlock> blockList = new ArrayList<BBlock>();

		method.setName("<init>");
		BLabel label1 = new BLabel(new Label(), 1);
		label1.setGoToLineNumber(8);// block will start from line number 8, So when
								// if_icmpeq executes and
								// based on the label value(newLabel1) it will
								// jump to this label
								// and it should update the PC to 8.
		BBlock block1 = new BBlock(label1);
		ArrayList<BInstruction> Instr1 = new ArrayList<BInstruction>();
		Instr1.add(new BALoad(0, 0));
		Instr1.add(new BInvokeSpecial("java/lang/Object", "init", "()V", 1));
		block1.setInstructions(Instr1);
		blockList.add(block1);

		BLabel label2 = new BLabel(new Label(), 2);
		BBlock block2 = new BBlock(label2);
		ArrayList<BInstruction> Instr2 = new ArrayList<BInstruction>();
		Instr2.add(new BIFicmpge(label1, 2));// set Label for jump
		Instr2.add(new BIConst_1(3));
		Instr2.add(new BIConst_5(4));
		block2.setInstructions(Instr2);
		blockList.add(block2);

		BLabel label3 = new BLabel(new Label(), 3);
		label3.setGoToLineNumber(5);
		BBlock block3 = new BBlock(label3);	// block start from line number 8, So
											// when if_icmpeq executes and
											// based on the label value(newLabel1) it will jump to this label
											// and it should update the PC to 8.
		ArrayList<BInstruction> Instr3 = new ArrayList<BInstruction>();
		Instr3.add(new BALoad(0, 5));
		Instr3.add(new BIConst_0(6));
		Instr3.add(new BPutStatic("com/gannon/ASM/BytecodeReader/Hello", "j",
				"I", 7));
		block3.setInstructions(Instr3);
		blockList.add(block3);

		method.setBlockList(blockList);

		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.push(6);
		operandStack.push(5);

		BLocalVarTable varTable = new BLocalVarTable();

		ArrayList<BMethod> methods = new ArrayList<BMethod>();
		methods.add(method);
		bClass.setMethods(methods);
		
		BFrame activeFrame = new BFrame(bClass, method, 0, varTable, operandStack);

		BIFicmpge ifGreaterNequal = new BIFicmpge(label1, 2);
		// Before calling the execute method, operand stack will have 5 at 0th
		// position and 6 at 1st position.
		// Expectation is, BIFicmpge will update program counter to 5.

		ifGreaterNequal.execute(activeFrame);

		Integer resultedPC = activeFrame.getLineNumber();
		assertEquals((Integer) 8, resultedPC);
	}


	@Test
	public void testExecuteGraterAndEqualNegative() {

		Label newLabel1 = new Label();
		Label newLabel2 = new Label();
		Label newLabel3 = new Label();
		
		//Create a Class to hold the method
				BClass bClass = new BClass("TestClass");

		///  Create Method with blocks and instructions
		BMethod method = new BMethod();
		ArrayList<BBlock> blockList = new ArrayList<BBlock>();

		method.setName("<init>");
		BLabel label1 = new BLabel(newLabel1, 1);
		label1.setGoToLineNumber(8);// block will start from line number 8, So when
								// if_icmpeq executes and
								// based on the label value(newLabel1) it will
								// jump to this label
								// and it should update the PC to 8.
		BBlock block1 = new BBlock(label1);
		ArrayList<BInstruction> Instr1 = new ArrayList<BInstruction>();
		Instr1.add(new BALoad(0, 0));
		Instr1.add(new BInvokeSpecial("java/lang/Object", "init", "()V", 1));
		block1.setInstructions(Instr1);
		blockList.add(block1);

		BLabel label2 = new BLabel(newLabel2, 2);
		BBlock block2 = new BBlock(label2);
		ArrayList<BInstruction> Instr2 = new ArrayList<BInstruction>();
		Instr2.add(new BIFicmpge(new BLabel(newLabel3), 2));// set Label for jump
		Instr2.add(new BIConst_1(3));
		Instr2.add(new BIConst_5(4));
		block2.setInstructions(Instr2);
		blockList.add(block2);

		BLabel label3 = new BLabel(newLabel3, 3);
		label3.setGoToLineNumber(5);
		BBlock block3 = new BBlock(label3);	// block start from line number 8, So
											// when if_icmpeq executes and
											// based on the label value(newLabel1) it will jump to this label
											// and it should update the PC to 8.
		ArrayList<BInstruction> Instr3 = new ArrayList<BInstruction>();
		Instr3.add(new BALoad(0, 5));
		Instr3.add(new BIConst_0(6));
		Instr3.add(new BPutStatic("com/gannon/ASM/BytecodeReader/Hello", "j",
				"I", 7));
		block3.setInstructions(Instr3);
		blockList.add(block3);

		method.setBlockList(blockList);

		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.push(2);
		operandStack.push(5);

		BLocalVarTable varTable = new BLocalVarTable();

		ArrayList<BMethod> methods = new ArrayList<BMethod>();
		methods.add(method);
		bClass.setMethods(methods);
		
		BFrame activeFrame = new BFrame(bClass,method, 2, varTable, operandStack);

		BIFicmpge ifGreaterNequal = new BIFicmpge(new BLabel(newLabel3), 2);
		// Before calling the execute method, operand stack will have 5 at 0th
		// position and 6 at 1st position. Program Counter is set at 2.
		// Expectation is, BIFicmpge will update program counter to 3.

		ifGreaterNequal.execute(activeFrame);

		Integer resultedPC = activeFrame.getLineNumber();
		assertEquals((Integer) 3, resultedPC);
	}


	@Test
	public void testExecuteNotGreaterThanNEqual() {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();



		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.push(3);
		operandStack.push(6);

		BLocalVarTable varTable = new BLocalVarTable();

		HashMap<String, Integer> labelMapping = new HashMap<String, Integer>();
		labelMapping.put(label1.toString(), 5);
		labelMapping.put(label2.toString(), 8);
		labelMapping.put(label3.toString(), 12);

		BFrame activeFrame = new BFrame(5, varTable, operandStack);
		BIFicmpge ifGreaterNequal = new BIFicmpge(new BLabel(label1), 5);
	//	activeFrame.setLabelMap(labelMapping);
		// Before calling the execute method, operand stack will have 5 at 0th
		// position and 6 at 1st position. Program counter is set to 5
		// Expectation is, BIFicmpge will update program counter to 6.

		ifGreaterNequal.execute(activeFrame);

		Integer resultedPC = activeFrame.getLineNumber();
		assertEquals((Integer) 6, resultedPC);
	}

	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		Label label = new Label();
		BIFicmpge instance = new BIFicmpge(new BLabel(label), 0);
		int expResult = 162;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		Label label = new Label();
		BIFicmpge instance = new BIFicmpge(new BLabel(label), 0);
		String expResult = "if_icmpge";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test() {
		Label newLabel = new Label();
		BLabel label = new BLabel(newLabel);
		Stack<String> operandStack = new Stack<String>();
		operandStack.add("5");
		operandStack.add("5");

		DependencyFrame dependency = new DependencyFrame();
		dependency.setTempVariableStack(operandStack);
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		
		BLocalVariable localvar1 = new BLocalVariable("i", "I", null, 0); 
		method.addLocalVariableTable(localvar1);
		BLocalVariable localvar2 = new BLocalVariable("j", "I", null, 1); 
		method.addLocalVariableTable(localvar2);
		BLocalVariable localvar3 = new BLocalVariable("k", "I", null, 2); 
		method.addLocalVariableTable(localvar3);
		BLocalVariable localvar4 = new BLocalVariable("l", "I", null, 3); 
		method.addLocalVariableTable(localvar4);
		BLocalVariable localvar5 = new BLocalVariable("m", "I", null, 4); 
		method.addLocalVariableTable(localvar5);
		BLocalVariable localvar6 = new BLocalVariable("n", "I", null, 5); 
		method.addLocalVariableTable(localvar6);
		
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();

		BPredicateInstruction ifGreater=new BIFicmpge(label, 2);
		ifGreater.analyzing(dependency);
		System.out.println(dependency.getRelations().getRelations());
		Dependency actualTree=dependency.getRelations().getRelation(7);
		actualTree.inorderBST();

		BinNode rightNode= new BinNode("5");
		BinNode leftNode= new BinNode("5");
		BinPredicateNode rootNode=new BinPredicateNode("1000");
		Dependency expectedTree=new Dependency(rootNode, ifGreater);
		
		expectedTree.insertToLeft(leftNode);
		expectedTree.insertToRight(rightNode);
		expectedTree.inorderBST();

		assertEquals(expectedTree.getAllLeaves(), actualTree.getAllLeaves());
	}
}

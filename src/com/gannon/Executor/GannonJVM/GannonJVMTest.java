package com.gannon.Executor.GannonJVM;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.ASM.BytecodeClassGenerator.BClassGenerator;
import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BLabel;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.Executor.Instruction.BALoad;
import com.gannon.Executor.Instruction.BBipush;
import com.gannon.Executor.Instruction.BILoad;
import com.gannon.Executor.Instruction.BIReturn;
import com.gannon.Executor.Instruction.BIStore;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Executor.Instruction.BInvokeVirtual;

public class GannonJVMTest {

	@Test
	public void testExecute() {
		BMethod expectedMethod = new BMethod();

		expectedMethod.setName("plusFive");
		ArrayList<BBlock> labelList = new ArrayList<BBlock>();
		Label L1 = new Label();
		Label L2 = new Label();
		BBlock label0 = new BBlock(new BLabel(L1, 0));
		ArrayList<BInstruction> Instr0 = new ArrayList<BInstruction>();
		Instr0.add(new BALoad(0));
		Instr0.add(new BILoad(1));
		Instr0.add(new BBipush(10));
		Instr0.add(new BInvokeVirtual("com/gannon/ASM/BytecodeReader/Hello",
				"callee", "(II)I"));
		Instr0.add(new BIStore(2));
		label0.setInstructions(Instr0);
		labelList.add(label0);

		BBlock label1 = new BBlock(new BLabel(L2, 1));

		ArrayList<BInstruction> Instr1 = new ArrayList<BInstruction>();
		Instr1.add(new BILoad(2));
		Instr1.add(new BIReturn());
		label1.setInstructions(Instr1);
		labelList.add(label1);

		expectedMethod.setBlockList(labelList);

	}
	
	@Test
	public void testClassName() {
		BClass myclass=BClassGenerator.getBClass("Triangle.class");
		assertEquals("Triangle", myclass.getShortClassName());
	}


	@Test
	public void testMethodName() {
		BClass myclass=BClassGenerator.getBClass("Triangle.class");
		BMethod m=myclass.getMethod("triangleType");
		assertEquals("triangleType", m.getName());
	}

	@Test
	public void testMethodTriangleType() {
		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(5);
		varTable.add(5);
		varTable.add(5);
		varTable.add(5);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(m, 0, varTable, operandStack);

		// execution
		GannonJVM jvm = new GannonJVM(activeFrame);
		Object o = jvm.execute();

		System.out.print(o);
	}

}

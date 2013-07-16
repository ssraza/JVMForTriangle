package test.function;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.ASM.BytecodeReader.ByteCodeClassGenerator;
import com.gannon.Executor.Instruction.BALoad;
import com.gannon.Executor.Instruction.BBipush;
import com.gannon.Executor.Instruction.BILoad;
import com.gannon.Executor.Instruction.BIReturn;
import com.gannon.Executor.Instruction.BIStore;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Executor.Instruction.BInvokeVirtual;

public class BytecodeFactoryTest {

	@Test
	public void testLebel() {
		ByteCodeClassGenerator clasGen = new ByteCodeClassGenerator("Hello.class");
		clasGen.cparser();
		BClass triangleClass = clasGen.getbFactory().getBClass();
			System.out.println("879787454 "+triangleClass.getClassName());
			System.out.println("455454"+triangleClass.getMethods().get(2).getName());
		BMethod actualMethod = triangleClass.getMethods().get(2);

		System.out.println("21231321 "+actualMethod.getName());
		BMethod expectedMethod = new BMethod();
		
		expectedMethod.setName("plusFive");
		ArrayList<BBlock> labelList = new ArrayList<BBlock>();
			
		BBlock label0 = new BBlock();
			label0.setNewLableName("L0");
				ArrayList<BInstruction> Instr0 = new ArrayList<BInstruction>();
					Instr0.add(new BALoad(0));
					Instr0.add(new BILoad(1));
					Instr0.add(new BBipush(10));
					Instr0.add(new BInvokeVirtual("com/gannon/ASM/BytecodeReader/Hello", "callee", "(II)I"));
					Instr0.add(new BIStore(2));
					label0.setInstructions(Instr0);
			labelList.add(label0);
			
			BBlock label1 = new BBlock();
			label1.setNewLableName("L1");
				ArrayList<BInstruction> Instr1 = new ArrayList<BInstruction>();
					Instr1.add(new BILoad(2));
					Instr1.add(new BIReturn());						
			label1.setInstructions(Instr1);
			labelList.add(label1);				
					
			expectedMethod.setBlockList(labelList);
				
			assertEquals(actualMethod,expectedMethod);

	}

	

}
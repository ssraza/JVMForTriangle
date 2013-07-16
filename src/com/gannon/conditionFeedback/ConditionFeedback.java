package com.gannon.conditionFeedback;

import java.util.ArrayList;

import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.ASM.BytecodeReader.ByteCodeClassGenerator;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Main.InterfaceAPISingleton;

public class ConditionFeedback {
	
	public static void main(String [] args) {

		ByteCodeClassGenerator clasGen = new ByteCodeClassGenerator(
				"Hello.class");
		clasGen.cparser();
		BClass loadedClass = clasGen.getbFactory().getBClass();
		InterfaceAPISingleton.getInstance().setbClass(loadedClass);
		
		BMethod selectedMethod = loadedClass.getMethods().get(3);
		
		ArrayList<BBlock> blockList = selectedMethod.getBlockList();
		
		for (BBlock block : blockList) {
			
			for (BInstruction instr : block.getInstructions()) {
				instr.execute(null);
				
			}
		}
	}

}

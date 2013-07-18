package com.gannon.conditionFeedback;

import java.util.ArrayList;

import com.gannon.ASM.classgenerator.BClassGenerator;
import com.gannon.ASM.components.BBlock;
import com.gannon.ASM.components.BClass;
import com.gannon.ASM.components.BMethod;
import com.gannon.Main.InterfaceAPISingleton;
import com.gannon.jvm.Instructions.BInstruction;

public class ConditionFeedback {
	
	public static void main(String [] args) {

		BClassGenerator clasGen = new BClassGenerator(
				"Hello.class");
		clasGen.getBClass();
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

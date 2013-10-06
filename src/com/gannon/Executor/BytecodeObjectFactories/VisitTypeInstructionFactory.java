package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BNew;
import com.gannon.jvm.utilities.OpcodeUtility;

public class VisitTypeInstructionFactory {
	BInstruction instr;
	public BInstruction createInst(int opCode, String packageName, int lineNumber){
		if(OpcodeUtility.getOpCodeCommand(opCode).equals("new")){
        	System.out.println("In VisitTypeInstructionFactory factory new "+ packageName);
            instr= new BNew(packageName, lineNumber);
        }
		return instr;
	}
}

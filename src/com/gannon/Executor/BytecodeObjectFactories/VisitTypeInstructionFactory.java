package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.jvm.instructions.BANewArray;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BNew;
import com.gannon.jvm.utilities.OpcodeUtility;

public class VisitTypeInstructionFactory {
	BInstruction instr;
	public BInstruction createInst(int opCode, String packageName, int lineNumber){
		//System.out.println("VisitTypeInstructionFactory");
		if(OpcodeUtility.getOpCodeCommand(opCode).equals("new")){
        	//System.out.println("In VisitTypeInstructionFactory factory new "+ packageName);
            instr= new BNew(packageName, lineNumber);
        }
		if(OpcodeUtility.getOpCodeCommand(opCode).equals("anewarray")){
        	//System.out.println("In VisitTypeInstructionFactory factory new "+ packageName);
            instr= new BANewArray(packageName, lineNumber);
        }
		
		//temp code for testing purpose
		if (instr == null)
        	System.out.println("instr null");
		return instr;
	}
}

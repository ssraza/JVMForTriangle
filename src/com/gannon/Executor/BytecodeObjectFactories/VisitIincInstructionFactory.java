package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.jvm.instructions.BIConst_0;
import com.gannon.jvm.instructions.BIConst_1;
import com.gannon.jvm.instructions.BIConst_m1;
import com.gannon.jvm.instructions.BIinc;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.utilities.OpcodeUtility;

public class VisitIincInstructionFactory {
	BInstruction instr;
	public BInstruction createInst(int opCode, int inc, int linNumber) {
		//System.out.println("instr is: " + OpcodeUtility.getOpCodeCommand(opCode));
		if (OpcodeUtility.getOpCodeCommand(opCode).equals("iinc")) {
			//System.out.println("In VisitIincInstructionFactory iinc ");
			instr = new BIinc(linNumber,inc);//new BIReturn(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iconst_1")) {
			//System.out.println("In VisitInstructionFactory iconst_1 ");
			instr = new BIConst_1(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iconst_0")) {
			//System.out.println("In VisitInstructionFactory iconst_1 ");
			instr = new BIConst_0(linNumber);
		}	
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iconst_m1")) {
			//System.out.println("In VisitInstructionFactory iconst_1 ");
			instr = new BIConst_m1(linNumber);
		}	
		
		return instr;
	}
}

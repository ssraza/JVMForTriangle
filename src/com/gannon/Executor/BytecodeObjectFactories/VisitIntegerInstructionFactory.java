package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.jvm.instructions.BBipush;
import com.gannon.jvm.instructions.BIAStore;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BNewArray;
import com.gannon.jvm.instructions.BSipush;
import com.gannon.jvm.utilities.OpcodeUtility;

public class VisitIntegerInstructionFactory {
	BInstruction instr;


public BInstruction createInst(int opCode,int operand1, int linNumber) {
		System.out.println("VisitIntegerInstructionFactory");
		if (OpcodeUtility.getOpCodeCommand(opCode).equals("bipush")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BBipush(operand1, linNumber);
		}else if (OpcodeUtility.getOpCodeCommand(opCode).equals("sipush")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BSipush(operand1, linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("newarray")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BNewArray(operand1, linNumber);//BBipush(operand1, linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iastore")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BIAStore(operand1, linNumber);//BNewArray(operand1, linNumber);//BBipush(operand1, linNumber);
		}
		
		//temp code for testing purpose
		else if (instr == null)
        	System.out.println("instr null");

		return instr;
	}
}

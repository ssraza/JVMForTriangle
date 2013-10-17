package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.jvm.instructions.BBipush;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BSipush;
import com.gannon.jvm.utilities.OpcodeUtility;

public class VisitIntegerInstructionFactory {
	BInstruction instr;


	public BInstruction createInst(int opCode,int operand1, int linNumber) {
		if (OpcodeUtility.getOpCodeCommand(opCode).equals("bipush")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BBipush(operand1, linNumber);
		}else if (OpcodeUtility.getOpCodeCommand(opCode).equals("sipush")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BSipush(operand1, linNumber);
		}

		return instr;
	}
}

package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.Utility.HardBytecode;
import com.gannon.jvm.instructions.BBipush;
import com.gannon.jvm.instructions.BInstruction;

public class VisitIntegerInstructionFactory {
	BInstruction instr;


	public BInstruction createInst(int opCode,int operand1, int linNumber) {
		if (HardBytecode.getStringByteCode(opCode).equals("bipush")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BBipush(operand1, linNumber);
		}

		return instr;
	}
}

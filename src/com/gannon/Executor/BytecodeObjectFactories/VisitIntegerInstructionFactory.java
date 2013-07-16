package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.Executor.Instruction.BBipush;
import com.gannon.Executor.Instruction.BIReturn;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Utility.HardBytecode;

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

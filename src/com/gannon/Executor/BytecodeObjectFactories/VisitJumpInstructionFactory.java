package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.Executor.Instruction.BIFicmpeq;
import com.gannon.Executor.Instruction.BIFicmpge;
import com.gannon.Executor.Instruction.BIFicmpne;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Utility.HardBytecode;

import org.objectweb.asm.Label;

public class VisitJumpInstructionFactory {

	BInstruction instr;
	
	 public BInstruction createInst(int opCode, Label label, int linNumber){
		 //IF_ICMPEQ, IF_ICMPNE, IF_ICMPGE
		 if(HardBytecode.getStringByteCode(opCode).equals("if_icmpeq")){
	        	//System.out.println("In VisitFieldInstructionFactory IF_ICMPEQ "+ opCode+ "  " + label + "  " + linNumber);
	            instr= new BIFicmpeq(label, linNumber);     
	        }
	        else if(HardBytecode.getStringByteCode(opCode).equals("if_icmpne")){
	        	//System.out.println("In VisitFieldInstructionFactory IF_ICMPNE "+ opCode+ "  " + label + "  " + linNumber);
	            instr= new BIFicmpne(label, linNumber);     
	        }
	        else if(HardBytecode.getStringByteCode(opCode).equals("if_icmpge")){
	        	//System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	            instr= new BIFicmpge(label, linNumber);     
	        }
		 
		return instr;
		 
		 
	 }
}

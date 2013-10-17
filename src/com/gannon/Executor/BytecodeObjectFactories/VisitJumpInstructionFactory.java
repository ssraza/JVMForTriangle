package com.gannon.Executor.BytecodeObjectFactories;

import org.objectweb.asm.Label;

import com.gannon.asm.components.BLabel;
import com.gannon.jvm.instructions.BIFicmpeq;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BIFicmpne;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.utilities.OpcodeUtility;

public class VisitJumpInstructionFactory {

	BInstruction instr;

	 public BInstruction createInst(int opCode, Label goToLabel, int linNumber){
		 System.out.println("VisitJumpInstructionFactory");
		 BLabel bLabel=new BLabel(goToLabel);
		 if(OpcodeUtility.getOpCodeCommand(opCode).equals("if_icmpeq")){
			 //System.out.println("In VisitFieldInstructionFactory IF_ICMPEQ "+ opCode+ "  " + label + "  " + linNumber);
			 instr= new BIFicmpeq(bLabel, linNumber);
		 }
	     else if(OpcodeUtility.getOpCodeCommand(opCode).equals("if_icmpne")){
	        //System.out.println("In VisitFieldInstructionFactory IF_ICMPNE "+ opCode+ "  " + label + "  " + linNumber);
	    	 instr= new BIFicmpne(bLabel, linNumber);
	     }
	     else if(OpcodeUtility.getOpCodeCommand(opCode).equals("if_icmpge")){
	    	 //System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	    	 instr= new BIFicmpge(bLabel, linNumber);
	     }
		 
		//temp code for testing purpose
		 if (instr == null)
	        	System.out.println("instr null");
		 
		return instr;


	 }
}

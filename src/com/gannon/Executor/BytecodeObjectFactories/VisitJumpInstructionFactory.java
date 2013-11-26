package com.gannon.Executor.BytecodeObjectFactories;

import org.objectweb.asm.Label;

import com.gannon.asm.components.BLabel;
import com.gannon.jvm.instructions.BGoto;
import com.gannon.jvm.instructions.BIFeq;
import com.gannon.jvm.instructions.BIFge;
import com.gannon.jvm.instructions.BIFicmpeq;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BIFicmpgt;
import com.gannon.jvm.instructions.BIFicmple;
import com.gannon.jvm.instructions.BIFicmpne;
import com.gannon.jvm.instructions.BIFle;
import com.gannon.jvm.instructions.BIFne;
import com.gannon.jvm.instructions.BIf_Icmplt;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.utilities.OpcodeUtility;

public class VisitJumpInstructionFactory {

	BInstruction instr;

	 public BInstruction createInst(int opCode, Label goToLabel, int linNumber){
		 //System.out.println("VisitJumpInstructionFactory");
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
		  
	     else if(OpcodeUtility.getOpCodeCommand(opCode).equals("if_icmpne")){
	    	 //System.out.println("In VisitFieldInstructionFactory IF_ICMPNE "+ opCode+ "  " + label + "  " + linNumber);
	    	 instr= new BIFicmpne(bLabel, linNumber);
	     }
	     else if(OpcodeUtility.getOpCodeCommand(opCode).equals("if_icmpge")){
	    	 //System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	    	 instr= new BIFicmpge(bLabel, linNumber);
	     }	        
	     else if(OpcodeUtility.getOpCodeCommand(opCode).equals("if_icmple")){
	    	 //System.out.println("factory for if_icmple, label is: " + bLabel.toString());
	    	 //System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	    	 //System.out.println("for if_icmple, label is: " + bLabel.toString());
	    	 instr= new BIFicmple(bLabel, linNumber);
	     }
	     else if(OpcodeUtility.getOpCodeCommand(opCode).equals("ifle")){
	    	 //System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	    	 //System.out.println("for ifle, label is: " + bLabel.toString());
	    	 instr= new BIFle(bLabel, linNumber);
	     }
	     else if(OpcodeUtility.getOpCodeCommand(opCode).equals("if_icmpgt")){
	    	 //System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	    	 //System.out.println("for ifle, label is: " + bLabel.toString());
	    	 instr= new BIFicmpgt(bLabel, linNumber);
	     }
	     else if(OpcodeUtility.getOpCodeCommand(opCode).equals("goto")){
	    	 //System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	    	 //System.out.println("factory for goto, label is: " + bLabel.toString());
	    	 instr= new BGoto(bLabel, linNumber);
	     }
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
		 else if(OpcodeUtility.getOpCodeCommand(opCode).equals("if_icmple")){
	        //System.out.println("factory for if_icmple, label is: " + bLabel.toString());
	        //System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	        //System.out.println("for if_icmple, label is: " + bLabel.toString());
			 instr= new BIFicmple(bLabel, linNumber);
		 }
		 else if(OpcodeUtility.getOpCodeCommand(opCode).equals("ifle")){
	       	//System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	       	//System.out.println("for ifle, label is: " + bLabel.toString());	
			 instr= new BIFle(bLabel, linNumber);
		 }
		 else if(OpcodeUtility.getOpCodeCommand(opCode).equals("if_icmpgt")){
			 //System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
			 //System.out.println("for ifle, label is: " + bLabel.toString());
			 instr= new BIFicmpgt(bLabel, linNumber);
		 }
		 else if(OpcodeUtility.getOpCodeCommand(opCode).equals("goto")){
			 //System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
			 //System.out.println("factory for goto, label is: " + bLabel.toString());
			 instr= new BGoto(bLabel, linNumber);
		 }
		 else if(OpcodeUtility.getOpCodeCommand(opCode).equals("if_icmplt")){
	        	//System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	        	//System.out.println("factory for goto, label is: " + bLabel.toString());
	            instr= new BIf_Icmplt(bLabel, linNumber);
		 }
		 else if(OpcodeUtility.getOpCodeCommand(opCode).equals("ifne")){
	        	//System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	        	//System.out.println("factory for goto, label is: " + bLabel.toString());
	            instr= new BIFne(bLabel, linNumber);
		 }
		 else if(OpcodeUtility.getOpCodeCommand(opCode).equals("ifeq")){
	        	//System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	        	//System.out.println("factory for goto, label is: " + bLabel.toString());
	            instr= new BIFeq(bLabel, linNumber);
		 }
		 else if(OpcodeUtility.getOpCodeCommand(opCode).equals("ifge")){
	        	//System.out.println("In VisitFieldInstructionFactory IF_ICMPGE "+ opCode+ "  " + label + "  " + linNumber);
	        	//System.out.println("factory for goto, label is: " + bLabel.toString());
	            instr= new BIFge(bLabel, linNumber);
		 }
		 	
		 
		//temp code for testing purpose
		 if (instr == null)
	        	System.out.println("instr null");

		return instr;


	 }
}

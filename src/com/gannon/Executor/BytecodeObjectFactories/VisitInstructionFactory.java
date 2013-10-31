package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.jvm.instructions.BAALoad;
import com.gannon.jvm.instructions.BAAStore;
import com.gannon.jvm.instructions.BAReturn;
import com.gannon.jvm.instructions.BArrayLength;
import com.gannon.jvm.instructions.BD2i;
import com.gannon.jvm.instructions.BDConst_1;
import com.gannon.jvm.instructions.BDup;
import com.gannon.jvm.instructions.BI2d;
import com.gannon.jvm.instructions.BIALoad;
import com.gannon.jvm.instructions.BIAStore;
import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.instructions.BIConst_0;
import com.gannon.jvm.instructions.BIConst_1;
import com.gannon.jvm.instructions.BIConst_2;
import com.gannon.jvm.instructions.BIConst_3;
import com.gannon.jvm.instructions.BIConst_4;
import com.gannon.jvm.instructions.BIConst_5;
import com.gannon.jvm.instructions.BIConst_m1;
import com.gannon.jvm.instructions.BIMul;
import com.gannon.jvm.instructions.BIRem;
import com.gannon.jvm.instructions.BIReturn;
import com.gannon.jvm.instructions.BISub;
import com.gannon.jvm.instructions.BIdiv;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPop;
import com.gannon.jvm.instructions.BReturn;
import com.gannon.jvm.utilities.OpcodeUtility;

public class VisitInstructionFactory {
	BInstruction instr;

	public BInstruction getInstr() {
		return instr;
	}

	public void setInstr(BInstruction instr) {
		this.instr = instr;
	}

	public BInstruction createInst(int opCode, int linNumber) {
		if (OpcodeUtility.getOpCodeCommand(opCode).equals("ireturn")) {
			//System.out.println("In VisitInstructionFactory ireturn ");
			instr = new BIReturn(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("return")) {
			//System.out.println("In VisitInstructionFactory return ");
			instr = new BReturn(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("pop")) {
			//System.out.println("In VisitInstructionFactory pop ");
			instr = new BPop(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iadd")) {
			//System.out.println("In VisitInstructionFactory pop ");
			instr = new BIAdd(linNumber);
		}else if (OpcodeUtility.getOpCodeCommand(opCode).equals("isub")) {
			//System.out.println("In VisitInstructionFactory pop ");
			instr = new BISub(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("idiv")) {
			//System.out.println("In VisitInstructionFactory pop ");
			instr = new BIdiv(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iconst_0")) {
			//System.out.println("In VisitInstructionFactory iconst_0 ");
			instr = new BIConst_0(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iconst_1")) {
			//System.out.println("In VisitInstructionFactory iconst_1 ");
			instr = new BIConst_1(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iconst_2")) {
			//System.out.println("In VisitInstructionFactory iconst_2 ");
			instr = new BIConst_2(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iconst_3")) {
			//System.out.println("In VisitInstructionFactory iconst_3 ");
			instr = new BIConst_3(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iconst_4")) {
			//System.out.println("In VisitInstructionFactory iconst_4 ");
			instr = new BIConst_4(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iconst_5")) {
			//System.out.println("In VisitInstructionFactory iconst_5 ");
			instr = new BIConst_5(linNumber);
		}else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iconst_m1")) {
			//System.out.println("In VisitInstructionFactory iconst_5 ");
			instr = new BIConst_m1(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("dup")) {
			//System.out.println("In VisitInstructionFactory dup ");
			instr = new BDup(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("areturn")) {
			//System.out.println("In VisitInstructionFactory areturn ");
			instr = new BAReturn(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("aastore")) {
			//System.out.println("In VisitInstructionFactory areturn ");
			instr = new BAAStore(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iastore")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BIAStore(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("aaload")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BAALoad(linNumber);//BIAStore(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("iaload")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BIALoad(linNumber);//BAALoad(linNumber);//BIAStore(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("arraylength")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BArrayLength(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("dconst_1")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BDConst_1(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("imul")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BIMul(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("irem")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BIRem(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("i2d")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BI2d(linNumber);
		}
		else if (OpcodeUtility.getOpCodeCommand(opCode).equals("d2i")) {
			//System.out.println("In VisitIntegerInstructionFactory ireturn");
			instr = new BD2i(linNumber);//BI2d(linNumber);
		}
		
		//temp code for testing purpose
		if (instr == null)
        	System.out.println("instr null");
		return instr;
	}
}

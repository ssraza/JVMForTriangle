package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.Utility.HardBytecode;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BInvokeInterface;
import com.gannon.jvm.instructions.BInvokeSpecial;
import com.gannon.jvm.instructions.BInvokeStatic;
import com.gannon.jvm.instructions.BInvokeVirtual;

public class VisitMethodInstructionFactory {
	private  BInstruction instr;
    ////INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or INVOKEINTERFACE.
    public BInstruction createInst(int opCode, String owner, String name, String desc, int linNumber){
        if(HardBytecode.getStringByteCode(opCode).equals("invokevirtual")){
        	//System.out.println("In VisitMethodInstructionFactory invokevirtual "+ owner + "  " + name + "  " + desc);
            instr= new BInvokeVirtual(owner, name, desc, linNumber);
        }
        else if(HardBytecode.getStringByteCode(opCode).equals("invokespecial")){
        	//System.out.println("In VisitMethodInstructionFactory invokespecial "+ owner + "  " + name + "  " + desc);
        	instr= new BInvokeSpecial(owner, name, desc, linNumber);
        }
        else if(HardBytecode.getStringByteCode(opCode).equals("invokestatic")){
        	//System.out.println("In VisitMethodInstructionFactory invokestatic "+ owner + "  " + name + "  " + desc);
            instr= new BInvokeStatic(owner, name, desc, linNumber);
        }
        else if(HardBytecode.getStringByteCode(opCode).equals("invokeinterface")){
        	//System.out.println("In VisitMethodInstructionFactory invokeinterface "+ owner + "  " + name + "  " + desc);
            instr= new BInvokeInterface(owner, name, desc, linNumber);
        }
        return instr;
    }

}

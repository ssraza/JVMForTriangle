package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.Executor.Instruction.BGetField;
import com.gannon.Executor.Instruction.BGetStatic;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Executor.Instruction.BInvokeInterface;
import com.gannon.Executor.Instruction.BInvokeSpecial;
import com.gannon.Executor.Instruction.BInvokeStatic;
import com.gannon.Executor.Instruction.BInvokeVirtual;
import com.gannon.Executor.Instruction.BPutField;
import com.gannon.Executor.Instruction.BPutStatic;
import com.gannon.Utility.HardBytecode;

public class VisitMethodInstructionFactory {
BInstruction instr; 
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.Executor.Instruction.BGetField;
import com.gannon.Executor.Instruction.BGetStatic;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Executor.Instruction.BPutField;
import com.gannon.Executor.Instruction.BPutStatic;
import com.gannon.Utility.HardBytecode;

/**
 *
 * @author Pratik
 */
public class VisitFieldInstructionFactory {
    BInstruction instr; 
    
    public BInstruction createInst(int opCode, String owner, String name, String desc, int linNumber){
        if(HardBytecode.getStringByteCode(opCode).equals("getfield")){
        	//System.out.println("In VisitFieldInstructionFactory getfield "+ owner+ "  " + name + "  " + desc);
            instr= new BGetField(owner, name, desc, linNumber);     
        }
        else if(HardBytecode.getStringByteCode(opCode).equals("putfield")){
        	//System.out.println("In VisitFieldInstructionFactory putfield "+ owner+ "  " + name + "  " + desc);
            instr= new BPutField(owner, name, desc, linNumber);     
        }
        else if(HardBytecode.getStringByteCode(opCode).equals("getstatic")){
        	//System.out.println("In VisitFieldInstructionFactory getstatic "+ owner+ "  " + name + "  " + desc);
            instr= new BGetStatic(owner, name, desc, linNumber);     
        }
        else if(HardBytecode.getStringByteCode(opCode).equals("putstatic")){
        	//System.out.println("In VisitFieldInstructionFactory putstatic "+ owner+ "  " + name + "  " + desc);
            instr= new BPutStatic(owner, name, desc, linNumber);     
        }
        return instr;
    }
    
}

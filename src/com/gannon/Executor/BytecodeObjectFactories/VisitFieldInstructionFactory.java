/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.Utility.HardBytecode;
import com.gannon.jvm.instructions.BGetField;
import com.gannon.jvm.instructions.BGetStatic;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPutField;
import com.gannon.jvm.instructions.BPutStatic;

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

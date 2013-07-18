/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.jvm.instructions.BGetField;
import com.gannon.jvm.instructions.BGetStatic;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPutField;
import com.gannon.jvm.instructions.BPutStatic;
import com.gannon.jvm.utilities.Utility;


/**
 *
 * @author Pratik
 */
public class VisitFieldInstructionFactory {
    private BInstruction instr;

    public BInstruction createInst(int opCode, String owner, String name, String desc, int linNumber){
        if(Utility.getOpCodeCommand(opCode).equals("getfield")){
        	//System.out.println("In VisitFieldInstructionFactory getfield "+ owner+ "  " + name + "  " + desc);
            instr= new BGetField(owner, name, desc, linNumber);
        }
        else if(Utility.getOpCodeCommand(opCode).equals("putfield")){
        	//System.out.println("In VisitFieldInstructionFactory putfield "+ owner+ "  " + name + "  " + desc);
            instr= new BPutField(owner, name, desc, linNumber);
        }
        else if(Utility.getOpCodeCommand(opCode).equals("getstatic")){
        	//System.out.println("In VisitFieldInstructionFactory getstatic "+ owner+ "  " + name + "  " + desc);
            instr= new BGetStatic(owner, name, desc, linNumber);
        }
        else if(Utility.getOpCodeCommand(opCode).equals("putstatic")){
        	//System.out.println("In VisitFieldInstructionFactory putstatic "+ owner+ "  " + name + "  " + desc);
            instr= new BPutStatic(owner, name, desc, linNumber);
        }
        return instr;
    }

}

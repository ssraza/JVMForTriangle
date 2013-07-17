package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.Utility.HardBytecode;
import com.gannon.jvm.instructions.BALoad;
import com.gannon.jvm.instructions.BAStore;
import com.gannon.jvm.instructions.BILoad;
import com.gannon.jvm.instructions.BIStore;
import com.gannon.jvm.instructions.BInstruction;


//operation to variables
public class VisitVariableInstructionFactory {
	BInstruction instr; 
    public BInstruction createInst(int opCode,int operand1, int linNumber){
        if(HardBytecode.getStringByteCode(opCode).equals("iload")){  
        	//System.out.println("In VisitVariableInstructionFactory factory ILoad "+ operand1);
            instr= new BILoad(operand1, linNumber);     
        }
        else if(HardBytecode.getStringByteCode(opCode).equals("istore")){
        	//System.out.println("In VisitVariableInstructionFactory factory IStore "+ operand1);
            instr= new BIStore(operand1, linNumber);   
        }
        else if(HardBytecode.getStringByteCode(opCode).equals("astore")){
        	//System.out.println("In VisitVariableInstructionFactory factory astore "+ operand1);
            instr= new BAStore(operand1, linNumber);   
        }
        else if(HardBytecode.getStringByteCode(opCode).equals("aload")){
        	//System.out.println("In VisitVariableInstructionFactory factory aload "+ operand1);
            instr= new BALoad(operand1, linNumber);   
        }
        return instr;
    }
}

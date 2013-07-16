package com.gannon.Executor.BytecodeObjectFactories;

import com.gannon.Executor.Instruction.BALoad;
import com.gannon.Executor.Instruction.BAStore;
import com.gannon.Executor.Instruction.BILoad;
import com.gannon.Executor.Instruction.BIStore;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Utility.HardBytecode;


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

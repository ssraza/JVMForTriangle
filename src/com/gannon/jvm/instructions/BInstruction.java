package com.gannon.jvm.instructions;

import com.gannon.Utility.HardBytecode;
import com.gannon.jvm.BFrame;

/**
 * This class is a part of data structure created to organize the information
 * about the class being visited. This Class will have the information of
 * instructions (Opcodes) for a method.
 *
 *
 * @param opCode
 *            Opcode value
 * @param operand1
 *            Operand value
 * @param operand2
 *            Operand value
 * @param owner
 *            Owner of the instruction, generally a class
 * @param name
 *            Signature of the instruction, Method Signature
 * @param desc
 *            Parameter description of the instruction
 * **/
public abstract class BInstruction {
	private int lineNumber; // ID of the instruction

	public abstract Object execute(BFrame activeFrame);

	public abstract int getOpcode();
	
	public abstract String getOpcodeCommand();//readable opcode

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String toString(){
		return String.valueOf(lineNumber) + " "+getOpcodeCommand();
	}

}
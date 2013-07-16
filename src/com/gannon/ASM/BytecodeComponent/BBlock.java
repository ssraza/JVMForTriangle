package com.gannon.ASM.BytecodeComponent;

import java.util.ArrayList;

import org.objectweb.asm.Label;

import com.gannon.Executor.Instruction.BInstruction;

/**
 * This class is a part of data structure created to organize the information
 * about the class being visited. This Class will have the label informations.
 * 
 * 
 * @param Label
 * 
 * **/
public class BBlock {
	private Label originalLabel;//e.g, L55666666
	private ArrayList<BInstruction> instructions = new ArrayList<BInstruction>();
	private String newLableName;//
	

	public BBlock() {
		super();
	}

	public BBlock(String newLableName) {
		super();
		this.newLableName = newLableName;
	}

	public BBlock(Label lable) {
		super();
		originalLabel = lable;
	}

	public String getNewLableName() {
		return newLableName;
	}

	public void setNewLableName(String newLableName) {
		this.newLableName = newLableName;
	}

	public Label getLables() {
		return originalLabel;
	}

	public void setLables(Label lables) {
		this.originalLabel = lables;
	}

	public void addInstruction(BInstruction instruction) {
		instructions.add(instruction);
	}

	public ArrayList<BInstruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(ArrayList<BInstruction> instructions) {
		this.instructions = instructions;
	}

	public void display() {
		System.out.println("Lable " + originalLabel);
		for (BInstruction instr : instructions) {
			System.out.println(instr.getOpcodeCommand());
		}
	}

	public ArrayList<String> getDisplayStrings() {
		ArrayList<String> result = new ArrayList<String>();
		for (BInstruction intr : instructions) {
			result.add(intr.getOpcodeCommand() + "\n");
		}		
		return result;
		//return result.toArray(new String[result.size()]);
	}
	
//	public int getStartInsLineNumber(){
//		
//	}
//	
//	public int getEndInsLineNumber(){
//		
//	}
	
	
}

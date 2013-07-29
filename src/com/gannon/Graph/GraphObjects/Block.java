package com.gannon.Graph.GraphObjects;

import java.util.ArrayList;

import com.gannon.jvm.instructions.BInstruction;

public class Block {

	private ArrayList<BInstruction> instructions = new ArrayList<BInstruction>();
	private String owner;
	private int id;

	public Block(String owner, int id) {
		this.owner = owner;
		this.id = id;
	}

	public BInstruction getLastLineInstruction() {
		return instructions.get(instructions.size() - 1);
	}

	public BInstruction getFirstLineInstruction() {
		return instructions.get(0);
	}

	// public BInstruction getInstrctionBylineNumber(int lineNumber) {
	// BInstruction result = null;
	// for (BInstruction instr : instructions) {
	// if (instr.getLineNumber() == lineNumber) {
	// result = instr;
	// }
	// }
	// return result;
	// }

	public boolean isContainLineNumber(int lineNumber) {
		for (BInstruction instr : instructions) {
			if (instr.getLineNumber() == lineNumber) {
				return true;
			}
		}
		return false;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void addInstruction(BInstruction instruction) {
		instructions.add(instruction);
	}

	public int getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	public ArrayList<BInstruction> getInstructions() {
		return instructions;
	}

	// public String toString() {
	// String results = "Blocks info (ID):" + id + "\n";
	// for (BInstruction instr : instructions) {
	// results += instr.toStirng();
	// }
	// return results;
	// }

	public boolean hasInvoke() {
		return instructions.size() == 1
				&& instructions.get(0).getOpCodeCommand().contains("invoke");
	}
}

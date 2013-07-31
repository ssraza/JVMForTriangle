package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;

import com.gannon.jvm.instructions.BInstruction;

public class CBlock {
	private ArrayList<BInstruction> instructions = new ArrayList<BInstruction>();
	private String owner;// which method?
	private int id;
	
	public CBlock(int id) {
		super();
		this.id = id;
	}

	public CBlock(String owner, int id) {
		this.owner = owner;
		this.id = id;
	}

	public BInstruction getLastLineInstruction() {
		return instructions.get(instructions.size() - 1);
	}

	public BInstruction getFirstLineInstruction() {
		return instructions.get(0);
	}

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

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Blocks info (ID):" + id + "\n");
		for (BInstruction instr : instructions) {
			sb.append(instr);
		}
		return sb.toString();
	}

	public boolean hasInvoke() {
		return instructions.size() == 1 && instructions.get(0).getOpCodeCommand().contains("invoke");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((instructions == null) ? 0 : instructions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CBlock other = (CBlock) obj;
		if (instructions == null) {
			if (other.instructions != null)
				return false;
		} else if (!isEqual((CBlock)obj))
			return false;
		return true;
	}
	
	private boolean isEqual(CBlock block){
		if(instructions.size()!=block.getInstructions().size()){
			return false;
		}
		for(int i=0;i<instructions.size();i++){
			if(!instructions.get(i).equals(block.getInstructions().get(i))){
				return false;
			}
		}
		return true;
	}
}

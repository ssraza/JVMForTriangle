package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;
import java.util.List;

import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;

public class CBlock {
	private List<BInstruction> instructions = new ArrayList<BInstruction>();
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

	public BInstruction getFirstInstruction() {
		if (instructions != null && instructions.size() > 0) {
			return instructions.get(0);
		}
		return null;
	}
	
	public boolean hasIfStatement(){
		for (BInstruction instr : instructions) {
			if (instr.getOpCodeCommand().toLowerCase().contains("if")) {
				return true;
			}
		}
		return false;
	}
	
	//assume last statement is predicates, IF statement
	public BInstruction getIFInstruction() {
		if (instructions != null && instructions.size() > 0) {
			return instructions.get(instructions.size()-1);
		}
		System.out.print("getIFInstruction return null");
		return null;
	}

	public boolean containInstruction(BInstruction instruction) {
		for (BInstruction instr : instructions) {
			if (instr.equals(instruction)) {
				return true;
			}
		}
		return false;
	}

	public BPredicateInstruction findIfInstruction() {
		for (BInstruction instr : instructions) {
			if (instr.getOpCodeCommand().toLowerCase().contains("if")) {
				return (BPredicateInstruction) instr;
			}
		}
		return null;
	}

	//public BInstruction findGotoInstruction() {
	public BPredicateInstruction findGotoInstruction() {
		for (BInstruction instr : instructions) {
			if (instr.getOpCodeCommand().toLowerCase().contains("goto")) {
				return (BPredicateInstruction)instr;
			}
		}
		return null;
	}

	public BInstruction findReturnInstruction() {
		for (BInstruction instr : instructions) {
			if (instr.getOpCodeCommand().toLowerCase().contains("return")) {
				return instr;
			}
		}
		return null;
	}

	public boolean containLineNumber(int lineNumber) {
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

	public List<BInstruction> getInstructions() {
		return instructions;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Blocks info (ID):" + id + "\n");
		for (BInstruction instr : instructions) {
			sb.append(instr + "\n");
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
		result = prime * result + ((instructions == null) ? 0 : instructions.hashCode());
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
		} else if (!isEqual((CBlock) obj))
			return false;
		return true;
	}

	private boolean isEqual(CBlock block) {
		if (instructions.size() != block.getInstructions().size()) {
			return false;
		}
		for (int i = 0; i < instructions.size(); i++) {
			if (!instructions.get(i).equals(block.getInstructions().get(i))) {
				return false;
			}
		}
		return true;
	}
}

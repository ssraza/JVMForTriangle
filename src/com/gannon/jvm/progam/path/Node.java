package com.gannon.jvm.progam.path;

import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;
import com.gannon.jvm.instructions.Return;

public class Node {
	private BInstruction instruction;

	public Node(BInstruction ins) {
		super();
		this.instruction = ins;
	}

	public BInstruction getInstruction() {
		return instruction;
	}

	public void setInstruction(BInstruction instruction) {
		this.instruction = instruction;
	}

	public Integer getInstructionLineNumber() {
		return instruction.getLineNumber();
	}

	public boolean hasReturnInstruction() {
		return instruction instanceof Return;
	}
	
	public boolean isBPredicateNode() {
		return instruction instanceof BPredicateInstruction;
	}

	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Node) && instruction.equals(((Node) obj).getInstruction());
	}

	@Override
	public int hashCode() {
		return instruction.hashCode();
	}

	@Override
	public String toString() {
		return instruction.toString();
	}
}

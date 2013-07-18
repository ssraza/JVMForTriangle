package com.gannon.treeStructure;

import com.gannon.jvm.instructions.BInstruction;

public class Node {
	private BInstruction ins;

	public Node(BInstruction ins) {
		super();
		this.ins = ins;
	}

	public BInstruction getIns() {
		return ins;
	}

	public void setIns(BInstruction ins) {
		this.ins = ins;
	}

	public Integer getInstructionLineNumber(){
		return ins.getLineNumber();
	}


}

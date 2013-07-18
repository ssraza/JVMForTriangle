package com.gannon.jvm.data.dependency;

import com.gannon.jvm.instructions.BInstruction;

public class PNode {
	private BInstruction ins;

	public PNode(BInstruction ins) {
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

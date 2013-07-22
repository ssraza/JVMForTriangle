package com.gannon.jvm.instructions;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.BFrame;

public class BInvokeSpecial extends BInstruction {

	private String owner;
	private String name;
	private String desc;

	public BInvokeSpecial(String owner, String name, String desc, int lineNumber) {
		super(lineNumber);
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public Object execute(BFrame activeFrame) {
		return null;
	}

	public int getOpcode() {
		return 183;
	}

	public String getOpcodeCommand() {
		return "invokespecial" + " " + owner + " " + name + " " + desc;
	}

	public String toString() {
		return super.toString() + " " + owner + " " + name + " " + desc;
	}

	public String getOperand() {
		return name;
	}

	@Override
	public void analyzing(RelationFrame dependency) {
		// TODO Auto-generated method stub
		
	}

}

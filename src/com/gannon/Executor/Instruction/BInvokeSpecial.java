package com.gannon.Executor.Instruction;

import com.gannon.Executor.GannonJVM.BFrame;

public class BInvokeSpecial extends BInstruction {

	private String owner;
	private String name;
	private String desc;

	public BInvokeSpecial(String owner, String name, String desc) {
		super();
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public BInvokeSpecial(String owner, String name, String desc, int lineNumber) {
		setLineNumber(lineNumber);
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

}

package com.gannon.jvm.instructions;

import com.gannon.jvm.BFrame;

public class BGetStatic extends BInstruction {

	private String owner;
	private String name;
	private String desc;
	public boolean debug = true;

	public BGetStatic(String owner, String name, String desc, int lineNumber) {
		setLineNumber(lineNumber);
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public BGetStatic(String owner, String name, String desc) {
		super();
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public Object execute(BFrame activeFrame) {
		return null;

	}

	public int getOpcode() {
		return 178;
	}

	public String getOpcodeCommand() {
		return "getstatic" + " " + owner + " " + name + " " + desc;
	}

	public String toString() {
		return super.toString() + " " + owner + " " + name + " " + desc;
	}

	public String getOperand() {
		return name;
	}

}

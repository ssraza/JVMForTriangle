package com.gannon.Executor.Instruction;

import com.gannon.Executor.JVMExecutionObjects.BFrame;

public class BInvokeInterface extends BInstruction {

	private String owner;
	private String name;
	private String desc;

	public BInvokeInterface(String owner, String name, String desc) {
		super();
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public BInvokeInterface(String owner, String name, String desc,
			int lineNumber) {
		setLineNumber(lineNumber);
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public Object execute(BFrame activeFrame) {
		return null;
	}

	public int getOpcode() {
		return 185;
	}

	public String getOpcodeCommand() {
		return "invokeinterface" + " " + owner + " " + name + " " + desc;
	}

	public String toString() {
		return super.toString() + " " + owner + " " + name + " " + desc;
	}

	public String getOperand() {
		return name;
	}

}

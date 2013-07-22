package com.gannon.jvm.instructions;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.method.BFrame;

public class BInvokeInterface extends BInstruction {

	private String owner;
	private String name;
	private String desc;

	public BInvokeInterface(String owner, String name, String desc,
			int lineNumber) {
		super(lineNumber);
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

	@Override
	public void analyzing(RelationFrame dependency) {
		// TODO Auto-generated method stub
		
	}

}

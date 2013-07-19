package com.gannon.jvm.instructions;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.data.dependency.RelationCollector;

public class BInvokeStatic extends BInstruction{

	private String owner;
	private String name;
	private String desc;

	public BInvokeStatic(String owner, String name, String desc, int lineNumber) {
		setLineNumber(lineNumber);
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public Object execute(BFrame activeFrame) {
		return null;
	}

	public int getOpcode() {
		return 184;
	}

	public String getOpcodeCommand() {
		return "invokestatic" + " " + owner + " " + name + " " + desc;
	}

	public String toString() {
		return super.toString() + " " + owner + " " + name + " " + desc;
	}

	public String getOperand() {
		return name;
	}

	@Override
	public void analyzing(RelationCollector dependency) {
		// TODO Auto-generated method stub
		
	}

}

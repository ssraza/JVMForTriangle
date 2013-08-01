package com.gannon.jvm.instructions;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BPutStatic extends BInstruction {

	private String owner;
	private String name;
	private String desc;

	public BPutStatic(String owner, String name, String desc, int lineNumber) {
		super(lineNumber);
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	@Override
	public Object execute(BFrame activeFrame) {
		return null;

	}

	@Override
	public int getOpcode() {
		return 179;
	}

	public String getOpcodeCommand() {
		return "putstatic" + " " + owner + " " + name + " " + desc;
	}

	@Override
	public String toString() {
		return super.toString() + " " + owner + " " + name + " " + desc;
	}

	public String getOperand() {
		return name;
	}

	@Override
	public void analyzing(DependencyFrame dependency) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.gannon.jvm.instructions;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

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

	@Override
	public Object execute(BFrame activeFrame) {
		return null;
	}

	@Override
	public int getOpcode() {
		return 185;
	}

	public String getOpcodeCommand() {
		return "invokeinterface" + " " + owner + " " + name + " " + desc;
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

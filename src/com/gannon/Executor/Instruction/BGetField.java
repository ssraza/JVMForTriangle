/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gannon.Executor.Instruction;

import com.gannon.Executor.JVMExecutionObjects.BFrame;

/**
 *
 * @author Pratik
 */
public class BGetField extends BInstruction{
	private String owner;
	private String name;
	private String desc;
	public boolean debug = true;

	public BGetField(String owner, String name, String desc, int lineNumber) {
		setLineNumber(lineNumber);
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public BGetField(String owner, String name, String desc) {
		super();
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public Object execute(BFrame activeFrame) {
		activeFrame.setPC(1 + activeFrame.getPC());
		return null;

	}

	public int getOpcode() {
		return 180;
	}

	public String getOpcodeCommand() {
		return "getfield" + " " + owner + " " + name + " " + desc;
	}

	public String toString() {
		return super.toString() + " " + owner + " " + name + " " + desc;
	}

	public String getOperand() {
		return name;
	}

}

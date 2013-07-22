package com.gannon.jvm.instructions;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.method.BFrame;

public class BPop extends BInstruction {
	public BPop(int lineNumber) {
		super(lineNumber);
	}

	public String toString() {
		return super.toString();
	}

	public Object execute(BFrame activeFrame) {
		activeFrame.setPC(1 + activeFrame.getPC());
		return null;

	}

	public int getOpcode() {
		return 87;
	}

	public String getOpcodeCommand() {
		return "pop";
	}

	public String getOperand() {
		return "-1";
	}

	@Override
	public void analyzing(RelationFrame dependency) {
		// TODO Auto-generated method stub

	}

}

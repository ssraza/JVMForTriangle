package com.gannon.jvm.instructions;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.data.dependency.RelationCollector;

public class BPop extends BInstruction {
	public BPop() {
		super();
	}

	public BPop(int lineNumber) {
		setLineNumber(lineNumber);
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
	public void analyzing(RelationCollector dependency) {
		// TODO Auto-generated method stub
		
	}

}

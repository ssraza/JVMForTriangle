package com.gannon.jvm.instructions;

import com.gannon.asm.components.BLabel;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BIf_Icmplt extends BPredicateInstruction{

	public BIf_Icmplt(BLabel Label, int lineNumber) {
		super(Label, lineNumber);
	}

	@Override
	public Object execute(BFrame activeFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOpcode() {
		// TODO Auto-generated method stub
		return 161;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		//System.out.println("tostring");
		return super.toString() + " " + getOperand();
	}

}

package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.asm.components.BLabel;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;

public class BGoto extends BPredicateInstruction implements BGotoMarkerInterface {
	
	public BGoto(BLabel Label, int lineNumber) {
		super(Label, lineNumber);
	}
	
	@Override
	public String toString() {
		return super.toString()+" "+getOperand();
	}
	@Override
	public Object execute(BFrame activeFrame) {
//		Integer pc = getOperand().getGoToLineNumber();			
//		activeFrame.setLineNumber(pc);
		
		return null;
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOpcode() {;
		// TODO Auto-generated method stub
		return 167;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {
		// TODO Auto-generated method stub
		
	}

}

package com.gannon.jvm.instructions;

import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BNew extends BInstruction{

	private String packageName;
	
	public BNew(String packageName, int lineNumber) {
		super(lineNumber);
		this.packageName = packageName;
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
		return 187;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + packageName;
	}
	

}

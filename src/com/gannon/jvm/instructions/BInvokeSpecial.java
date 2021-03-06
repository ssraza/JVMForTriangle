package com.gannon.jvm.instructions;

import java.util.ArrayList;

import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;

public class BInvokeSpecial extends BInstruction  {

	private String owner;
	private String name;
	private String desc;

	public BInvokeSpecial(String owner, String name, String desc, int lineNumber) {
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
		return 183;
	}

	public String getOpcodeCommand() {
		return "invokespecial" + " " + owner + " " + name + " " + desc;
	}

	@Override
	public String toString() {
		return super.toString() + " " + owner + " " + name + " " + desc;
	}

	@Override
	public String getStringOperand() {
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

	@Override
	public BMethod getNextMethod(BClass bClass, String methodName) {
		ArrayList<BMethod> methodList = bClass.getMethods();
		for (int count = 0; count < methodList.size(); count ++) {
			if (methodList.get(count).getName().equals(methodName) ) {
				System.out.println("Next called Method is " + methodList.get(count).getName());
				return methodList.get(count);
			}
		}
		return null;
	}
	
	@Override
	public String getOwner() {
		return owner;
	}

}

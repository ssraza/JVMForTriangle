package com.gannon.jvm.instructions;

import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;

public abstract class BInstructionAbstract implements BInvokeTypeInteface{
	@Override
	public BMethod getNextMethod(BClass bClass){
		return null;
	}
	@Override
	public String getOwner(){
		return null;
	}
	@Override
	public BMethod getNextMethod(BClass bClass, String methodName){
		return null;
	}
	
	@Override
	public String getStringOperand(){
		return null;
	}
	
}

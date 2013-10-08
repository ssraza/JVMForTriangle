package com.gannon.jvm.instructions;

import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;

public interface BInvokeTypeInteface {
	public BMethod getNextMethod(BClass bClass);
	public BMethod getNextMethod(BClass bClass, String methodName);
	public String getOwner();
	public String getStringOperand();
	
}

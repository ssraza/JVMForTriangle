package com.gannon.jvm.execution.path;

import java.util.ArrayList;
import java.util.Stack;

import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.method.JVMStackSingleton;
import com.gannon.jvm.execution.method.MethodExecutor;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.progam.path.TestPath;

public class GannonPathJVM {
	private PathFrame pathFrame;
	private PathExecutor executor=new PathExecutor();

	public GannonPathJVM() {
		super();
	}

	public Object run(TestPath targetPath, ArrayList<Object> inputs) {
		init( targetPath, inputs);
		Object result = executor.execute(pathFrame);
		return result;
	}

	private void init(TestPath targetPath, ArrayList<Object> inputs) {
		// crate active frame
		BLocalVarTable localVariableTable = new BLocalVarTable(inputs);
		pathFrame = new PathFrame(targetPath, localVariableTable);

	}



}

package com.gannon.jvm.execution.path;

import java.util.ArrayList;

import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;

public class GannonPathJVM {
	private PathFrame pathFrame;
	private PathExecutor executor;

	public GannonPathJVM() {
		super();
	}

	private void init(TestPath targetPath, ArrayList<Object> inputs) {
		// crate active frame
		BLocalVarTable localVariableTable = new BLocalVarTable(inputs);
		pathFrame = new PathFrame(targetPath, localVariableTable);
		// init pathExecutor
		executor = new PathExecutor();

	}

	public Object run(TestPath targetPath, ArrayList<Object> inputs) {
		init(targetPath, inputs);
		Object result = executor.execute(pathFrame);
		return result;
	}

}

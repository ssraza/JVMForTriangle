package com.gannon.jvm.execution.path;

import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;
import com.ganon.jvm.shared.Frame;

public class PathFrame extends Frame {
	private TestPath testPath;

	public PathFrame(TestPath testPath) {
		super();
		this.testPath = testPath;
	}

	public PathFrame(TestPath testPath, BLocalVarTable localVariableTable) {
		super(localVariableTable);
		this.testPath = testPath;
	}

	public TestPath getTestPath() {
		return testPath;
	}
}

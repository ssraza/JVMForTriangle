package com.gannon.jvm.data.dependency;

import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.progam.path.TestPath;

public class DependencyAnalyzer {


	public DependencyAnalyzer() {
		super();
	}

	// assume activeFrame is saved in JVM stack
	public Object execute(DependencyFrame rFrame) {
		TestPath targetPath = rFrame.getTargetPath();

		for (BInstruction bInstruction : targetPath.getInstrucitons()) {
			bInstruction.analyzing(rFrame);

		}

		return null;
	}
}

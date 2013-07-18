package com.gannon.treeStructure;

import com.gannon.jvm.BFrame;

public interface Analyzable {
	void dependencyAnalyzing(int pc, BFrame frame);
}

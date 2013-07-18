package com.gannon.jvm.data.dependency;

import com.gannon.jvm.BFrame;

public interface Analyzable {
	void dependencyAnalyzing(int pc, BFrame frame);
}

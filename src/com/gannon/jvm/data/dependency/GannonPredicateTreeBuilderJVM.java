package com.gannon.jvm.data.dependency;

import com.gannon.jvm.progam.path.TestPath;

public class GannonPredicateTreeBuilderJVM {
	private DependencyFrame relationFrame=new DependencyFrame();
	private DependencyAnalyzer analyzer=new DependencyAnalyzer();

	public GannonPredicateTreeBuilderJVM() {
		super();
	}

	public void run(TestPath targetPath) {
		// The analyzer only reads the path in a frame, here we use a rFrame
		// Therefore we need to add the path to rFrame before we start analyzing
		// dependency
		// it is similar to BFrame for method execution
		// relationFrame will collect all relations
		init(targetPath);
		analyzer.execute(relationFrame);
	}

	private void init(TestPath targetPath) {
		relationFrame.setTargetPath(targetPath);
		relationFrame.initParameterRelation();
	}

	public DependencyFrame getRelationFrame() {
		return relationFrame;
	}

	public void setRelationFrame(DependencyFrame relationFrame) {
		this.relationFrame = relationFrame;
	}

	public DependencyAnalyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(DependencyAnalyzer analyzer) {
		this.analyzer = analyzer;
	}
}

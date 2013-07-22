package com.gannon.jvm.data.dependency;

import java.util.ArrayList;

import com.gannon.jvm.progam.path.TestPath;

public class GannonJVMPredicateTreeBuilder {
	private RelationFrame relationFrame;
	private DependencyAnalyzer analyzer;
	private TestPath targetPath;

	public GannonJVMPredicateTreeBuilder(TestPath targetPath) {
		super();
		this.relationFrame = new RelationFrame();
		this.analyzer = new DependencyAnalyzer();
		this.targetPath=targetPath;
	}

	public BinNode build() {
		// The analyzer only reads the path in a frame, here we use a rFrame
		// Therefore we need to add the path to rFrame before we start analyzing dependency
		// it is similar to BFrame for method execution
		relationFrame.setTargetPath(targetPath);
		
		//relationFrame will collect all relations
		analyzer.execute(relationFrame);				
		return assemblePredicateTree(relationFrame.getRelations());
	}
	
	private BinNode assemblePredicateTree(ArrayList<Relation> relations){
		BinNode result=null;
		
		
		
		
		return result;		
	}
}

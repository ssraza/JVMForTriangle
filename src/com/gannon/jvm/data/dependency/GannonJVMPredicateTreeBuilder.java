package com.gannon.jvm.data.dependency;

import java.util.ArrayList;

import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.progam.path.TestPath;

public class GannonJVMPredicateTreeBuilder {
	private RelationFrame relationFrame;
	private DependencyAnalyzer analyzer;

	public GannonJVMPredicateTreeBuilder() {
		super();
		this.relationFrame = new RelationFrame();
		this.analyzer = new DependencyAnalyzer();
	}

	public BinNode run(TestPath targetPath) {
		execute(targetPath);
		// return assemblePredicateTree(relationFrame.getRelations());
		return null;
	}

	private void execute(TestPath targetPath) {
		// The analyzer only reads the path in a frame, here we use a rFrame
		// Therefore we need to add the path to rFrame before we start analyzing
		// dependency
		// it is similar to BFrame for method execution
		// relationFrame will collect all relations
		relationFrame.setTargetPath(targetPath);
		analyzer.execute(relationFrame);
	}

	public Relation build(BinNode rootNode, ArrayList<Relation> relations) {
		if ( rootNode != null) {
			Relation leftRelation = findRelation(relations, rootNode.getLeftBNode());
			Relation rightRelation = findRelation(relations, rootNode.getRightBNode());
			if (rootNode.getLeftBNode()!=null && leftRelation != null) {
				Relation buildRelation = build(rootNode.getLeftBNode(), relations);
				return buildRelation.join(leftRelation);

			}
			if (rootNode.getRightBNode()!=null &&rightRelation != null) {
				return build(rootNode.getRightBNode(), relations).join(rightRelation);
			}
		}
		return new Relation(new BinNode("10000"),null);


	}

	public Relation findRelation(ArrayList<Relation> relations, BinNode variableNode) {
		for (Relation r : relations) {
			if (r.getTheBTRootNode().equals(variableNode)) {
				return r;
			}
		}
		return null;
	}
}

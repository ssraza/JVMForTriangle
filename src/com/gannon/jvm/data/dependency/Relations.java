package com.gannon.jvm.data.dependency;

import java.util.ArrayList;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Relation;

public class Relations {
	private ArrayList<Relation> relations = new ArrayList<Relation>();

	public Relations() {
		super();
	}

	public Relations(ArrayList<Relation> relations) {
		super();
		this.relations = relations;
	}

	public ArrayList<Relation> getRelations() {
		return relations;
	}

	public void setRelations(ArrayList<Relation> relations) {
		this.relations = relations;
	}

	public Relation findRelation(BinNode variableNode) {
		for (Relation r : relations) {
			if (r.getTheBTRootNode().equals(variableNode)) {
				return r;
			}
		}
		return null;
	}

	public void addRelation(Relation r) {
		relations.add(r);
	}

	public Relation getRelation(int index){
		return relations.get(index);
	}

	public void combinRelations(Relation newRelation) {
		BinNode leftNode = newRelation.getLeftNode();
		BinNode rightNode = newRelation.getRightNode();
		// check previous relations
		if (!leftNode.isParamter()) {
			Relation rLeft = findRelation(leftNode);
			newRelation.insertToLeft(rLeft.getTheBTRootNode());
		}
		if (!rightNode.isParamter()) {
			Relation rRight = findRelation(rightNode);
			newRelation.insertToRight(rRight.getTheBTRootNode());
		}
	}

}

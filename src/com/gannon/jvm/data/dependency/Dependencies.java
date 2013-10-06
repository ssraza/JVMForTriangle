package com.gannon.jvm.data.dependency;

import java.util.ArrayList;

import com.gannon.jvm.instructions.BInstruction;

public class Dependencies {
	private ArrayList<Dependency> relations = new ArrayList<Dependency>();

	public Dependencies() {
		super();
	}

	public Dependencies(ArrayList<Dependency> relations) {
		super();
		this.relations = relations;
	}

	public ArrayList<Dependency> getRelations() {
		return relations;
	}

	public void setRelations(ArrayList<Dependency> relations) {
		this.relations = relations;
	}

	public Dependency findRelation(BinNode variableNode) {
		for (Dependency r : relations) {
			if (r.getTheBTRootNode().equals(variableNode)) {
				return r;
			}
		}
		return null;
	}
	
	public Dependency findRelation(BInstruction instr) {
		for (Dependency r : relations) {
			if (r.getInst()!=null && r.getInst().equals(instr)) {
				return r; 
			}
		}
		return null;
	}

	public void add(Dependency r) {
		relations.add(r);
	}

	public Dependency getRelation(int index){
		return relations.get(index);
	}

	public void expendTheRelations(Dependency theRelation) {
		BinNode leftNode = theRelation.getLeftNode();
		BinNode rightNode = theRelation.getRightNode();
		// check previous relations
		if (!leftNode.isParamter()) {
			Dependency rLeft = findRelation(leftNode);
			theRelation.insertToLeft(rLeft.getTheBTRootNode());
		}
		if (!rightNode.isParamter()) {
			Dependency rRight = findRelation(rightNode);
			theRelation.insertToRight(rRight.getTheBTRootNode());
		}
	}

	public int size(){
		return relations.size();
	}

	@Override
	public String toString(){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<relations.size();i++){
			sb.append("===== Relation "+i+" ===========\n");
			sb.append(relations.get(i));
		}
		return sb.toString();
	}
}

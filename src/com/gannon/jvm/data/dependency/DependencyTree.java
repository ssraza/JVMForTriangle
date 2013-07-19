package com.gannon.jvm.data.dependency;

//build dependency tree only for predicate
public class DependencyTree {
	private BinNode root;

	public DependencyTree() {
		super();
	}


	public void insertToLeft(Relation rootRelation, Relation aNewRelation){
		if(root==null){
			root=aNewRelation.getTheBTRootNode();
		}else{
			//BinNode foundNode=relation.search(aNewRelation.getTheBTRootNode());
		}
	}


}

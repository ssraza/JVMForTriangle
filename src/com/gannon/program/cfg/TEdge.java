package com.gannon.program.cfg;

public class TEdge {
	private TNode sourceTNode;
	private TNode targetTNode;
	
	public TEdge(TNode source,TNode target){
		sourceTNode = source;
		targetTNode = target;
	}
	
	public TNode getSourceTNode(){
		return sourceTNode;
	}
	
	public TNode getTargetTNode(){
		return targetTNode;
	}
}

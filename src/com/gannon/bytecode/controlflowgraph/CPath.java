package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;
import java.util.List;

public class CPath {
	private int id;
	private List<CNode> nodes = new ArrayList<CNode>();
	public CPath(int id) {
		super();
		this.id = id;
	}
	
	public void add(CNode node){
		nodes.add(node);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CNode> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<CNode> nodes) {
		this.nodes = nodes;
	}

	public void addNodes(CNode nodes) {
		this.nodes.add(nodes);
	}

	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("CPath [id=" + id  +"]\n"); 
		for(CNode node: nodes){
			sb.append(node);
		}
		return sb.toString();
	}
	
	
}

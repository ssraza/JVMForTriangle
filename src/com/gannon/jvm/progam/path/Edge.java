package com.gannon.jvm.progam.path;

import com.gannon.jvm.utilities.ConstantsUtility;

public class Edge {
	private int id;
	private Node source;
	private Node target;
	private int predicateValue = ConstantsUtility.UNDEFINED_EXPECTED_VALUE;

	public Edge(int id) {
		super();
		this.id = id;
	}

	public Edge(int id, Node source, Node target) {
		super();
		this.id = id;
		this.source = source;
		this.target = target;
	}

	public Edge(Node source, Node target) { 
		super();
		this.source = source;
		this.target = target;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getTarget() {
		return target;
	}

	public void setTarget(Node target) {
		this.target = target;
	}

	public int getPredicateValue() {
		return predicateValue;
	}

	public void setPredicateValue(int predicateValue) {
		this.predicateValue = predicateValue;
	}
}

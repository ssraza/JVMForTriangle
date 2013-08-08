package com.gannon.bytecode.controlflowgraph;

import com.gannon.jvm.progam.path.Edge;
import com.gannon.jvm.progam.path.Node;

public final class CEdge {
	private final int id;
	private final CNode source;
	private final CNode target;
	private CEdgeValue value = new CEdgeValue();

	public CEdge(int id, CNode source, CNode target) {
		super();
		this.id = id;
		this.source = source;
		this.target = target;
	}

	public int getId() {
		return id;
	}

	public CNode getSource() {
		return source;
	}

	public CNode getTarget() {
		return target;
	}

	public CEdgeValue getValue() {
		return value;
	}

	public void setValue(CEdgeValue value) {
		this.value = value;
	}

	public Edge convertToEdge() {
		Node sourceNode = new Node(source.getBlock().getIFInstruction());
		Node targetNode = new Node(target.getBlock().getFirstInstruction());
		return new Edge(sourceNode, targetNode);
	}

	public CEdgeValue findValue(CNode sourceNode, CNode targetNode) {
		if (source.equals(sourceNode) && target.equals(targetNode)) {
			return value;
		}
		return null;
	}

	@Override
	public int hashCode() {
		return source.hashCode() + target.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CEdge other = (CEdge) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return source.equals(other.source) && target.equals(other.target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [id=" + this.id + "] Source Node [" + source + "] Target Node[" + target + "]";
	}
}

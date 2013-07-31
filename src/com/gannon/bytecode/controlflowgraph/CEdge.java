package com.gannon.bytecode.controlflowgraph;

public final class CEdge {
	private final int id;
	private final CNode source;
	private final CNode target;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
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
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [id=" + this.id + ", source=" + this.source.getId()
				+ ", target=" + this.target.getId() + "]";
	}
}

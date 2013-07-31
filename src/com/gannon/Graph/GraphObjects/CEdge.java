package com.gannon.Graph.GraphObjects;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CEdge other = (CEdge) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [id=" + this.id + ", source=" + this.source.getId() + ", target=" + this.target.getId() + "]";
	}
}

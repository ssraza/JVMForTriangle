package com.gannon.Graph.GraphObjects;

public class CNode {
	private final int id;
	private Block blcok;

	public CNode(final int id, Block blcok) {
		super();
		this.id = id;
		this.blcok = blcok;
	}

	public int getId() {
		return id;
	}

	public Block getBlcok() {
		return blcok;
	}

	@Override
	public String toString() {
		return "CNode [id=" + id + "]";
	}

	public void setBlcok(Block blcok) {
		this.blcok = blcok;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		CNode other = (CNode) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

package com.gannon.bytecode.controlflowgraph;

public class CNode {
	private final int id;
	private CBlock block;

	public CNode(final int id, CBlock blcok) {
		super();
		this.id = id;
		this.block = blcok;
	}

	public int getId() {
		return id;
	}

	public CBlock getBlock() {
		return block;
	}

	@Override
	public String toString() {
		return "CNode [id=" + id + "], block [id=" + block.getId() + "]";
	}

	public void setBlock(CBlock blcok) {
		this.block = blcok;
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

	public boolean hasInvoke() { 
		return block.hasInvoke();
	}

}

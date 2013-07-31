package com.gannon.bytecode.controlflowgraph;

import com.gannon.asm.components.BMethod;

public class Frame {
	private BMethod method;
	private int blockId;

	// private MethodGraphInitializer graphInitializer;

	// public Frame(BMethod methd, MethodGraphInitializer method,int blockId){
	// this.method = methd;
	// this.graphInitializer = method;
	// this.blockId = blockId;
	// }

	public Frame(BMethod method, int blockId) {
		super();
		this.method = method;
		this.blockId = blockId;
	}

	// public MethodGraphInitializer getGraphInitializer() {
	// return graphInitializer;
	// }
	//
	// public void setGraphInitializer(MethodGraphInitializer graphInitializer)
	// {
	// this.graphInitializer = graphInitializer;
	// }

	public void setMethod(BMethod method) {
		this.method = method;
	}

	public BMethod getMethod() {
		return method;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public int getBlockId() {
		return blockId;
	}
}

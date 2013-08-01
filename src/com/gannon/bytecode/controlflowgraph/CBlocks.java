package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;

import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;

public class CBlocks {
	private String methodName;
	private ArrayList<CBlock> blocks = new ArrayList<CBlock>();

	public CBlocks(String methodName) {
		super();
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public ArrayList<CBlock> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<CBlock> blocks) {
		this.blocks = blocks;
	}

	public boolean add(CBlock blcok) {
		return blocks.add(blcok);
	}

	public void clear() {
		blocks.clear();
	}

	public CBlock get(int block) {
		return blocks.get(block);
	}

	// find blockID with line number x, where x is in conditional instructions,
	// e.g., goto X, if X
	public int findPredicateInstructionTargtBlockId(
			BPredicateInstruction predicateInstruction) {
		int goToLineNumber = predicateInstruction.getOperand()
				.getGoToLineNumber();
		return findBlockIndexByLineNumber(goToLineNumber);
	}

	public int findBlockIndexByLineNumber(int lineNumber) {
		for (int i = 0; i < blocks.size(); i++) {
			CBlock b = blocks.get(i);
			if (b.containLineNumber(lineNumber)) {
				return i;
			}
		}
		return -1;// not in the block
	}

	public int size() {
		return blocks.size();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (CBlock b : blocks) {
			sb.append("=========================\n");
			sb.append(b);
		}
		return sb.toString();
	}

}

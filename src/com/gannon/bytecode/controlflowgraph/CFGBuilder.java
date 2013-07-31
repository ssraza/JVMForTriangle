package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;

import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BInstruction;

public class CFGBuilder {
	private BMethod bMethod;

	public CFGBuilder(BMethod bMethod) {
		super();
		this.bMethod = bMethod;
	}
	
	public int[] getSplitLine() {
		ArrayList<BInstruction> instrList = new ArrayList<BInstruction>();
		for(BBlock label : bMethod.getBlockList()){
			instrList.addAll(label.getInstructions());
		}

		int leaders[] = new int[instrList.size()];
		for (int i = 0; i < instrList.size(); i++) {
			String opcode = instrList.get(i).getOpCodeCommand();
			if (opcode.contains("if") || opcode.contains("goto")) {
				leaders[i + 1] = 1;
				int ponitLineNumber = Integer.parseInt((String) instrList.get(i).getOperand());
				leaders[ponitLineNumber] = 1;
			} // add notes here
			if (opcode.contains("return")) {
				if (i != instrList.size() - 1) {
					leaders[i + 1] = 1;
				}
			}
			if (opcode.contains("invoke")) {
				leaders[i] = 1;
				if (i != instrList.size() - 1) {
					leaders[i + 1] = 1;
				}
			}
		}
		return leaders;
	}

}

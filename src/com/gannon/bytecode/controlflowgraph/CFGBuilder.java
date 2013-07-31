package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;

import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BInstruction;

public class CFGBuilder {
	private BMethod bMethod;

	// The first line of a block
	private final String[] leadingStrings = new String[] { "if", "goto",
			"return", "invoke" };

	public CFGBuilder(BMethod bMethod) {
		super();
		this.bMethod = bMethod;
	}

	
	
	public ArrayList<CBlock> buildBlocks() {
		ArrayList<CBlock> blocks = new ArrayList<CBlock>();
		boolean blockLeader[] = computeLeadingLineFlags();
		
		int IdCount = 0;
		CBlock block = new CBlock(bMethod.getName(), IdCount++);
		for (int j = 0; j < blockLeader.length; j++) {
			if (blockLeader[j] != 1) {
				ArrayList<BInstruction> instrList = new ArrayList<BInstruction>();
				for(BBlock label : bMethod.getBlockList()){
					instrList.addAll(label.getInstructions());
				}
				block.addInstruction(instrList.get(j));
			}
			if (blockLeader[j] == 1) {
				blocks.add(block);
				block = new CBlock(bMethod.getName(), IdCount);
				IdCount++;
				//block.addInstruction(bMethod.getInstructions().get(j));
				ArrayList<BInstruction> instrList = new ArrayList<BInstruction>();
				for(BBlock label : bMethod.getBlockList()){
					instrList.addAll(label.getInstructions());
				}
				block.addInstruction(instrList.get(j));
			}
		}
		blocks.add(block);
		this.blockList = blocks;
		return blockList;
	}
	
	
	// return a flag array to indicate if an instruction is a leader instruction
	// of a block
	public boolean[] computeLeadingLineFlags() {
		ArrayList<BInstruction> instructions = bMethod.getInstructions();
		//leader 0 is not used. index 1== line number 1
		boolean leaders[] = new boolean[instructions.size() + 1];
		for (BInstruction instruction : instructions) {
			if (isContainLeadingString(leadingStrings, instruction)) {
				leaders[instruction.getLineNumber()] = true;
			}
		}
		return leaders;
	}

	public void displayLeadingFlags(boolean[] computeLeadingLineFlags) {
		for (int i = 0; i < computeLeadingLineFlags.length; i++) {
			System.out.println("Line Number " + i + " Flag: "
					+ computeLeadingLineFlags[i] + "\n ");
		}
	}

	private boolean isContainLeadingString(String[] leadingStrings,
			BInstruction ins) {
		for (int i = 0; i < leadingStrings.length; i++) {
			if (ins.getOpCodeCommand().contains(leadingStrings[i])) {
				return true;
			}
		}
		return false;
	}
}

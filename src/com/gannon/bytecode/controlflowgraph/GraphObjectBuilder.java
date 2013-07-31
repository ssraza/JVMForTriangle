package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;

import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BInstruction;

public class GraphObjectBuilder {
	private ArrayList<CBlock> blockList = new ArrayList<CBlock>();
	private BMethod bMethod;

	public GraphObjectBuilder(BMethod bmethod) {
		super();
		this.bMethod = bmethod;
	}

	public ArrayList<CBlock> getBlocks() {
		return blockList;
	}

	public ArrayList<CBlock> buildBlocks() {
		ArrayList<CBlock> blocks = new ArrayList<CBlock>();
		int blockLeader[] = getSplitLine();
		int IdCount = 0;
		CBlock block = new CBlock(bMethod.getName(), IdCount);
		IdCount++;
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

	public int getBlockIndexByOperandLineNumber(int operandLineNumber,
			ArrayList<CBlock> blocks) {
		for (int i = 0; i < blocks.size(); i++) {
			CBlock b = blocks.get(i);
			if (b.isContainLineNumber(operandLineNumber)) {
				return i;
			}
		}
		return -1;// not in the block
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

	public Graph buildGraph(ArrayList<CBlock> listOfBlock) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < listOfBlock.size(); i++) {
			BInstruction instr = listOfBlock.get(i).getLastLineInstruction();
			String lastLineOpcode = instr.getOpCodeCommand();
			String lastLineOperand = (String) instr.getOpCodeCommand();
			if (lastLineOpcode.toLowerCase().contains("if")) {
				edges.add(new Edge(listOfBlock.get(i), listOfBlock.get(i + 1)));
				int operandLineNumber = Integer.parseInt(lastLineOperand);
				int destID = getBlockIndexByOperandLineNumber(
						operandLineNumber, listOfBlock);
				edges.add(new Edge(listOfBlock.get(i), listOfBlock.get(destID)));
			} else if (lastLineOpcode.toLowerCase().contains("goto")) {
				int operandLineNumber = Integer.parseInt(lastLineOperand);
				int destID = getBlockIndexByOperandLineNumber(
						operandLineNumber, listOfBlock);
				edges.add(new Edge(listOfBlock.get(i), listOfBlock.get(destID)));
			} else if (i != listOfBlock.size() - 1) {
				if (lastLineOpcode.toLowerCase().indexOf("ireturn") == -1) {
					edges.add(new Edge(listOfBlock.get(i), listOfBlock
							.get(i + 1)));
				}
			}
		}
		return new Graph(listOfBlock, edges);
	}
}
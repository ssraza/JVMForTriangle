package com.gannon.Graph.GraphObjects;

import java.util.ArrayList;

import com.gannon.Graph.GraphObjects.Graph.Edge;
import com.gannon.asm.component.BBlock;
import com.gannon.asm.component.BMethod;
import com.gannon.jvm.Instructions.BInstruction;

public class GraphObjectBuilder {
	private ArrayList<Block> blockList = new ArrayList<Block>();
	private BMethod bMethod;

	public GraphObjectBuilder(BMethod bmethod) {
		super();
		this.bMethod = bmethod;
	}

	public ArrayList<Block> getBlocks() {
		return blockList;
	}

	public ArrayList<Block> buildBlocks() {
		ArrayList<Block> blocks = new ArrayList<Block>();
		int blockLeader[] = getSplitLine();
		int IdCount = 0;
		Block block = new Block(bMethod.getName(), IdCount);
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
				block = new Block(bMethod.getName(), IdCount);
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
			ArrayList<Block> blocks) {
		for (int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
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
			String opcode = instrList.get(i).getOpcodeCommand();
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

	public Graph buildGraph(ArrayList<Block> listOfBlock) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < listOfBlock.size(); i++) {
			BInstruction instr = listOfBlock.get(i).getLastLineInstruction();
			String lastLineOpcode = instr.getOpcodeCommand();
			String lastLineOperand = (String) instr.getOperand();
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
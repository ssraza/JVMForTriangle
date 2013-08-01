package com.gannon.bytecode.controlflowgraph;

import java.util.*;

import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;

/*
 * A wrapper of BMethod
 * It used for building CFG
 */
public class CFGMethod {
	private BMethod bMethod;

	public CFGMethod(BMethod bMethod) {
		super();
		this.bMethod = bMethod;
	}

	public CGraph buildGraph() {
		CBlocks listOfBlock=buildBlocks();
		int edgeId = 0;
		Set<CEdge> edges = new HashSet<CEdge>();

		for (int i = 0; i < listOfBlock.size(); i++) {
			CBlock block = listOfBlock.get(i);
			if (block.findIfInstruction() != null) {
				// first edge is next to next
				CNode sourceNode = new CNode(i, listOfBlock.get(i));
				CNode targetNode1 = new CNode(i + 1, listOfBlock.get(i + 1));
				CEdge cEdge1 = new CEdge(edgeId++, sourceNode, targetNode1);
				edges.add(cEdge1);

				// second edge connects to a block contains the instruction with
				// gotolinenumber
				int operandLineNumber = block.findIfInstruction().getOperand().getGoToLineNumber();
				int destBlockID = listOfBlock.findBlockIndexByLineNumber(operandLineNumber);
				CNode targetNode2 = new CNode(destBlockID, listOfBlock.get(destBlockID));
				CEdge cEdge2 = new CEdge(edgeId++, sourceNode, targetNode2);
				edges.add(cEdge2);
			} else if (block.findGotoInstruction() != null) {
				int operandLineNumber = block.findIfInstruction().getOperand()
						.getGoToLineNumber();
				int destBlockID = listOfBlock
						.findBlockIndexByLineNumber(operandLineNumber);
				CNode sourceNode = new CNode(i, listOfBlock.get(i));
				CNode targetNode = new CNode(destBlockID,	listOfBlock.get(destBlockID));
				edges.add(new CEdge(edgeId++, sourceNode, targetNode));
			} else if (block.findReturnInstruction() == null) {
				// connect to next block if this block doesn't have return
				CNode sourceNode = new CNode(i, listOfBlock.get(i));
				CNode targetNode1 = new CNode(i + 1, listOfBlock.get(i + 1));
				edges.add(new CEdge(edgeId++, sourceNode, targetNode1));
			}

		}
		return new CGraph(listOfBlock.convertToSet(), edges);
	}

	public CBlocks buildBlocks() {
		int blockID = 0;
		CBlocks blocks = new CBlocks(bMethod.getName());
		boolean blockLeader[] = computeLeadingLineFlags();
		ArrayList<BInstruction> instructions = bMethod.getInstructions();
		CBlock block = new CBlock(bMethod.getName(), blockID++);

		for (int j = 1; j < blockLeader.length; j++) {
			if (blockLeader[j]) {
				blocks.add(block);
				block = new CBlock(bMethod.getName(), blockID++);
			}
			block.addInstruction(instructions.get(j));
		}
		blocks.add(block);
		return blocks;
	}

	// return a flag array to indicate if an instruction is a leader instruction
	// of a block
	public boolean[] computeLeadingLineFlags() {
		ArrayList<BInstruction> instrList = bMethod.getInstructions();
		boolean leaders[] = new boolean[instrList.size()];

		for (int i = 0; i < instrList.size(); i++) {
			BInstruction instr = instrList.get(i);
			String opcode = instr.getOpCodeCommand();

			// rule 1: first instruction is a leader, however, for
			// implementation purpose, we omit it
			// if (i == 0) {
			// leaders[i] = true;
			// }

			// rule 2: Each instruction that is the target of a conditional "if"
			// /unconditional "goto" branch is a leader.
			// and its next line is a leader
			if (opcode.contains("if") || opcode.contains("goto")) {
				// next line is a leader
				leaders[i + 1] = true;
				// target line number is a leader
				if (instr instanceof BPredicateInstruction) {
					int targetLineNumber = ((BPredicateInstruction) instr)
							.getOperand().getGoToLineNumber();
					leaders[targetLineNumber - 1] = true;
				}
			}

			// rule 3: Each instruction that immediately follows a
			// <T>return(¡°ireturn¡±,
			// ¡°return¡±) a is a leader.
			if (opcode.contains("return")) {
				leaders[i - 1] = true;
			}
			if (opcode.contains("invoke")) {
				leaders[i] = true;
				leaders[i + 1] = true;
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

}

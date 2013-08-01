package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;

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

	public CBlocks buildBlocks() {
		int blockID = 0;
		CBlocks blocks=new CBlocks(bMethod.getName());
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

			// rule 1: first instruction is a leader, however, for implementation purpose, we omit it
//			if (i == 0) {
//				leaders[i] = true;
//			}

			// rule 2: Each instruction that is the target of a conditional "if"
			// /unconditional "goto" branch is a leader.
			// and its next line is a leader
			if (opcode.contains("if") || opcode.contains("goto")) {
				// next line is a leader
				leaders[i + 1] = true;
				// target line number is a leader
				if (instr instanceof BPredicateInstruction) {
					int targetLineNumber = ((BPredicateInstruction) instr).getOperand().getGoToLineNumber();
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
			System.out.println("Line Number " + i + " Flag: " + computeLeadingLineFlags[i] + "\n ");
		}
	}
	
	

}

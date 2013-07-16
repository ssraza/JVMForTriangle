package com.gannon.Utility;

import java.util.ArrayList;
import java.util.HashMap;

import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Executor.JVMExecutionObjects.BFrame;

public class ByteCodeUtility {
	private BFrame activeFrame;
	
	public HashMap<String, String> createLabelMapping(ArrayList<BBlock> labelDetails) {
		HashMap<String, String> labelNameMap = new HashMap<String, String>();
		int index = 0;

		for (BBlock label : labelDetails) {
			labelNameMap.put(label.getLables().toString(), "L"+index);
			index++;
		}		
		return labelNameMap;		
	}

	public ArrayList<String> getModifiedLabelBytecodes(ArrayList<BInstruction> instructionList, HashMap<String, String> labelNameMapping){
		ArrayList<String> modifiedBytecodes = new ArrayList<String>();
		for (BInstruction bInstruction : instructionList) {
			if (bInstruction.getOpcode() == 159 ||
					bInstruction.getOpcode() == 162 ||
					bInstruction.getOpcode() == 160 ){
				String[] opcode = bInstruction.getOpcodeCommand().split(" ");

				modifiedBytecodes.add(opcode[0]+" "+labelNameMapping.get(opcode[1]));				
			}
			else{
				modifiedBytecodes.add(bInstruction.getOpcodeCommand());
			}				
		}

		return modifiedBytecodes;
	}
	
	
	public HashMap<String, Integer> createLabelMapping(BMethod executingMethod ) {
		
		HashMap<String, Integer> labelHashMap = new HashMap<String, Integer>();
		int pc = 0;
		ArrayList<BBlock> labelList = executingMethod.getBlockList();
		for (BBlock label : labelList) {
			labelHashMap.put(label.getLables().toString(), pc);
			// The next pc value must be current pc added number of instructions
			// in current label
			pc = pc + label.getInstructions().size();
		}

		return labelHashMap;
	//	activeFrame.setLabelMap(labelHashMap);
	}

	// get the Instruction and PC mapping, PC should start from 0.
	public HashMap<Integer, BInstruction> createInstrPCMapping(BMethod method) {
		HashMap<Integer, BInstruction> instrHashMap = new HashMap<Integer, BInstruction>();
		int pc = 0;
		ArrayList<BBlock> lablList = method.getBlockList();
		for (BBlock labels : lablList) {
			ArrayList<BInstruction> instructionList = labels.getInstructions();
			for (BInstruction instructions : instructionList) {
				instrHashMap.put(pc, instructions);
				pc++;
			}
		}
		//activeFrame.setInstructionMap(instrHashMap);
		return instrHashMap;
	}
	
}

package com.gannon.ParameterDependency;

import java.util.ArrayList;
import java.util.HashMap;

import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.Executor.Instruction.BInstruction;

public class DependencyGenerator {
	private ArrayList<BBlock> blockList;
	private BMethod bMethod;
	private DependencyDataCollector dependencyDataCollector;
	private Integer tempVarCounter = 0;
	private Object firstParameter;
	private Object secondParameter;
	private String arithmaticOperation;

	public DependencyGenerator(BMethod bMethd) {
		super();
		this.bMethod = bMethd;
	}

	public DependencyDataCollector dependencyTreeGenerator() {
		blockList = bMethod.getBlockList();
		blockList.remove(blockList.size() - 1);
		dependencyDataCollector = new DependencyDataCollector();
		for (BBlock blocks : blockList) {
			// for(BInstruction instr: blocks.getInstructions()){
			ArrayList<BInstruction> instrutionList = blocks.getInstructions();

			for (int index = 0; index < instrutionList.size(); index++) {
				if (instrutionList.get(index).getOpcode() == 96
						|| instrutionList.get(index).getOpcode() == 97) {
					String tempVar = "temp" + tempVarCounter;

					dependencyDataCollector.generatePCAndTempVarMapping(
							instrutionList.get(index).getLineNumber(), tempVar);

					if (instrutionList.get(index + 1).getOpcode() == 54) {
						dependencyDataCollector
								.generateTempVarAndLocalVarMapping(tempVar,
										(Integer) instrutionList.get(index + 1)
												.getOperand());
					} else {
						dependencyDataCollector
								.generateTempVarAndLocalVarMapping(tempVar, -1);
					}
					
					if (instrutionList.get(index - 1).getOpcodeCommand() == "iadd") {
						this.firstParameter = "temp"+ (tempVarCounter - 1);
					} else {
						this.firstParameter = instrutionList.get(index - 1)
								.getOpcodeCommand();
					}
					this.secondParameter = getSecondParameter(instrutionList,
							index, tempVarCounter, dependencyDataCollector);

					DependentObject dependentObject = new DependentObject(
							firstParameter, secondParameter, tempVar,
							arithmaticOperation);

					dependencyDataCollector.addDependentObject(dependentObject);
					tempVarCounter++;
				}
			}
		}
		return dependencyDataCollector;
	}

	private Object getSecondParameter(ArrayList<BInstruction> instrutionList,
			int index, int tempVarCounter, DependencyDataCollector dependencyDataCollector) {
		Object secondParameter;
		HashMap<Integer, String> tempVarAndPC = dependencyDataCollector
				.getTempVarAndPCMapping();
		if (instrutionList.get(index - 1).getOpcode() == 96) {
			if (instrutionList.get(index - 2).getOpcode() == 96) {
				secondParameter = "temp"+ (tempVarCounter - 2);
			} else {
				secondParameter = instrutionList.get(index - 4)
						.getOpcodeCommand();
			}
		} else {
			if (instrutionList.get(index - 2).getOpcode() == 96) {
				secondParameter = "temp"+ (tempVarCounter - 1);
			} else {
				secondParameter = instrutionList.get(index - 2)
						.getOpcodeCommand();
			}
		}

		return secondParameter;
	}

}

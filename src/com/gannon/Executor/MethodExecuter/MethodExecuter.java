package com.gannon.Executor.MethodExecuter;

import java.util.ArrayList;

import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.JVMStackSingleton;
import com.gannon.Utility.ByteCodeUtility;

public class MethodExecuter {
	private BMethod method;
	private BFrame activeFrame;
	private Integer returnValue;


	public MethodExecuter() {
		super();
	}

	public MethodExecuter(BMethod method) {
		super();
		this.method = method;
	}

	public MethodExecuter(BFrame actvFrame) {
		super();
		this.activeFrame = actvFrame;
		this.method = activeFrame.getMethod();
	}

	public Integer getReturnValue() {
		return returnValue;
	}

	// For the first method call PC = 0 but for method calling return,
	// It will be PC value stored in BFrame object
	public void execute() {
		int pc = this.activeFrame.getPC();
		// Create label-pc and pc-Instruction mapping
		ByteCodeUtility mappingUtility = new ByteCodeUtility();

		this.activeFrame.setLabelMap(mappingUtility.createLabelMapping(this.activeFrame.getMethod()));
		this.activeFrame.setInstructionMap((mappingUtility.createInstrPCMapping(this.activeFrame.getMethod())));

		ArrayList<BInstruction> instructions = getInstructionsforActiveMethod(this.activeFrame);

		for (pc = pc; pc < instructions.size(); pc = this.activeFrame.getPC()) {
			returnValue = (Integer) instructions.get(pc).execute(
					this.activeFrame);
			// In case of Bytecode return, we will unload the method frame
			// from JVMStack
			// in bytecode execute() method it self, so check the JVMStack
			// size before getting new instruction
			// for execute.
			if (JVMStackSingleton.getInstance().size() != 0) {
				this.activeFrame = JVMStackSingleton.getInstance()
						.getActiveBFrame();
				this.method = this.activeFrame.getMethod();
				instructions = getInstructionsforActiveMethod(this.activeFrame);
			}
			else {
				break;
			}
		}
	}

	// for each input, the method returns the ID list of instructions have been executed
	public void getExecutionTrace() {
		ArrayList<Integer> executedInstructionID = new ArrayList<Integer>();
		int pc = this.activeFrame.getPC();
		// Create label-pc and pc-Instruction mapping
		ByteCodeUtility mappingUtility = new ByteCodeUtility();

		this.activeFrame.setLabelMap(mappingUtility.createLabelMapping(this.activeFrame.getMethod()));
		this.activeFrame.setInstructionMap((mappingUtility.createInstrPCMapping(this.activeFrame.getMethod())));

		ArrayList<BInstruction> instructions = getInstructionsforActiveMethod(this.activeFrame);

		for (pc = pc; pc < instructions.size(); pc = this.activeFrame.getPC()) {
			returnValue = (Integer) instructions.get(pc).execute(
					this.activeFrame);
			//executedBytecodeList.add(instructions.get(pc).getOpcodeCommand());
			// In case of Bytecode return, we will unload the method frame
			// from JVMStack
			// in bytecode execute() method it self, so check the JVMStack
			// size before getting new instruction
			// for execute.
			if (JVMStackSingleton.getInstance().size() != 0) {
				this.activeFrame = JVMStackSingleton.getInstance()
						.getActiveBFrame();
				this.method = this.activeFrame.getMethod();
				instructions = getInstructionsforActiveMethod(this.activeFrame);
			}
			else {
				break;
			}
		}

	}

	public BMethod getMethod() {
		return method;
	}

	public void setMethod(BMethod method) {
		this.method = method;
	}

	public BFrame getActiveFrame() {
		return activeFrame;
	}

	public void setActiveFrame(BFrame activeFrame) {
		this.activeFrame = activeFrame;
	}

	public ArrayList<BInstruction> getInstructionsforActiveMethod(BFrame activeFrame){
		ArrayList<BInstruction> instructionList = new ArrayList<BInstruction>();
		BMethod activeMethod = activeFrame.getMethod();
		ArrayList<BBlock> labelList = activeMethod.getBlockList();
		for(BBlock labels: labelList){
			instructionList.addAll(labels.getInstructions());
		}

		return instructionList;
	}
}

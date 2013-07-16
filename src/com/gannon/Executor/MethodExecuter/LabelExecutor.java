package com.gannon.Executor.MethodExecuter;

import java.util.ArrayList;

import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.JVMStackSingleton;

public class LabelExecutor {
	private ArrayList<BInstruction> instructionList;
	private Object returnValue;
	private BFrame activeFrame;
	private Integer PC;

	public LabelExecutor(ArrayList<BInstruction> instructionList,
			BFrame activeFrame) {
		super();
		this.instructionList = instructionList;
		this.activeFrame = activeFrame;
		this.PC = activeFrame.getPC();
	}

	public Object executeLabel() {
		System.out.println("this.activeFrame.getPC()   "
				+ this.activeFrame.getPC());

		// for (int pc = PC; pc < instructionList.size(); pc =
		// this.activeFrame.getPC()) {
		// System.out.println("Instruction: "
		// + instructionList.get(pc).getOpcodeCommand());
		// returnValue = (Integer) instructionList.get(pc).execute(
		// this.activeFrame);
		// System.out.println("PC value "+PC);
		// }

		for (BInstruction insrt : instructionList) {
			returnValue = (Integer) insrt.execute(this.activeFrame);

			if (JVMStackSingleton.getInstance().size() != 0) {
				System.out
						.println("check made for JVMStackSingleton, size is not zero.");
				this.activeFrame = JVMStackSingleton.getInstance()
						.getActiveBFrame();
				ArrayList<BBlock> labelList = this.activeFrame.getMethod()
						.getBlockList();
				for (int newIndex = 0; newIndex < labelList.size() - 1; newIndex++) {
					labelList = this.activeFrame.getMethod().getBlockList();
				}
			}
		}
			return returnValue;		
	}
}

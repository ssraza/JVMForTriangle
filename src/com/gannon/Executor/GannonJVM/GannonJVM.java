package com.gannon.Executor.GannonJVM;

import java.util.ArrayList;

import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Utility.ByteCodeUtility;

public class GannonJVM {
	private BFrame activeFrame;

	public GannonJVM() {
		super();
	}

	public GannonJVM(BFrame actvFrame) {
		super();
		this.activeFrame = actvFrame;
	}

	// For the first method call PC = 0 but for method calling return,
	// It will be PC value stored in BFrame object
	public Object execute() {
		Object result=null;
		BMethod method = activeFrame.getMethod();
		ArrayList<BInstruction> instructions = method.getInstructions();
		int pc=activeFrame.getPC();// points to next executing instruction
		
		//before executing push active frame to stack
		JVMStackSingleton.getInstance().pushFrame(activeFrame);
		

		while (activeFrame.getPC()!=-1) {
			BInstruction bInstruction = instructions.get(activeFrame.getPC());
			result= bInstruction.execute(activeFrame);
			// In case of Bytecode return, we will unload the method frame
			// from JVMStack in bytecode execute() method it self, so check the JVMStack
			// size before getting new instruction for execute.
			/*if (JVMStackSingleton.getInstance().size() != 0) {
				activeFrame = JVMStackSingleton.getInstance()
						.getActiveBFrame();
				method = activeFrame.getMethod();
				instructions = method.getInstructions();
			}
			else {
				break;
			}*/
			System.out.println(activeFrame.getPC());
		}
		return result;
	}


	public BFrame getActiveFrame() {
		return activeFrame;
	}

	public void setActiveFrame(BFrame activeFrame) {
		this.activeFrame = activeFrame;
	}

}

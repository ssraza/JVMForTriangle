package com.gannon.jvm;

import java.util.ArrayList;

import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.Utility.ByteCodeUtility;
import com.gannon.jvm.instructions.BInstruction;

public class MethodExecutor {
	public MethodExecutor() {
		super();
	}

	// assume activeFrame is saved in JVM stack
	public Object execute(JVMStackSingleton jvmStack) {
		Object result = null;
		BFrame activeFrame = jvmStack.peekActivekFrame();
		BMethod method = activeFrame.getMethod();
		ArrayList<BInstruction> instructions = method.getInstructions();

		// flag pc =-1 points to next executing instruction
		// the flag is set in return instruction
		while (activeFrame.getPC() != -1) {
			BInstruction bInstruction = instructions.get(activeFrame.getPC());
			result = bInstruction.execute(activeFrame);
			// In case of Bytecode return, we will unload the method frame
			// from JVMStack in bytecode execute() method it self, so check the
			// JVMStack size before getting new instruction for execute.
			/*
			 * if (JVMStackSingleton.getInstance().size() != 0) { activeFrame =
			 * JVMStackSingleton.getInstance() .getActiveBFrame(); method =
			 * activeFrame.getMethod(); instructions = method.getInstructions();
			 * } else { break; }
			 */
			System.out.println(activeFrame.getPC());
		}

		//post execution job, need to method to method calling
		if (activeFrame.getPC() == -1) {
			postExecution(jvmStack, result);
		}

		return result;
	}

	//after execution, be sure to push the result to the caller
	private void postExecution(JVMStackSingleton jvmStack, Object result) {
		jvmStack.popActivekFrame();
		if (jvmStack.size() != 0) {
			jvmStack.peekActivekFrame().getOperandStack().push(result);
		}

	}

}

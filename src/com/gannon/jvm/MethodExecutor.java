package com.gannon.jvm;

import java.util.ArrayList;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.PNode;
import com.gannon.jvm.data.dependency.NonPredicateNode;
import com.gannon.jvm.data.dependency.Path;
import com.gannon.jvm.data.dependency.PredicateNode;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;

public class MethodExecutor {
	//save execution path
	private Path executedPath=new Path();

	public MethodExecutor() {
		super();
	}

	// assume activeFrame is saved in JVM stack
	public Object execute(JVMStackSingleton jvmStack) {
		Object runTimePredicateResult = null;
		BFrame activeFrame = jvmStack.peekActiveFrame();
		BMethod method = activeFrame.getMethod();
		ArrayList<BInstruction> instructions = method.getInstructions();

		// flag pc =-1 points to next executing instruction
		// the flag is set in return instruction
		while (activeFrame.getPC() != -1) {
			BInstruction bInstruction = instructions.get(activeFrame.getPC());
			runTimePredicateResult = bInstruction.execute(activeFrame);

			//save runtime execution results for predicate statements to node
			saveRuntimePredicateResult(runTimePredicateResult, bInstruction);

			// In case of Bytecode return, we will unload the method frame
			// from JVMStack in bytecode execute() method it self, so check the
			// JVMStack size before getting new instruction for execute.
			/*
			 * if (JVMStackSingleton.getInstance().size() != 0) { activeFrame =
			 * JVMStackSingleton.getInstance() .getActiveBFrame(); method =
			 * activeFrame.getMethod(); instructions = method.getInstructions();
			 * } else { break; }
			 */

		}

		//post execution job, needed for method to method calling
		if (activeFrame.getPC() == -1) {
			postExecution(jvmStack, runTimePredicateResult);
		}
		//ideally, only predicate statements will results, e.g., true or false;
		return runTimePredicateResult;
	}

	//save path with predicate results if the instruction is predicateInstruction
	private void saveRuntimePredicateResult(Object result,
			BInstruction bInstruction) {
		if(bInstruction instanceof BPredicateInstruction){
			PredicateNode pNode = new PredicateNode(bInstruction);
			executedPath.add(pNode);
			pNode.setRunTimePredicateValue((Boolean)result);
		}else{
			executedPath.add(new NonPredicateNode(bInstruction));
		}
	}

	//after execution, be sure to push the result to the caller
	private void postExecution(JVMStackSingleton jvmStack, Object result) {
		jvmStack.popActivekFrame();
		if (jvmStack.size() != 0) {
			jvmStack.peekActiveFrame().getOperandStack().push(result);
		}

	}

	public Path getExecutedPath() {
		return executedPath;
	}

	//don't call this method, it is only used by writing test cases
	public ArrayList<Integer> getExecutedInsIDs( ){
		return getExecutedPath().getExecutedInsIDs();
	}
}

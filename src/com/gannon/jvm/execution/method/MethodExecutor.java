package com.gannon.jvm.execution.method;

import java.util.ArrayList;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;
import com.gannon.jvm.progam.path.NonPredicateNode;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.ConstantsUtility;

public class MethodExecutor {
	// save execution path
	private TestPath executedPath = new TestPath();

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
		while (activeFrame.getLineNumber() != -1) {
			BInstruction bInstruction = instructions.get(activeFrame.getLineNumber() - 1);
			//System.out.println(bInstruction.getOpCodeCommand());
			runTimePredicateResult = bInstruction.execute(activeFrame);

			// save runtime execution results for predicate statements to node
			saveRuntimePredicateResult(runTimePredicateResult, bInstruction);

			// In case of Bytecode return, we will unload the method frame
			// from JVMStack in bytecode execute() method it self, so check the
			// JVMStack size before getting new instruction for execute.

			if (JVMStackSingleton.getInstance().size() != 0) {
				activeFrame = JVMStackSingleton.getInstance().peekActiveFrame();
				method = activeFrame.getMethod();
				instructions = method.getInstructions();
			} else {
				break;
			}

		}
		// post execution job, needed for method to method calling
		if (activeFrame.getLineNumber() == -1) {
			postExecution(jvmStack, runTimePredicateResult);
		}
		// ideally, only predicate statements will results, e.g., true or false;
		return runTimePredicateResult;
	}

	// save path with predicate results if the instruction is
	// predicateInstruction
	private void saveRuntimePredicateResult(Object result, BInstruction bInstruction) {
		if (bInstruction instanceof BPredicateInstruction) {
			PredicateNode pNode = new PredicateNode(bInstruction);
			executedPath.add(pNode);
			pNode.setActualPredicateResult((Boolean)result.equals(true)?ConstantsUtility.EXPECTED_TRUE: ConstantsUtility.EXPECTED_FALSE);
		} else {
			executedPath.add(new NonPredicateNode(bInstruction));
		}
	}

	// after execution, be sure to push the result to the caller
	private void postExecution(JVMStackSingleton jvmStack, Object result) {
		jvmStack.popActivekFrame();
		if (jvmStack.size() != 0) {
			jvmStack.peekActiveFrame().getOperandStack().push(result);
		}
	}

	public TestPath getExecutedPath() {
		return executedPath;
	}

	// don't call this method, it is only used by writing test cases
	public ArrayList<Integer> getExecutedInsIDs() {
		return getExecutedPath().getExecutedInsIDs();
	}

}

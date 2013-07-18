package com.gannon.jvm;

import java.util.ArrayList;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;
import com.gannon.treeStructure.Node;
import com.gannon.treeStructure.NonPredicateNode;
import com.gannon.treeStructure.Path;
import com.gannon.treeStructure.PredicateNode;

public class MethodExecutor {
	//save execution path a
	private Path path=new Path();

	public MethodExecutor() {
		super();
	}

	// assume activeFrame is saved in JVM stack
	public Object execute(JVMStackSingleton jvmStack) {
		Object result = null;
		BFrame activeFrame = jvmStack.peekActiveFrame();
		BMethod method = activeFrame.getMethod();
		ArrayList<BInstruction> instructions = method.getInstructions();

		// flag pc =-1 points to next executing instruction
		// the flag is set in return instruction
		while (activeFrame.getPC() != -1) {


			BInstruction bInstruction = instructions.get(activeFrame.getPC());
			result = bInstruction.execute(activeFrame);

			//save path with predicate results if the instruction is predicateInstruction
			if(bInstruction instanceof BPredicateInstruction){
				path.add(new PredicateNode(bInstruction, (Boolean)result));
			}else{
				path.add(new NonPredicateNode(bInstruction));
			}

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
			jvmStack.peekActiveFrame().getOperandStack().push(result);
		}

	}

	public ArrayList<Integer> getExecutedInsIDs() {
		ArrayList<Integer> ids=new ArrayList<Integer>();
		for(Node node: path.getNodes()){
			ids.add(node.getIns().getLineNumber());
		}
		return ids;
	}
}

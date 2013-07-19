package com.gannon.jvm.data.dependency;

import java.util.ArrayList;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.JVMStackSingleton;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;
import com.gannon.jvm.progam.path.NonPredicateNode;
import com.gannon.jvm.progam.path.Path;
import com.gannon.jvm.progam.path.PredicateNode;

public class DependencyAnalyzer {
	private RelationCollector dependency;
	private ArrayList<Relation> listOfDependencies;
	public DependencyAnalyzer(Path path) {
		super();
	}

	// assume activeFrame is saved in JVM stack
	public ArrayList<Relation> execute(JVMStackSingleton jvmStack) {
		BFrame activeFrame = jvmStack.peekActiveFrame();
		BMethod method = activeFrame.getMethod();
		ArrayList<BInstruction> instructions = method.getInstructions();
		dependency = new RelationCollector();
		for (BInstruction bInstruction : instructions) {
			bInstruction.analyzing(dependency);
		}
		listOfDependencies = dependency.getListOfTrees();
		return listOfDependencies;
	}

	// save path with predicate results if the instruction is
	// predicateInstruction
	private void saveRuntimePredicateResult(Object result,
			BInstruction bInstruction) {
		if (bInstruction instanceof BPredicateInstruction) {
			PredicateNode pNode = new PredicateNode(bInstruction);
			executedPath.add(pNode);
			pNode.setRunTimePredicateValue((Boolean) result);
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

	public Path getExecutedPath() {
		return executedPath;
	}

	// don't call this method, it is only used by writing test cases
	public ArrayList<Integer> getExecutedInsIDs() {
		return getExecutedPath().getExecutedInsIDs();
	}
}

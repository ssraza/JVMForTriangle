package com.gannon.jvm.execution.path;

import java.util.ArrayList;

import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.instructions.BPredicateInstruction;
import com.gannon.jvm.instructions.Return;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;

public class PathExecutor {

	public PathExecutor() {
		super();
	}

	public Object execute(PathFrame pathFrame) {
		boolean endOfPath = false;
		Object result = null;
		TestPath path = pathFrame.getTestPath();
		for (;;) {
			for (Node node : path.getNodes()) {
				result = node.getInstruction().execute(pathFrame);

				// stop if reach end of the program
				if (node.getInstruction() instanceof Return) {
					endOfPath = true;
					break;
				}

				// we only concern of predicate node, e.g., a>b
				if (node.getInstruction() instanceof BPredicateInstruction) {
					((PredicateNode) node).setActualPredicateResult((Boolean) result);

					// if the actual predicate result is not equal to expected
					// results and the predicate is not a set as ignored
					// we need to generate a new value
					if (!((PredicateNode) node).hasPassed() && !((PredicateNode) node).isIgnore()) {
						System.out.println((PredicateNode) node);
						// apply rules to get new input newA, newB, newC
						int newA = 7, newB = 7, newC = 7;
						ArrayList<Object> newInput = new ArrayList<Object>();
						newInput.add(0);//reference
						newInput.add(newA);
						newInput.add(newB);
						newInput.add(newC);

						// set ignore flag in the node so next time to skip RE-Evaluate the
						// predicate instruction
						((PredicateNode) node).setIgnore(true);
						pathFrame.setLocalVariableTable(new BLocalVarTable(newInput));

						// quit the current execution and re-run the path with
						// new inputs
						break;
					}

				}
			}
			if (endOfPath) {
				break;
			}
		}
		return result;
	}
}

package com.gannon.jvm.execution.path;

import java.util.ArrayList;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.JVMStackSingleton;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;

public class PathExecutor {

	public PathExecutor() {
		super();
	}

	// assume activeFrame is saved in JVM stack
	public Object execute(PathFrame pathFrame) {
		TestPath path=pathFrame.getTestPath();
		for (Node node : path.getNodes()) {
			Object result=node.getInstruction().execute(pathFrame);
			if (node.getInstruction() instanceof BPredicateInstruction){
				((PredicateNode)node).setRunTimePredicateValue((Boolean)result);
			}
		}

		return null;
	}
}

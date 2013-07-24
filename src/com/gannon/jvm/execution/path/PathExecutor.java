package com.gannon.jvm.execution.path;

import java.util.ArrayList;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
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

	public Object execute(PathFrame pathFrame) {
		Object result = null;
		TestPath path=pathFrame.getTestPath();
		for (Node node : path.getNodes()) {
			result=node.getInstruction().execute(pathFrame);
			if (node.getInstruction() instanceof BPredicateInstruction){
				((PredicateNode)node).setRunTimePredicateValue((Boolean)result);
				if(!((PredicateNode)node).isNodePass()){
					//apply rule and keep execution
					//get new input newA, newB, newC
					int newA=7, newB=7, newC=7;
					ArrayList<Object> newInput=new ArrayList<Object>();
					newInput.add(newA);
					newInput.add(newB);
					newInput.add(newC);
					pathFrame.setLocalVariableTable(new BLocalVarTable(newInput));
					
				}
			}
		}

		return result;
	}
}

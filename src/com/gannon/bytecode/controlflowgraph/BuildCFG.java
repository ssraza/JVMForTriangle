package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import com.gannon.asm.components.BMethod;


// the class has not been tested.
public class BuildCFG {
	ArrayList<BMethod> methods = new ArrayList<BMethod>();
	BMethod startMethod = new BMethod();

	public BuildCFG(ArrayList<BMethod> methods, BMethod startMethod) {
		this.methods = methods;
		this.startMethod = startMethod;
	}

	public CGraph getResultGraph() {
		CFGMethod cfgMethod=new CFGMethod(startMethod);
		CGraph resultGraph = cfgMethod.buildGraph();

		Frame currentFrame = new Frame(startMethod, 0);
		Stack<Frame> stack = new Stack<Frame>();

		if (cfgMethod != null) {
			stack.push(currentFrame);
		}

		while (!stack.empty()) {
			CFGMethod currentMGI = new CFGMethod(currentFrame.getMethod());
			CGraph currentGraph=currentMGI.buildGraph();
			ArrayList<CNode> currentBlocks=new ArrayList<CNode>(currentGraph.getCNodes());
			int blockId = currentFrame.getBlockId();
			for (int i = blockId; i < currentBlocks.size(); i++) {
				// always point to next block
				CNode b = currentBlocks.get(i); 
				if (b.hasInvoke()) { 
					String methodName = currentFrame.getMethod().getName();
					// It will give the name including MethodNAme, Desc etc.


					BMethod calleeMethod = getCurrentMethod(methodName);
					if (calleeMethod != null) {
						currentFrame.setBlockId(i + 1);
						Frame calleeFrame = new Frame(calleeMethod, 0);
						stack.push(calleeFrame);
						CFGMethod calleeCfgMethod = new CFGMethod(calleeMethod);
						CGraph calleeMethodGraph =calleeCfgMethod.buildGraph();
						resultGraph.mergeCallingGraph(calleeMethodGraph, b);
						currentFrame = calleeFrame;
					}
					break;
				}
				if (i == currentBlocks.size() - 1) {
					stack.pop();
					if (!stack.empty()) {
						currentFrame = stack.peek();
					}
				}
			}
		}
		return resultGraph;
	}

	public BMethod getCurrentMethod(String mName) {
		for (int i = 0; i < methods.size(); i++) {
			String methodName = methods.get(i).getName();
			if (methodName.endsWith(mName)) {
				return methods.get(i);
			}
		}
		return null;
	}
}

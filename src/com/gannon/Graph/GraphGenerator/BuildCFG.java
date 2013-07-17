package com.gannon.Graph.GraphGenerator;

import java.util.ArrayList;
import java.util.Stack;

import com.gannon.Graph.GraphObjects.Block;
import com.gannon.Graph.GraphObjects.Frame;
import com.gannon.Graph.GraphObjects.Graph;
import com.gannon.Graph.GraphObjects.GraphObjectBuilder;
import com.gannon.asm.component.BMethod;

public class BuildCFG {
	ArrayList<BMethod> methods = new ArrayList<BMethod>();
	BMethod startMethod = new BMethod();

	public BuildCFG(ArrayList<BMethod> methods, BMethod startMethod) {
		this.methods = methods;
		this.startMethod = startMethod;
	}

	public Graph getResultGraph() {
		GraphObjectBuilder graphGenerator = new GraphObjectBuilder(
				startMethod);
		ArrayList<Block> resultantBlocks = graphGenerator.buildBlocks();

		Graph resultGraph = graphGenerator.buildGraph(resultantBlocks);

		Frame currentFrame = new Frame(startMethod, 0);
		Stack<Frame> stack = new Stack<Frame>();

		if (graphGenerator != null) {
			stack.push(currentFrame);
		}

		while (!stack.empty()) {
			// ArrayList<Block> currentBlocks = currentFrame.getMethod();
			System.out.println("!!!!!!!!!!!!!!!!!!");
			GraphObjectBuilder currentMGI = new GraphObjectBuilder(
					currentFrame.getMethod());
			ArrayList<Block> currentBlocks = currentMGI.buildBlocks();
			int blockId = currentFrame.getBlockId();
			for (int i = blockId; i < currentBlocks.size(); i++) {
				// always point to next block
				Block b = currentBlocks.get(i);
				if (b.hasInvoke()) {
					String methodName = (String) b.getLastLineInstruction().getOperand();
					System.out.println("---------------");
					System.out.println(b.getLastLineInstruction().getOperand());

					BMethod calleeMethod = getCurrentMethod(methodName);
					if (calleeMethod != null) {
						currentFrame.setBlockId(i + 1);
						Frame calleeFrame = new Frame(calleeMethod, 0);
						stack.push(calleeFrame);
						GraphObjectBuilder newGraph = new GraphObjectBuilder(
								calleeMethod);
						ArrayList<Block> calleeBlocks = newGraph.buildBlocks();
						Graph calleeMethodGraph = newGraph
								.buildGraph(calleeBlocks);
						resultGraph.mergeGraph(calleeMethodGraph, b);
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

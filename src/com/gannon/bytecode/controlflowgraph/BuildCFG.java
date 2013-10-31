package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;
import java.util.Stack;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BInstruction;


// the class has not been tested.
public class BuildCFG {
	private BClass bClass;
	BMethod startMethod = new BMethod();

	public BuildCFG(BClass bClass, BMethod startMethod) {
		this.bClass = bClass;
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
					currentFrame.setBlockId(i + 1);
					BInstruction ivInstruction = b.getBlock().getFirstInstruction();
					System.out.println("getOwner " + ivInstruction.getOwner());
					
					// It will give the name including MethodNAme, Desc etc.
					BMethod calleeMethod = null;
					
					if (ivInstruction.getOpCodeCommand().equals("invokevirtual") 
							&& ivInstruction.getOwner().contains(this.bClass.getClassName())
							&& ivInstruction.getStringOperand().equals(this.startMethod.getName()) ==  false) {
						calleeMethod = ivInstruction.getNextMethod(this.bClass);
						if (calleeMethod != null) {
							Frame calleeFrame = new Frame(calleeMethod, 0);
							stack.push(calleeFrame);
							CFGMethod calleeCfgMethod = new CFGMethod(calleeMethod);
							
							CGraph calleeMethodGraph =calleeCfgMethod.buildGraph();
							resultGraph.mergeCallingGraph(calleeMethodGraph, b);
							//System.out.print(resultGraph.printNodesToString());
							//System.out.print(resultGraph.printEdgesToString());
							currentFrame = calleeFrame;
						}
					}
					else if (ivInstruction.getOpCodeCommand().equals("invokespecial") 
							|| ivInstruction.getOpCodeCommand().equals("invokevirtual")
							&& ivInstruction.getStringOperand().equals(this.startMethod.getName()) ==  false) {
						String[] classStr = ivInstruction.getOwner().split("/");
						BClass nextClass = BClassGenerator.getBClass(classStr[classStr.length - 1] + ".class");
						if (nextClass != null) {
							//BInvokeSpecial isInstruction = (BInvokeSpecial)ivInstruction;
							calleeMethod = ivInstruction.getNextMethod(nextClass, ivInstruction.getStringOperand());//(this.bClass);
							if (calleeMethod != null) {
								Frame calleeFrame = new Frame(calleeMethod, 0);
								stack.push(calleeFrame);
								BuildCFG calleeCfgGraph = new BuildCFG(nextClass, calleeMethod);
								
								CGraph calleeMethodGraph =calleeCfgGraph.getResultGraph();
								resultGraph.mergeCallingGraph(calleeMethodGraph, b);
								//System.out.print(resultGraph.printNodesToString());
								//System.out.print(resultGraph.printEdgesToString());
								currentFrame = calleeFrame;
							}
						}
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
}

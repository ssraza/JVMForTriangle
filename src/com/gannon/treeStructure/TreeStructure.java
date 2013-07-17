package com.gannon.treeStructure;

import java.util.ArrayList;
import java.util.HashMap;

import com.gannon.ASM.BytecodeClassGenerator.BClassGenerator;
import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.Main.InterfaceAPISingleton;
import com.gannon.jvm.instructions.BInstruction;

public class TreeStructure {
		
	public static void main(String[] arg) {
		System.out.println("Start of Execution");
		BClassGenerator clasGen = new BClassGenerator(
				"Hello.class");
		clasGen.getBClass();
		BClass loadedClass = clasGen.getbFactory().getBClass();
		InterfaceAPISingleton.getInstance().setbClass(loadedClass);
		
		ArrayList<Tree> treeStructure = dependencyGenerator(loadedClass.getMethods().get(3));

		for(Tree treeStructre : treeStructure){
			System.out.println("=======================");
		System.out.println(treeStructre.getRootNode().getNodeName());
		System.out.println(treeStructre.getLeftLeaf().getNodeName());
		System.out.println(treeStructre.getArithmaticOperation());
		System.out.println(treeStructre.getRightLeaf().getNodeName());
		}
	}

	public static ArrayList<Tree> dependencyGenerator(BMethod bMethod) {
		
		ArrayList<BBlock> blockList = bMethod.getBlockList();
		blockList.remove(blockList.size() - 1);
		//dependencyDataCollector = new DependencyDataCollector();
		ArrayList<Tree> treeStructure = new ArrayList<Tree>();
		
		Tree tree;
		int tempVarCounter = 0; 
		// Iterate the blocks
		for (BBlock blocks : blockList) {
			// for(BInstruction instr: blocks.getInstructions()){
			ArrayList<BInstruction> instrutionList = blocks.getInstructions();
			// Iterate the instructions for a block
			for (int index = 0; index < instrutionList.size(); index++) {
				
				// check if opcode is iadd or ladd, if true then create a Tree object,
				// create a temp variable node for that iadd or ladd.
				// get the left leaf and right leaf (the values on which temp variable value depends)
				// if iadd is at index = 4
				// left leaf : 
				// take index = 3 instruction. if instruction = iadd, then get the temp variable
				// less than current temp variable
				// else get what ever instruction at index = 3.
				// Right leaf :
				// if instruction at index = 3 is iadd, then check if instruction at index = 2 is iadd or not
				// if instruction at index = 2 is iadd then, get the previous temp variable then 
				// the temp variable at index = 3
				// else if instruction at index = 2 is other than iadd then, take what ever instruction available 
				// at index = 0  
				if (instrutionList.get(index).getOpcode() == 96
						|| instrutionList.get(index).getOpcode() == 97) {
					
					String tempVar = "temp" + tempVarCounter;
					
					Node rootNode = new Node();
					Node leftLeaf = new Node();
					Node rightLeaf = new Node();
					
					rootNode.setNodeName(tempVar);
					
					HashMap<Integer, String> tempVarAndPCMapping = new HashMap<Integer, String>();

					tempVarAndPCMapping.put(index, tempVar);
					
					if (instrutionList.get(index - 1).getOpcodeCommand() == "iadd") {
						leftLeaf.setNodeName("temp"+ (tempVarCounter - 1));
					} else {
						leftLeaf.setNodeName(instrutionList.get(index - 1)
								.getOpcodeCommand());
					}
					rightLeaf = getSecondParameter(instrutionList,
							index, tempVarCounter, tempVarAndPCMapping);

					tree = new Tree(rootNode,leftLeaf,rightLeaf, instrutionList.get(index).getOpcodeCommand());

					treeStructure.add(tree);
					tempVarCounter++;
				}
			}
		}	
		return treeStructure;
	}

	// Right leaf :
	// if instruction at index = 3 is iadd, then check if instruction at index = 2 is iadd or not
	// if instruction at index = 2 is iadd then, get the previous temp variable then 
	// the temp variable at index = 3
	// else if instruction at index = 2 is other than iadd then, take what ever instruction available 
	// at index = 0  
	public static Node getSecondParameter(ArrayList<BInstruction> instrutionList,
			int index, int tempVarCounter, HashMap<Integer, String> dependencyDataCollector) {
		Node rightLeaf = new Node();
		HashMap<Integer, String> tempVarAndPC = dependencyDataCollector;
		if (instrutionList.get(index - 1).getOpcode() == 96) {
			if (instrutionList.get(index - 2).getOpcode() == 96) {
				rightLeaf.setNodeName("temp"+ (tempVarCounter - 2));
			} else {
				rightLeaf.setNodeName(instrutionList.get(index - 4)
						.getOpcodeCommand());
			}
		} else {
			if (instrutionList.get(index - 2).getOpcode() == 96) {
				rightLeaf.setNodeName("temp"+ (tempVarCounter - 1));
			} else {
				rightLeaf.setNodeName(instrutionList.get(index - 2)
						.getOpcodeCommand());
			}
		}

		return rightLeaf;
	}

}

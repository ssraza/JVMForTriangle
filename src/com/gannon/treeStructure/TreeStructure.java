package com.gannon.treeStructure;

import java.util.ArrayList;
import java.util.HashMap;

public class TreeStructure {
		
	public static void main(String[] arg) {
		System.out.println("Start of Execution");
//		ByteCodeClassGenerator clasGen = new ByteCodeClassGenerator(
//				"Hello.class");
//		clasGen.cparser();
//		BClass loadedClass = clasGen.getbFactory().getBClass();
//		InterfaceAPISingleton.getInstance().setbClass(loadedClass);
		GeneratePathUtility utilityForPath = new GeneratePathUtility();
		
		
		ArrayList<Tree> treeStructure = dependencyGenerator(utilityForPath.getPath2());

		for(Tree treeStructre : treeStructure){
			System.out.println("=======================");
		System.out.println(treeStructre.getRootNode().getIns().getOpcodeCommand());
		System.out.println(treeStructre.getLeftLeaf().getIns().getOpcodeCommand());
		System.out.println(treeStructre.getArithmaticOperation());
		System.out.println(treeStructre.getRightLeaf().getIns().getOpcodeCommand());
		}
	}

	public static ArrayList<Tree> dependencyGenerator(Path path) {
		
		Tree tree;
		ArrayList<Tree> treeStructure = new ArrayList<Tree>();
		int tempVarCounter = 0; 
		int index = 0;
		int nodeId = 0;
		ArrayList<Node> nodeList = path.getListofNodes();
		
		for(Node node: nodeList){
				
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
				if (node.getIns().getOpcodeCommand() == "iadd") {
					
					String tempVar = "temp" + tempVarCounter;
					
					Node rootNode = new NonPredicateNode(nodeId++, node.getIns());
					Node leftLeaf = new NonPredicateNode(nodeId++, node.getIns());
					Node rightLeaf = new NonPredicateNode(nodeId++, node.getIns());
					
					rootNode.setNodeName(tempVar);
					
					HashMap<Integer, String> tempVarAndPCMapping = new HashMap<Integer, String>();

					tempVarAndPCMapping.put(index, tempVar);
					
					if (nodeList.get(index - 1).getNodeName() == "iadd") {
						leftLeaf.setNodeName("temp"+ (tempVarCounter - 1));
					} else {
						leftLeaf.setNodeName(nodeList.get(index - 1).getNodeName());
					}
					rightLeaf = getSecondParameter(nodeList,
							index, tempVarCounter, tempVarAndPCMapping);

					tree = new Tree(rootNode,leftLeaf,rightLeaf, nodeList.get(index - 1).getNodeName());

					treeStructure.add(tree);
					tempVarCounter++;
				}
					index++;				
		}	
		return treeStructure;
	}

	// Right leaf :
	// if instruction at index = 3 is iadd, then check if instruction at index = 2 is iadd or not
	// if instruction at index = 2 is iadd then, get the previous temp variable then 
	// the temp variable at index = 3
	// else if instruction at index = 2 is other than iadd then, take what ever instruction available 
	// at index = 0  
	public static Node getSecondParameter(ArrayList<Node> nodeList,
			int index, int tempVarCounter, HashMap<Integer, String> dependencyDataCollector) {
		Node rightLeaf = new NonPredicateNode();
		HashMap<Integer, String> tempVarAndPC = dependencyDataCollector;

		if (nodeList.get(index - 1).getNodeName() == "iadd") {
			if (nodeList.get(index - 2).getNodeName() == "iadd") {
				rightLeaf.setNodeName("temp"+ (tempVarCounter - 2));
			} else {
				rightLeaf.setNodeName(nodeList.get(index - 4).getNodeName());
			}
		} else {
			if (nodeList.get(index - 2).getNodeName() == "iadd") {
				rightLeaf.setNodeName("temp"+ (tempVarCounter - 1));
			} else {
				rightLeaf.setNodeName(nodeList.get(index - 2).getNodeName());
			}
		}
		return rightLeaf;
	}

}

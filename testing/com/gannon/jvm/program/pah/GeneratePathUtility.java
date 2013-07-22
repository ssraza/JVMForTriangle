package com.gannon.jvm.program.pah;

import com.gannon.jvm.instructions.*;
import com.gannon.jvm.progam.path.NonPredicateNode;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.PredicateNode;

public class GeneratePathUtility {


	private TestPath getPath1(){
		TestPath path = new TestPath();
		int counter = 0;
		
		path.setPathId(1);
		
		Node node0 = new NonPredicateNode(0,new BILoad(1));
		path.add(node0);
		
		Node node1 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node1);
		
		Node node2 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node2);
		
		Node node7 = new NonPredicateNode(counter++, new BIAdd());
		path.add(node7);
		
		Node node8 = new PredicateNode(counter++, new BIFicmpge(),false);
		path.add(node8);
		
		Node node24 = new NonPredicateNode(counter++, new BIConst_4());
		path.add(node24);
		
		Node node25 = new NonPredicateNode(counter++, new BIReturn());
		path.add(node25);
		
		return path;
	}
	
	
	//public ArrayList<BinaryTree> 
	
	
	public TestPath getPath2(){
		TestPath path = new TestPath();
		
		path.setPathId(2);
		int counter = 0;
		
		Node node0 = new NonPredicateNode(counter++, new BILoad(1));
		path.add(node0);
		
		Node node1 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node1);
		
		Node node2 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node2);
		
		Node node7 = new NonPredicateNode(counter++, new BIAdd());
		path.add(node7);
		
		Node node8 = new PredicateNode(counter++, new BIFicmpge(),false);
		path.add(node8);
		
		Node node3 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node3);
		
		Node node4 = new NonPredicateNode(counter++, new BILoad(1));
		path.add(node4);
		
		Node node5 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node5);
		
		Node node6 = new NonPredicateNode(counter++, new BIAdd());
		path.add(node6);
		
		Node node9 = new PredicateNode(counter++, new BIFicmpge(),false);
		path.add(node9);
		
		Node node10 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node10);
		
		Node node11 = new NonPredicateNode(counter++, new BILoad(1));
		path.add(node11);
		
		Node node12 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node12);
		
		Node node13 = new NonPredicateNode(counter++, new BIAdd());
		path.add(node13);
		
		Node node14 = new PredicateNode(counter++, new BIFicmpge(),false);
		path.add(node14);
		
		Node node15 = new NonPredicateNode(counter++, new BILoad(1));
		path.add(node15);
		
		Node node16 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node16);
		
		Node node17 = new PredicateNode(counter++, new BIFicmpne(),false);
		path.add(node17);
		
		Node node18 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node18);
		
		Node node19 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node19);
		
		Node node20 = new PredicateNode(counter++, new BIFicmpne(),false);
		path.add(node20);
		
		Node node21 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node21);
		
		Node node22 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node22);
		
		Node node23 = new PredicateNode(counter++, new BIFicmpne(),false);
		path.add(node23);
		
		Node node24 = new NonPredicateNode(counter++, new BIConst_1());
		path.add(node24);
		
		Node node25 = new NonPredicateNode(counter++, new BIReturn());
		path.add(node25);
//		
//		Node node6 = new NoPredicateNode();
//		node6.setNodeName("istore 2");
//		path.addNodeToList(node6);
		
		return path;
	}
}

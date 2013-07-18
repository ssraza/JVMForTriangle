package com.gannon.jvm.data.dependency;

import com.gannon.jvm.instructions.*;

public class GeneratePathUtility {


	private Path getPath1(){
		Path path = new Path();
		int counter = 0;
		
		path.setPathId(1);
		
		PNode node0 = new NonPredicateNode(0,new BILoad(1));
		path.add(node0);
		
		PNode node1 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node1);
		
		PNode node2 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node2);
		
		PNode node7 = new NonPredicateNode(counter++, new BIAdd());
		path.add(node7);
		
		PNode node8 = new PredicateNode(counter++, new BIFicmpge(),false);
		path.add(node8);
		
		PNode node24 = new NonPredicateNode(counter++, new BIConst_4());
		path.add(node24);
		
		PNode node25 = new NonPredicateNode(counter++, new BIReturn());
		path.add(node25);
		
		return path;
	}
	
	
	//public ArrayList<BinaryTree> 
	
	
	public Path getPath2(){
		Path path = new Path();
		
		path.setPathId(2);
		int counter = 0;
		
		PNode node0 = new NonPredicateNode(counter++, new BILoad(1));
		path.add(node0);
		
		PNode node1 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node1);
		
		PNode node2 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node2);
		
		PNode node7 = new NonPredicateNode(counter++, new BIAdd());
		path.add(node7);
		
		PNode node8 = new PredicateNode(counter++, new BIFicmpge(),false);
		path.add(node8);
		
		PNode node3 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node3);
		
		PNode node4 = new NonPredicateNode(counter++, new BILoad(1));
		path.add(node4);
		
		PNode node5 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node5);
		
		PNode node6 = new NonPredicateNode(counter++, new BIAdd());
		path.add(node6);
		
		PNode node9 = new PredicateNode(counter++, new BIFicmpge(),false);
		path.add(node9);
		
		PNode node10 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node10);
		
		PNode node11 = new NonPredicateNode(counter++, new BILoad(1));
		path.add(node11);
		
		PNode node12 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node12);
		
		PNode node13 = new NonPredicateNode(counter++, new BIAdd());
		path.add(node13);
		
		PNode node14 = new PredicateNode(counter++, new BIFicmpge(),false);
		path.add(node14);
		
		PNode node15 = new NonPredicateNode(counter++, new BILoad(1));
		path.add(node15);
		
		PNode node16 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node16);
		
		PNode node17 = new PredicateNode(counter++, new BIFicmpne(),false);
		path.add(node17);
		
		PNode node18 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node18);
		
		PNode node19 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node19);
		
		PNode node20 = new PredicateNode(counter++, new BIFicmpne(),false);
		path.add(node20);
		
		PNode node21 = new NonPredicateNode(counter++, new BILoad(2));
		path.add(node21);
		
		PNode node22 = new NonPredicateNode(counter++, new BILoad(3));
		path.add(node22);
		
		PNode node23 = new PredicateNode(counter++, new BIFicmpne(),false);
		path.add(node23);
		
		PNode node24 = new NonPredicateNode(counter++, new BIConst_1());
		path.add(node24);
		
		PNode node25 = new NonPredicateNode(counter++, new BIReturn());
		path.add(node25);
//		
//		Node node6 = new NoPredicateNode();
//		node6.setNodeName("istore 2");
//		path.addNodeToList(node6);
		
		return path;
	}
}

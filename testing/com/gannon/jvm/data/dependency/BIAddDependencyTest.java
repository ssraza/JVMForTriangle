package com.gannon.jvm.data.dependency;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import com.gannon.jvm.instructions.BIAdd;

public class BIAddDependencyTest {

	@Test
	public void testDependcy(){
		Stack<String> operandStack = new Stack<String>();
		operandStack.add("5");
		operandStack.add("9");

		RelationFrame dependency=new RelationFrame("com.gannon.asm.classgenerator.Triangle", "triangleType");
		dependency.setTempVariableStack(operandStack);

		BIAdd iadd=new BIAdd(2);
		iadd.analyzing(dependency);
		Relation actualTree=dependency.getRelations().get(4);
		actualTree.inorderBST();

		BinNode rightNode= new BinNode("9");
		BinNode leftNode= new BinNode("5");
		BinNode rootNode=new BinNode("100");
		Relation expectedTree=new Relation(rootNode, iadd);
		expectedTree.insertToLeft(leftNode);
		expectedTree.insertToRight(rightNode);
		expectedTree.inorderBST();

//		System.out.println(actualTree.getTheBTRootNode().getId());
//		System.out.println(actualTree.getLeftNode().getId());
//		System.out.println(actualTree.getRightNode().getId());
		assertEquals(expectedTree, actualTree);
	}

	/*@Test
	public void testDependcy2(){
		Stack<String> operandStack = new Stack<String>();
		operandStack.add("15");
		operandStack.add("9");

		RelationFrame dependency=new RelationFrame("com.gannon.asm.classgenerator.Triangle", "triangleType");
		dependency.setTempVariableStack(operandStack);

		BIAdd iadd=new BIAdd(2);
		iadd.analyzing(dependency);
		Relation actualTree=dependency.getRelations().get(0);
		//tree.inorderBST();

		BinNode rightNode= new BinNode("9");
		BinNode leftNode= new BinNode("15");
		BinNode rootNode=new BinNode("101");
		Relation expectedTree=new Relation(rootNode, iadd);
		expectedTree.insertToLeft(leftNode);
		expectedTree.insertToRight(rightNode);

		assertEquals(expectedTree, actualTree);
	}*/

}

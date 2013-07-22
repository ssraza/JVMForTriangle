package com.gannon.jvm.data.dependency;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.instructions.BIFicmpeq;
import com.gannon.jvm.instructions.BIFicmpge;

public class BIFicmpeqDependencyTest {
	@Test
	public void testDependcy(){
		Label newLabel = new Label();
		Stack<String> operandStack = new Stack<String>();
		operandStack.add("5");
		operandStack.add("5");

		RelationFrame dependency=new RelationFrame();
		dependency.setTempVariableStack(operandStack);

		BIFicmpeq ifEqual=new BIFicmpeq(newLabel, 2);
		ifEqual.analyzing(dependency);
		Relation actualTree=dependency.getRelations().get(0);
		actualTree.inorderBST();

		BinNode rightNode= new BinNode("5");
		BinNode leftNode= new BinNode("5");
		BinNode rootNode=new BinNode("100");
		Relation expectedTree=new Relation(rootNode, ifEqual);
		expectedTree.insertToLeft(leftNode);
		expectedTree.insertToRight(rightNode);
		expectedTree.inorderBST();

		assertEquals(expectedTree, actualTree);
	}

	

}

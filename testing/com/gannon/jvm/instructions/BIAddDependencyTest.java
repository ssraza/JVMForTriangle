package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Relation;
import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.progam.path.TestPath;

public class BIAddDependencyTest {

	@Test
	public void testDependcy() {
		Stack<String> operandStack = new Stack<String>();
		operandStack.add("5");
		operandStack.add("9");

		RelationFrame dependency = new RelationFrame();
		dependency.setTempVariableStack(operandStack);
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();

		BIAdd iadd = new BIAdd(2);
		iadd.analyzing(dependency);
		Relation actualTree = dependency.getRelations().getRelation(4);
		//actualTree.niceDisplay();

		BinNode rightNode = new BinNode("9");
		BinNode leftNode = new BinNode("5");
		BinNode rootNode = new BinNode("100");
		Relation expectedTree = new Relation(rootNode, iadd);
		expectedTree.insertToLeft(leftNode);
		expectedTree.insertToRight(rightNode);
		//expectedTree.niceDisplay();

		assertEquals(expectedTree, actualTree);
	}

	@Test
	public void testDependcyLevel2() {
		// set a stack
		Stack<String> operandStack = new Stack<String>();
		operandStack.add("1");
		operandStack.add("101");

		// set a frame
		RelationFrame dependency = new RelationFrame();
		dependency.setTempVariableStack(operandStack);
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();

		// a new relation
		BinNode rightNode1 = new BinNode("1");
		BinNode leftNode1 = new BinNode("2");
		BinNode rootNode1 = new BinNode("101");
		Relation tree9 = new Relation(rootNode1, new BILoad(2, 5));
		tree9.insertToLeft(leftNode1);
		tree9.insertToRight(rightNode1);
		dependency.getRelations().addRelation(tree9);

		// execution here
		BIAdd iadd = new BIAdd(2);
		iadd.analyzing(dependency);
		Relation actualTree = dependency.getRelations().getRelation(5);
		actualTree.niceDisplay();

		BinNode rightNode = new BinNode("101");
		BinNode leftNode = new BinNode("1");
		BinNode rootNode = new BinNode("100");
		Relation expectedTree = new Relation(rootNode, iadd);
		expectedTree.insertToLeft(leftNode);
		expectedTree.insertToRight(rightNode);
		// expectedTree.niceDisplay();

		// assertEquals(expectedTree, actualTree);
	}



}

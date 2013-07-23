package com.gannon.jvm.data.dependency;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.asm.components.BLabel;
import com.gannon.jvm.instructions.BIFicmpne;
import com.gannon.jvm.instructions.BPredicateInstruction;

public class BIFicmpneDependencyTest {

	@Test
	public void testDependency() {
		Label newLabel = new Label();
		BLabel label = new BLabel(newLabel);
		Stack<String> operandStack = new Stack<String>();
		operandStack.add("5");
		operandStack.add("8");

		RelationFrame dependency=new RelationFrame("com.gannon.asm.classgenerator.Triangle", "triangleType");
//		dependency.setClassName("com.gannon.asm.classgenerator.Triangle");
//		dependency.setMethodName("triangleType");
//		dependency.
		dependency.setTempVariableStack(operandStack);

		BPredicateInstruction ifNEqual=new BIFicmpne(label, 2);
		ifNEqual.analyzing(dependency);
		Relation actualTree=dependency.getRelations().get(4);
		actualTree.inorderBST();

		BinNode rightNode= new BinNode("8");
		BinNode leftNode= new BinNode("5");
		BinPredicateNode rootNode=new BinPredicateNode("100");
		Relation expectedTree=new Relation(rootNode, ifNEqual);
		
		expectedTree.insertToLeft(leftNode);
		expectedTree.insertToRight(rightNode);
		expectedTree.inorderBST();

		assertEquals(expectedTree, actualTree);
	}

}

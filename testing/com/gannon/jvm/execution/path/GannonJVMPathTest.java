package com.gannon.jvm.execution.path;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BLabel;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.execution.method.GannonJVM;
import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.instructions.BIConst_4;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BILoad;
import com.gannon.jvm.instructions.BIReturn;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.NonPredicateNode;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;

public class GannonJVMPathTest {

	@Test
	public void testMethodTriangleTypeNotATriangle() {
		GannonJVMPath jvm=new GannonJVMPath();

		//create input
		TestPath path = new TestPath();
		path.setPathId(1);

		Node node0 = new NonPredicateNode(new BILoad(1,0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2,1));
		path.add(node1);

		Node node2 = new NonPredicateNode( new BILoad(3,3));
		path.add(node2);

		Node node7 = new NonPredicateNode(new BIAdd(6));
		path.add(node7);

		BLabel lable = new BLabel(new Label());
		PredicateNode node8 = new PredicateNode(new BIFicmpge(lable,7));
		node8.setExpectedPredicateValue(false);
		path.add(node8);

		Node node24 = new NonPredicateNode(new BIConst_4(9));
		path.add(node24);

		Node node25 = new NonPredicateNode( new BIReturn(10));
		path.add(node25);

		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		ArrayList<Object> input = new ArrayList<>();
		input.add(4);
		input.add(6);
		input.add(1);
		//assertion
		assertEquals(new Integer(4), jvm.run(path,input));
	}

	@Test
	public void testMethodTriangleTypeBreak() {
		GannonJVMPath jvm=new GannonJVMPath();

		//create input
		TestPath path = new TestPath();
		path.setPathId(1);

		Node node0 = new NonPredicateNode(new BILoad(1,0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2,1));
		path.add(node1);

		Node node2 = new NonPredicateNode( new BILoad(3,3));
		path.add(node2);

		Node node7 = new NonPredicateNode(new BIAdd(6));
		path.add(node7);

		BLabel lable = new BLabel(new Label());
		PredicateNode node8 = new PredicateNode(new BIFicmpge(lable,7));
		node8.setExpectedPredicateValue(false);
		path.add(node8);

		Node node24 = new NonPredicateNode(new BIConst_4(9));
		path.add(node24);

		Node node25 = new NonPredicateNode( new BIReturn(10));
		path.add(node25);

		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		ArrayList<Object> input = new ArrayList<>();
		input.add(4);
		input.add(6);
		input.add(1);
		//assertion
		assertEquals(new Integer(4), jvm.run(path,input));
	}
}

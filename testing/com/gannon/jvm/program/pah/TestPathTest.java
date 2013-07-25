package com.gannon.jvm.program.pah;

import static org.junit.Assert.*;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BLabel;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.instructions.BIConst_2;
import com.gannon.jvm.instructions.BIFicmpeq;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BIFicmpne;
import com.gannon.jvm.instructions.BILoad;
import com.gannon.jvm.instructions.BIReturn;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.NonPredicateNode;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;

public class TestPathTest {

	@Test
	public void testEquals() {
		assertEquals(TrianglePathBuilderUtility.createPathID1(),
				TrianglePathBuilderUtility.createPathID1());
	}

	@Test
	public void testEqualsNotEqual() {
		// use Unit 4.11 version
		assertNotEquals(TrianglePathBuilderUtility.createPathID1(),
				TrianglePathBuilderUtility.createPathID2());
	}

	@Test
	public void testEqualsNotEqualTwoVersion() {
		// use Unit 4.11 version
		assertNotEquals(createPathID8_V1(), createPathID8_V2());
	}

	private TestPath createPathID8_V1() {
		TestPath path = new TestPath();
		path.setPathId(8);

		Node node1 = new NonPredicateNode(new BILoad(1, 0));// 0 vs 1
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(2, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BILoad(3, 3));
		path.add(node3);

		Node node4 = new NonPredicateNode(new BIAdd(4));
		path.add(node4);

		BLabel lable = new BLabel(new Label());
		PredicateNode node5 = new PredicateNode(new BIFicmpge(lable, 5));
		node5.setExpectedPredicateResult(false);
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(2, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(1, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BILoad(3, 8));
		path.add(node8);

		Node node9 = new NonPredicateNode(new BIAdd(9));
		path.add(node9);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node10 = new PredicateNode(new BIFicmpge(lable2, 10));
		node10.setExpectedPredicateResult(false);
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(3, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(1, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BILoad(2, 13));
		path.add(node13);

		Node node14 = new NonPredicateNode(new BIAdd(14));
		path.add(node14);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node15 = new PredicateNode(new BIFicmpge(lable3, 15));
		node15.setExpectedPredicateResult(false);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(true);
		path.add(node18);

		Node node24 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 25));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 26));
		node26.setExpectedPredicateResult(false);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 27));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 22));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 23));
		node29.setExpectedPredicateResult(false);
		path.add(node29);

		Node node30 = new NonPredicateNode(new BILoad(2, 24));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 25));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 26));
		node32.setExpectedPredicateResult(false);
		path.add(node32);

		Node node33 = new NonPredicateNode(new BIConst_2(27));
		path.add(node33);

		Node node34 = new NonPredicateNode(new BIReturn(28));
		path.add(node34);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	private TestPath createPathID8_V2() {
		TestPath path = new TestPath();
		path.setPathId(8);

		Node node1 = new NonPredicateNode(new BILoad(1, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(2, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BILoad(3, 3));
		path.add(node3);

		Node node4 = new NonPredicateNode(new BIAdd(4));
		path.add(node4);

		BLabel lable = new BLabel(new Label());
		PredicateNode node5 = new PredicateNode(new BIFicmpge(lable, 5));
		node5.setExpectedPredicateResult(false);
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(2, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(1, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BILoad(3, 8));
		path.add(node8);

		Node node9 = new NonPredicateNode(new BIAdd(9));
		path.add(node9);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node10 = new PredicateNode(new BIFicmpge(lable2, 10));
		node10.setExpectedPredicateResult(false);
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(3, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(1, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BILoad(2, 13));
		path.add(node13);

		Node node14 = new NonPredicateNode(new BIAdd(14));
		path.add(node14);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node15 = new PredicateNode(new BIFicmpge(lable3, 15));
		node15.setExpectedPredicateResult(false);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(true);
		path.add(node18);

		Node node24 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 25));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 26));
		node26.setExpectedPredicateResult(false);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 27));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 22));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 23));
		node29.setExpectedPredicateResult(false);
		path.add(node29);

		Node node30 = new NonPredicateNode(new BILoad(2, 24));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 25));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 26));
		node32.setExpectedPredicateResult(false);
		path.add(node32);

		Node node33 = new NonPredicateNode(new BIConst_2(27));
		path.add(node33);

		Node node34 = new NonPredicateNode(new BIReturn(28));
		path.add(node34);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

}

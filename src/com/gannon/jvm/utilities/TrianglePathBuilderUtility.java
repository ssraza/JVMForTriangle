package com.gannon.jvm.utilities;

import java.util.ArrayList;

import org.objectweb.asm.Label;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BLabel;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.instructions.BIConst_1;
import com.gannon.jvm.instructions.BIConst_2;
import com.gannon.jvm.instructions.BIConst_3;
import com.gannon.jvm.instructions.BIConst_4;
import com.gannon.jvm.instructions.BIFicmpeq;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BIFicmpne;
import com.gannon.jvm.instructions.BILoad;
import com.gannon.jvm.instructions.BIReturn;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.NonPredicateNode;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;

public class TrianglePathBuilderUtility {
	public static ArrayList<Object> createPathID1_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(6);
		input.add(6);
		input.add(6);
		return input;
	}

	public static ArrayList<Object> createPathID2_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(5);
		input.add(5);
		input.add(6);
		return input;
	}

	public static ArrayList<Object> createPathID3_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(4);
		input.add(5);
		input.add(4);
		return input;
	}

	public static ArrayList<Object> createPathID4_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(4);
		input.add(5);
		input.add(5);
		return input;
	}

	public static ArrayList<Object> createPathID5_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(4);
		input.add(4);
		input.add(5);
		return input;
	}

	public static ArrayList<Object> createPathID6_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(5);
		input.add(4);
		input.add(5);
		return input;
	}

	public static ArrayList<Object> createPathID7_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(4);
		input.add(5);
		input.add(5);
		return input;
	}

	public static ArrayList<Object> createPathID8_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(4);
		input.add(5);
		input.add(2);
		return input;
	}

	public static ArrayList<Object> createPathID9_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(14);
		input.add(11);
		input.add(20);
		return input;
	}

	public static ArrayList<Object> createPathID10_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(192);
		input.add(81);
		input.add(103);
		return input;
	}

	public static ArrayList<Object> createPathID11_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(14);
		input.add(60);
		input.add(1);
		return input;
	}

	public static ArrayList<Object> createPathID12_input() {
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);// store reference, not using it now
		input.add(14);
		input.add(6);
		input.add(100);
		return input;
	}

	public static TestPath createPathID10() {
		TestPath path = new TestPath();
		path.setPathId(10);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node5);

		Node node37 = new NonPredicateNode(new BIConst_4(37));
		path.add(node37);

		Node node38 = new NonPredicateNode(new BIReturn(38));
		path.add(node38);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID11() {
		TestPath path = new TestPath();
		path.setPathId(11);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node10);

		Node node36 = new NonPredicateNode(new BIConst_4(36));
		path.add(node36);

		Node node37 = new NonPredicateNode(new BIReturn(37));
		path.add(node37);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID12() {
		TestPath path = new TestPath();
		path.setPathId(12);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node15.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node15);

		Node node36 = new NonPredicateNode(new BIConst_4(36));
		path.add(node36);

		Node node37 = new NonPredicateNode(new BIReturn(37));
		path.add(node37);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID1() {
		TestPath path = new TestPath();
		path.setPathId(1);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node15.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node18);

		Node node19 = new NonPredicateNode(new BILoad(2, 19));
		path.add(node19);

		Node node20 = new NonPredicateNode(new BILoad(3, 20));
		path.add(node20);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node21 = new PredicateNode(new BIFicmpne(lable5, 21));
		node21.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node21);

		Node node22 = new NonPredicateNode(new BIConst_1(22));
		path.add(node22);

		Node node23 = new NonPredicateNode(new BIReturn(23));
		path.add(node23);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID8() {
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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node15.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node18);

		Node node24 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 25));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 26));
		node26.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 27));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 28));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 29));
		node29.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node29);

		Node node30 = new NonPredicateNode(new BILoad(2, 30));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 31));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 32));
		node32.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node32);

		Node node33 = new NonPredicateNode(new BIConst_2(33));
		path.add(node33);

		Node node34 = new NonPredicateNode(new BIReturn(34));
		path.add(node34);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID9() {
		TestPath path = new TestPath();
		path.setPathId(9);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node15.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node18);

		Node node19 = new NonPredicateNode(new BILoad(2, 19));
		path.add(node19);

		Node node20 = new NonPredicateNode(new BILoad(3, 20));
		path.add(node20);

		BLabel lable40 = new BLabel(new Label());
		PredicateNode node21 = new PredicateNode(new BIFicmpne(lable40, 21));
		node21.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node21);

		Node node24 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 25));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 26));
		node26.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 27));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 28));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 29));
		node29.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node29);

		Node node30 = new NonPredicateNode(new BILoad(2, 30));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 31));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 32));
		node32.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node32);

		Node node33 = new NonPredicateNode(new BIConst_2(33));
		path.add(node33);

		Node node34 = new NonPredicateNode(new BIReturn(34));
		path.add(node34);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID2() {
		TestPath path = new TestPath();
		path.setPathId(2);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node15.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node18);

		Node node24 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 25));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 26));
		node26.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node26);

		// Node node30 = new NonPredicateNode(new BILoad(2, 21));
		// path.add(node30);
		//
		// Node node31 = new NonPredicateNode(new BILoad(3, 22));
		// path.add(node31);
		//
		// BLabel lable7 = new BLabel(new Label());
		// PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 23));
		// node32.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		// path.add(node32);

		Node node35 = new NonPredicateNode(new BIConst_3(35));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(36));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID3() {
		TestPath path = new TestPath();
		path.setPathId(3);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node15.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node18);

		Node node24 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 25));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 26));
		node26.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 27));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 28));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 29));
		node29.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node29);

		Node node35 = new NonPredicateNode(new BIConst_3(35));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(36));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID4() {
		TestPath path = new TestPath();
		path.setPathId(4);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node15.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node18);

		Node node24 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 25));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 26));
		node26.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 27));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 28));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 29));
		node29.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node29);

		Node node30 = new NonPredicateNode(new BILoad(2, 30));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 31));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 32));
		node32.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node32);

		Node node35 = new NonPredicateNode(new BIConst_3(35));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(36));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID5() {
		TestPath path = new TestPath();
		path.setPathId(5);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node15.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node18);

		Node node19 = new NonPredicateNode(new BILoad(2, 19));
		path.add(node19);

		Node node20 = new NonPredicateNode(new BILoad(3, 20));
		path.add(node20);

		BLabel lable40 = new BLabel(new Label());
		PredicateNode node21 = new PredicateNode(new BIFicmpne(lable40, 21));
		node21.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node21);

		Node node24 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 25));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 26));
		node26.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node26);

		Node node35 = new NonPredicateNode(new BIConst_3(35));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(36));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID6() {
		TestPath path = new TestPath();
		path.setPathId(6);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node15.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node18);

		Node node19 = new NonPredicateNode(new BILoad(2, 19));
		path.add(node19);

		Node node20 = new NonPredicateNode(new BILoad(3, 20));
		path.add(node20);

		BLabel lable40 = new BLabel(new Label());
		PredicateNode node21 = new PredicateNode(new BIFicmpne(lable40, 21));
		node21.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node21);

		Node node24 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 25));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 26));
		node26.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 27));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 28));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 29));
		node29.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node29);

		Node node35 = new NonPredicateNode(new BIConst_3(35));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(36));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathID7() {
		TestPath path = new TestPath();
		path.setPathId(7);

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
		node5.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node10.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
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
		node15.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(1, 16));
		path.add(node16);

		Node node17 = new NonPredicateNode(new BILoad(2, 17));
		path.add(node17);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node18 = new PredicateNode(new BIFicmpne(lable4, 18));
		node18.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node18);

		Node node19 = new NonPredicateNode(new BILoad(2, 19));
		path.add(node19);

		Node node20 = new NonPredicateNode(new BILoad(3, 20));
		path.add(node20);

		BLabel lable40 = new BLabel(new Label());
		PredicateNode node21 = new PredicateNode(new BIFicmpne(lable40, 21));
		node21.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node21);

		Node node24 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 25));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 26));
		node26.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 27));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 28));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 29));
		node29.setExpectedPredicateResult(ConstantsUtility.EXPECTED_FALSE);
		path.add(node29);

		Node node30 = new NonPredicateNode(new BILoad(2, 30));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 31));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 32));
		node32.setExpectedPredicateResult(ConstantsUtility.EXPECTED_TRUE);
		path.add(node32);

		Node node35 = new NonPredicateNode(new BIConst_3(35));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(36));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

}

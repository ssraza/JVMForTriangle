package com.gannon.jvm.utilities;

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

	public static TestPath createPathNotTriangle1() {
		TestPath path = new TestPath();
		path.setPathId(10);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

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

	public static TestPath createPathNotTriangle2() {
		TestPath path = new TestPath();
		path.setPathId(11);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

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

	public static TestPath createPathNotTriangle3() {
		TestPath path = new TestPath();
		path.setPathId(12);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

		Node node10 = new NonPredicateNode(new BILoad(3, 10));
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(1, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(2, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BIAdd(13));
		path.add(node13);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node14 = new PredicateNode(new BIFicmpge(lable3, 14));
		node14.setExpectedPredicateValue(false);
		path.add(node14);

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

	public static TestPath createPathEquilateralTriangle() {
		TestPath path = new TestPath();
		path.setPathId(1);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable1 = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable1, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

		Node node10 = new NonPredicateNode(new BILoad(3, 10));
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(1, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(2, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BIAdd(13));
		path.add(node13);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node14 = new PredicateNode(new BIFicmpge(lable3, 14));
		node14.setExpectedPredicateValue(false);
		path.add(node14);

		Node node15 = new NonPredicateNode(new BILoad(1, 15));
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(2, 16));
		path.add(node16);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node17 = new PredicateNode(new BIFicmpne(lable4, 17));
		node17.setExpectedPredicateValue(false);
		path.add(node17);

		Node node18 = new NonPredicateNode(new BILoad(2, 18));
		path.add(node18);

		Node node19 = new NonPredicateNode(new BILoad(3, 19));
		path.add(node19);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node20 = new PredicateNode(new BIFicmpne(lable5, 20));
		node20.setExpectedPredicateValue(false);
		path.add(node20);

		Node node21 = new NonPredicateNode(new BIConst_1(21));
		path.add(node21);

		Node node22 = new NonPredicateNode(new BIReturn(22));
		path.add(node22);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathScaleneTriangle1() {
		TestPath path = new TestPath();
		path.setPathId(8);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable1 = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable1, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

		Node node10 = new NonPredicateNode(new BILoad(3, 10));
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(1, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(2, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BIAdd(13));
		path.add(node13);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node14 = new PredicateNode(new BIFicmpge(lable3, 14));
		node14.setExpectedPredicateValue(false);
		path.add(node14);

		Node node15 = new NonPredicateNode(new BILoad(1, 15));
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(2, 16));
		path.add(node16);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node17 = new PredicateNode(new BIFicmpne(lable4, 17));
		node17.setExpectedPredicateValue(false);
		path.add(node17);

		Node node24 = new NonPredicateNode(new BILoad(1, 18));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 19));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 20));
		node26.setExpectedPredicateValue(false);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 21));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 22));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 23));
		node29.setExpectedPredicateValue(false);
		path.add(node29);

		Node node30 = new NonPredicateNode(new BILoad(2, 24));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 25));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 26));
		node32.setExpectedPredicateValue(false);
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

	public static TestPath createPathScaleneTriangle2() {
		TestPath path = new TestPath();
		path.setPathId(9);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable1 = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable1, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

		Node node10 = new NonPredicateNode(new BILoad(3, 10));
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(1, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(2, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BIAdd(13));
		path.add(node13);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node14 = new PredicateNode(new BIFicmpge(lable3, 14));
		node14.setExpectedPredicateValue(false);
		path.add(node14);

		Node node15 = new NonPredicateNode(new BILoad(1, 15));
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(2, 16));
		path.add(node16);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node17 = new PredicateNode(new BIFicmpne(lable4, 17));
		node17.setExpectedPredicateValue(false);
		path.add(node17);

		Node node18 = new NonPredicateNode(new BILoad(2, 18));
		path.add(node18);

		Node node19 = new NonPredicateNode(new BILoad(3, 19));
		path.add(node19);

		BLabel lable40 = new BLabel(new Label());
		PredicateNode node20 = new PredicateNode(new BIFicmpne(lable40, 20));
		node20.setExpectedPredicateValue(false);
		path.add(node20);

		Node node24 = new NonPredicateNode(new BILoad(1, 21));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 22));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 23));
		node26.setExpectedPredicateValue(false);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 25));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 26));
		node29.setExpectedPredicateValue(false);
		path.add(node29);

		Node node30 = new NonPredicateNode(new BILoad(2, 27));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 28));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 29));
		node32.setExpectedPredicateValue(false);
		path.add(node32);

		Node node33 = new NonPredicateNode(new BIConst_2(30));
		path.add(node33);

		Node node34 = new NonPredicateNode(new BIReturn(31));
		path.add(node34);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathISOScaleTriangle1() {
		TestPath path = new TestPath();
		path.setPathId(2);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable1 = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable1, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

		Node node10 = new NonPredicateNode(new BILoad(3, 10));
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(1, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(2, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BIAdd(13));
		path.add(node13);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node14 = new PredicateNode(new BIFicmpge(lable3, 14));
		node14.setExpectedPredicateValue(false);
		path.add(node14);

		Node node15 = new NonPredicateNode(new BILoad(1, 15));
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(2, 16));
		path.add(node16);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node17 = new PredicateNode(new BIFicmpne(lable4, 17));
		node17.setExpectedPredicateValue(false);
		path.add(node17);

		Node node24 = new NonPredicateNode(new BILoad(1, 18));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 19));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 20));
		node26.setExpectedPredicateValue(false);
		path.add(node26);

		Node node30 = new NonPredicateNode(new BILoad(2, 21));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 22));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 23));
		node32.setExpectedPredicateValue(false);
		path.add(node32);

		Node node35 = new NonPredicateNode(new BIConst_3(24));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(25));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathISOScaleTriangle2() {
		TestPath path = new TestPath();
		path.setPathId(3);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable1 = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable1, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

		Node node10 = new NonPredicateNode(new BILoad(3, 10));
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(1, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(2, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BIAdd(13));
		path.add(node13);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node14 = new PredicateNode(new BIFicmpge(lable3, 14));
		node14.setExpectedPredicateValue(false);
		path.add(node14);

		Node node15 = new NonPredicateNode(new BILoad(1, 15));
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(2, 16));
		path.add(node16);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node17 = new PredicateNode(new BIFicmpne(lable4, 17));
		node17.setExpectedPredicateValue(false);
		path.add(node17);

		Node node24 = new NonPredicateNode(new BILoad(1, 18));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 29));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 20));
		node26.setExpectedPredicateValue(false);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 21));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 22));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 23));
		node29.setExpectedPredicateValue(false);
		path.add(node29);

		Node node35 = new NonPredicateNode(new BIConst_3(24));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(25));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathISOScaleTriangle3() {
		TestPath path = new TestPath();
		path.setPathId(4);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable1 = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable1, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

		Node node10 = new NonPredicateNode(new BILoad(3, 10));
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(1, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(2, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BIAdd(13));
		path.add(node13);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node14 = new PredicateNode(new BIFicmpge(lable3, 14));
		node14.setExpectedPredicateValue(false);
		path.add(node14);

		Node node15 = new NonPredicateNode(new BILoad(1, 15));
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(2, 16));
		path.add(node16);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node17 = new PredicateNode(new BIFicmpne(lable4, 17));
		node17.setExpectedPredicateValue(false);
		path.add(node17);

		Node node24 = new NonPredicateNode(new BILoad(1, 18));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 19));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 20));
		node26.setExpectedPredicateValue(false);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 21));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 22));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 23));
		node29.setExpectedPredicateValue(false);
		path.add(node29);

		Node node30 = new NonPredicateNode(new BILoad(2, 24));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 25));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 26));
		node32.setExpectedPredicateValue(false);
		path.add(node32);

		Node node35 = new NonPredicateNode(new BIConst_3(27));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(28));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathISOScaleTriangle4() {
		TestPath path = new TestPath();
		path.setPathId(5);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable1 = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable1, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

		Node node10 = new NonPredicateNode(new BILoad(3, 10));
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(1, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(2, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BIAdd(13));
		path.add(node13);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node14 = new PredicateNode(new BIFicmpge(lable3, 14));
		node14.setExpectedPredicateValue(false);
		path.add(node14);

		Node node15 = new NonPredicateNode(new BILoad(1, 15));
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(2, 16));
		path.add(node16);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node17 = new PredicateNode(new BIFicmpne(lable4, 17));
		node17.setExpectedPredicateValue(false);
		path.add(node17);

		Node node18 = new NonPredicateNode(new BILoad(2, 18));
		path.add(node18);

		Node node19 = new NonPredicateNode(new BILoad(3, 19));
		path.add(node19);

		BLabel lable40 = new BLabel(new Label());
		PredicateNode node20 = new PredicateNode(new BIFicmpne(lable40, 20));
		node20.setExpectedPredicateValue(false);
		path.add(node20);

		Node node24 = new NonPredicateNode(new BILoad(1, 21));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 22));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 23));
		node26.setExpectedPredicateValue(false);
		path.add(node26);

		Node node35 = new NonPredicateNode(new BIConst_3(24));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(25));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathISOScaleTriangle5() {
		TestPath path = new TestPath();
		path.setPathId(6);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable1 = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable1, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

		Node node10 = new NonPredicateNode(new BILoad(3, 10));
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(1, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(2, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BIAdd(13));
		path.add(node13);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node14 = new PredicateNode(new BIFicmpge(lable3, 14));
		node14.setExpectedPredicateValue(false);
		path.add(node14);

		Node node15 = new NonPredicateNode(new BILoad(1, 15));
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(2, 16));
		path.add(node16);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node17 = new PredicateNode(new BIFicmpne(lable4, 17));
		node17.setExpectedPredicateValue(false);
		path.add(node17);

		Node node18 = new NonPredicateNode(new BILoad(2, 18));
		path.add(node18);

		Node node19 = new NonPredicateNode(new BILoad(3, 19));
		path.add(node19);

		BLabel lable40 = new BLabel(new Label());
		PredicateNode node20 = new PredicateNode(new BIFicmpne(lable40, 20));
		node20.setExpectedPredicateValue(false);
		path.add(node20);

		Node node24 = new NonPredicateNode(new BILoad(1, 21));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 22));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 23));
		node26.setExpectedPredicateValue(false);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 25));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 26));
		node29.setExpectedPredicateValue(false);
		path.add(node29);

		Node node35 = new NonPredicateNode(new BIConst_3(27));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(28));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

	public static TestPath createPathISOScaleTriangle6() {
		TestPath path = new TestPath();
		path.setPathId(7);

		Node node0 = new NonPredicateNode(new BILoad(1, 0));
		path.add(node0);

		Node node1 = new NonPredicateNode(new BILoad(2, 1));
		path.add(node1);

		Node node2 = new NonPredicateNode(new BILoad(3, 2));
		path.add(node2);

		Node node3 = new NonPredicateNode(new BIAdd(3));
		path.add(node3);

		BLabel lable1 = new BLabel(new Label());
		PredicateNode node4 = new PredicateNode(new BIFicmpge(lable1, 4));
		node4.setExpectedPredicateValue(false);
		path.add(node4);

		Node node5 = new NonPredicateNode(new BILoad(2, 5));
		path.add(node5);

		Node node6 = new NonPredicateNode(new BILoad(1, 6));
		path.add(node6);

		Node node7 = new NonPredicateNode(new BILoad(3, 7));
		path.add(node7);

		Node node8 = new NonPredicateNode(new BIAdd(8));
		path.add(node8);

		BLabel lable2 = new BLabel(new Label());
		PredicateNode node9 = new PredicateNode(new BIFicmpge(lable2, 9));
		node9.setExpectedPredicateValue(false);
		path.add(node9);

		Node node10 = new NonPredicateNode(new BILoad(3, 10));
		path.add(node10);

		Node node11 = new NonPredicateNode(new BILoad(1, 11));
		path.add(node11);

		Node node12 = new NonPredicateNode(new BILoad(2, 12));
		path.add(node12);

		Node node13 = new NonPredicateNode(new BIAdd(13));
		path.add(node13);

		BLabel lable3 = new BLabel(new Label());
		PredicateNode node14 = new PredicateNode(new BIFicmpge(lable3, 14));
		node14.setExpectedPredicateValue(false);
		path.add(node14);

		Node node15 = new NonPredicateNode(new BILoad(1, 15));
		path.add(node15);

		Node node16 = new NonPredicateNode(new BILoad(2, 16));
		path.add(node16);

		BLabel lable4 = new BLabel(new Label());
		PredicateNode node17 = new PredicateNode(new BIFicmpne(lable4, 17));
		node17.setExpectedPredicateValue(false);
		path.add(node17);

		Node node18 = new NonPredicateNode(new BILoad(2, 18));
		path.add(node18);

		Node node19 = new NonPredicateNode(new BILoad(3, 19));
		path.add(node19);

		BLabel lable40 = new BLabel(new Label());
		PredicateNode node20 = new PredicateNode(new BIFicmpne(lable40, 20));
		node20.setExpectedPredicateValue(false);
		path.add(node20);

		Node node24 = new NonPredicateNode(new BILoad(1, 21));
		path.add(node24);

		Node node25 = new NonPredicateNode(new BILoad(2, 22));
		path.add(node25);

		BLabel lable5 = new BLabel(new Label());
		PredicateNode node26 = new PredicateNode(new BIFicmpeq(lable5, 23));
		node26.setExpectedPredicateValue(false);
		path.add(node26);

		Node node27 = new NonPredicateNode(new BILoad(1, 24));
		path.add(node27);

		Node node28 = new NonPredicateNode(new BILoad(3, 25));
		path.add(node28);

		BLabel lable6 = new BLabel(new Label());
		PredicateNode node29 = new PredicateNode(new BIFicmpeq(lable6, 26));
		node29.setExpectedPredicateValue(false);
		path.add(node29);

		Node node30 = new NonPredicateNode(new BILoad(2, 27));
		path.add(node30);

		Node node31 = new NonPredicateNode(new BILoad(3, 28));
		path.add(node31);

		BLabel lable7 = new BLabel(new Label());
		PredicateNode node32 = new PredicateNode(new BIFicmpeq(lable7, 29));
		node32.setExpectedPredicateValue(false);
		path.add(node32);

		Node node35 = new NonPredicateNode(new BIConst_3(30));
		path.add(node35);

		Node node36 = new NonPredicateNode(new BIReturn(31));
		path.add(node36);

		// get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");

		// set method in the path
		path.setbMethod(m);

		return path;
	}

}

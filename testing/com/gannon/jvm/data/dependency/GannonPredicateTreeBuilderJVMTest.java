package com.gannon.jvm.data.dependency;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
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
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;

public class GannonPredicateTreeBuilderJVMTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	/*
	 * 101
	 * /\
	 * 1 100
	 *    /\
	 *    2 3
	 */
	@Test
	public void testBuildPathNotTriangle() {
		GannonPredicateTreeBuilderJVM jvm = new GannonPredicateTreeBuilderJVM();
		jvm.run(TrianglePathBuilderUtility.createPathID10());
		System.out.println(jvm.getRelationFrame().getRelations());

	}

	@Test
	public void testBuildPathEquilateral() {
		GannonPredicateTreeBuilderJVM jvm = new GannonPredicateTreeBuilderJVM();
		jvm.run(TrianglePathBuilderUtility.createPathID1());
		System.out.println(jvm.getRelationFrame().getRelations());

	}
	
	@Test
	public void testBuildPathScalene() {
		GannonPredicateTreeBuilderJVM jvm = new GannonPredicateTreeBuilderJVM();
		jvm.run(TrianglePathBuilderUtility.createPathID8());
		System.out.println(jvm.getRelationFrame().getRelations());

	}
	
	@Test
	public void testBuildPathISOScale() {
		GannonPredicateTreeBuilderJVM jvm = new GannonPredicateTreeBuilderJVM();
		jvm.run(TrianglePathBuilderUtility.createPathID2());
		System.out.println(jvm.getRelationFrame().getRelations());

	}

}

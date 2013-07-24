package com.gannon.jvm.execution.path;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
import com.gannon.jvm.execution.method.GannonMethodJVM;
import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.instructions.BIConst_4;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BILoad;
import com.gannon.jvm.instructions.BIReturn;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.NonPredicateNode;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;

public class GannonPathJVMTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	@Test
	public void testMethodTriangleTypePathID_1() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID1();
		ArrayList<Object> input = new ArrayList<>();
		input.add(-1);//store reference, not using it now
		input.add(6);
		input.add(6);
		input.add(6);
		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(1), jvm.run(path, input));
	}

	@Test
	public void testMethodTriangleTypePathID_2() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID2();
		ArrayList<Object> input = new ArrayList<>();
		input.add(-1);//store reference, not using it now
		input.add(4);
		input.add(4);
		input.add(5);
		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
	}

	@Test
	public void testMethodTriangleTypePathID_3() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID3();
		ArrayList<Object> input = new ArrayList<>();
		input.add(-1);//store reference, not using it now
		input.add(4);
		input.add(5);
		input.add(4);
		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
	}

	@Test
	public void testMethodTriangleTypePathID_4() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID4();
		ArrayList<Object> input = new ArrayList<>();
		input.add(-1);//store reference, not using it now
		input.add(4);
		input.add(5);
		input.add(5);
		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
	}

	@Test
	public void testMethodTriangleTypePathID_5() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID5();
		ArrayList<Object> input = new ArrayList<>();
		input.add(-1);//store reference, not using it now
		input.add(4);
		input.add(5);
		input.add(5);
		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
	}

	@Test
	public void testMethodTriangleTypePathID_6() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID6();
		ArrayList<Object> input = new ArrayList<>();
		input.add(-1);//store reference, not using it now
		input.add(4);
		input.add(5);
		input.add(5);
		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
	}

	@Test
	public void testMethodTriangleTypePathID_7() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID7();
		ArrayList<Object> input = new ArrayList<>();
		input.add(-1);//store reference, not using it now
		input.add(4);
		input.add(5);
		input.add(5);
		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
	}

	@Test
	public void testMethodTriangleTypePathID_8() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID8();
		ArrayList<Object> input = new ArrayList<>();
		input.add(-1);//store reference, not using it now
		input.add(4);
		input.add(5);
		input.add(2);
		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(2), jvm.run(path, input));
	}

	@Test
	public void testMethodTrianglePathID_10() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID10();

		ArrayList<Object> input = new ArrayList<>();
		input.add(-1);//store reference, not using it now
		input.add(14);
		input.add(6);
		input.add(1);
		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(4), jvm.run(path, input));
	}
}

package com.gannon.jvm.execution.path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;

//testing predicates in each path match the expected predicate results
//assert hasPassed ==true
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
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID1_input();
		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(1), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(15)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(18)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(21)).hasPassed());
	}

	@Test
	public void testMethodTriangleTypePathID_2() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID2();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID2_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(15)).hasPassed());
		assertFalse(((PredicateNode)path.getNode(18)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(26)).hasPassed());
	}

	@Test
	public void testMethodTriangleTypePathID_3() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID3();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID3_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(15)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(18)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(26)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(29)).hasPassed());
	}

	@Test
	public void testMethodTriangleTypePathID_4() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID4();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID4_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(15)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(18)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(26)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(29)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(32)).hasPassed());
	}

	@Test
	public void testMethodTriangleTypePathID_5() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID5();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID5_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(15)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(18)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(21)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(26)).hasPassed());
	}

	@Test
	public void testMethodTriangleTypePathID_6() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID6();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID6_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(15)).hasPassed());
		assertFalse(((PredicateNode)path.getNode(18)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(21)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(26)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(29)).hasPassed());
	}


	@Test
	public void testMethodTriangleTypePathID_7() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID7();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID7_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(3), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(15)).hasPassed());
		assertFalse(((PredicateNode)path.getNode(18)).hasPassed());

	}

	@Test
	public void testMethodTriangleTypePathID_8() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID8();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID8_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(2), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(15)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(18)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(26)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(29)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(32)).hasPassed());
	}

	@Test
	public void testMethodTriangleTypePathID_9() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID9();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID9_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(2), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(15)).hasPassed());
		assertFalse(((PredicateNode)path.getNode(18)).hasPassed());

	}

	@Test
	public void testMethodTriangleTypePathID_10() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID10();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID10_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(4), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
	}

	@Test
	public void testMethodTriangleTypePathID_11() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID11();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID11_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(4), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
	}

	@Test
	public void testMethodTriangleTypePathID_12() {
		// create input
		TestPath path = TrianglePathBuilderUtility.createPathID12();
		ArrayList<Object> input = TrianglePathBuilderUtility.createPathID12_input();

		// assertion
		GannonPathJVM jvm = new GannonPathJVM();
		assertEquals(new Integer(4), jvm.run(path, input));
		assertTrue(((PredicateNode)path.getNode(5)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(10)).hasPassed());
		assertTrue(((PredicateNode)path.getNode(15)).hasPassed());
	}
}

package com.gannon.bytecode.controlflowgraph;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.progam.path.TestPaths;
import com.gannon.jvm.utilities.ConstantsUtility;

public class CGraphTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	//
	// _1
	// / \
	// 2 3
	// \ /
	// _4
	//

	@Test
	public void testGetRoot() {
		CNode expectedRoot = new CNode(1, new CBlock(1));

		CGraph g = create4NodesGraph();
		assertEquals(expectedRoot, g.getRoot());

	}

	@Test
	public void testGetSink() {
		CNode expectedRoot = new CNode(4, new CBlock(4));
		CGraph g = create4NodesGraph();
		assertEquals(expectedRoot, g.getSink());

	}

	private CGraph create4NodesGraph() {
		CGraph g = new CGraph();
		CNode node1 = new CNode(1, new CBlock(1));
		g.addCNode(node1);
		CNode node2 = new CNode(2, new CBlock(2));
		g.addCNode(node2);
		CNode node3 = new CNode(3, new CBlock(3));
		g.addCNode(node3);
		CNode node4 = new CNode(4, new CBlock(4));
		g.addCNode(node4);

		g.addCEdge(new CEdge(1, node1, node2));
		g.addCEdge(new CEdge(2, node1, node3));
		g.addCEdge(new CEdge(3, node2, node4));
		g.addCEdge(new CEdge(4, node3, node4));
		return g;
	}

	//
	// _1
	// / \
	// 2 3
	// | | \
	// | 4 5
	// | | /
	// | 6
	// | |
	// | 7
	// \ /
	// 8
	private CGraph create8NodesGraph() {
		CGraph g = new CGraph();
		CNode node1 = new CNode(1, new CBlock(1));
		g.addCNode(node1);
		CNode node2 = new CNode(2, new CBlock(2));
		g.addCNode(node2);
		CNode node3 = new CNode(3, new CBlock(3));
		g.addCNode(node3);
		CNode node4 = new CNode(4, new CBlock(4));
		g.addCNode(node4);
		CNode node5 = new CNode(5, new CBlock(5));
		g.addCNode(node5);
		CNode node6 = new CNode(6, new CBlock(6));
		g.addCNode(node6);
		CNode node7 = new CNode(7, new CBlock(7));
		g.addCNode(node7);
		CNode node8 = new CNode(8, new CBlock(8));
		g.addCNode(node8);

		g.addCEdge(new CEdge(1, node1, node2));
		g.addCEdge(new CEdge(2, node1, node3));
		g.addCEdge(new CEdge(3, node2, node8));
		g.addCEdge(new CEdge(4, node3, node4));
		g.addCEdge(new CEdge(5, node3, node5));
		g.addCEdge(new CEdge(6, node4, node6));
		g.addCEdge(new CEdge(7, node5, node6));
		g.addCEdge(new CEdge(8, node6, node7));
		g.addCEdge(new CEdge(9, node7, node8));

		return g;
	}

	@Test
	public void testGetLongestPath() {

		CNode node1 = new CNode(1, new CBlock(1));
		CNode node2 = new CNode(2, new CBlock(2));
		CNode node3 = new CNode(3, new CBlock(3));
		CNode node4 = new CNode(4, new CBlock(4));
		CNode node5 = new CNode(5, new CBlock(5));
		CNode node6 = new CNode(6, new CBlock(6));
		CNode node7 = new CNode(7, new CBlock(7));
		CNode node8 = new CNode(8, new CBlock(8));

		CGraph g = create8NodesGraph();
		CPath longestPath = g.getLongestPath(node1, node8);

		assertEquals(longestPath.getNodes().size(), 6);
		assertEquals(longestPath.getNodes().get(0), node1);
		assertEquals(longestPath.getNodes().get(1), node3);
		assertEquals(longestPath.getNodes().get(2), node4);
		assertEquals(longestPath.getNodes().get(3), node6);
		assertEquals(longestPath.getNodes().get(4), node7);
		assertEquals(longestPath.getNodes().get(5), node8);

	}

	@Test
	public void testGetNymberOfPaths() {
		CNode node1 = new CNode(1, new CBlock(1));
		CNode node2 = new CNode(2, new CBlock(2));
		CNode node3 = new CNode(3, new CBlock(3));
		CNode node4 = new CNode(4, new CBlock(4));
		CNode node5 = new CNode(5, new CBlock(5));
		CNode node6 = new CNode(6, new CBlock(6));
		CNode node7 = new CNode(7, new CBlock(7));
		CNode node8 = new CNode(8, new CBlock(8));

		CGraph g = create8NodesGraph();

		int numberOfPaths = g.getNumberOfPathsBetweenTwoNodes(node1, node8);
		assertEquals(3, numberOfPaths);

	}

	@Test
	public void testBuildTriangleCFG() {
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		assertEquals("triangleType", m.getName());

		CFGMethod cfg = new CFGMethod(m);
		System.out.print(cfg.buildGraph());

	}

	public CGraph getCFG() {
		BClass myclass = BClassGenerator.getBClass("ClassForRandomMethods.class");
		BMethod m = myclass.getMethod("testGraphMethod2");
		CFGMethod cfg = new CFGMethod(m);
		CGraph graph = cfg.buildGraph();
		return graph;
	}

	@Test
	public void GenerateAllTestPathTest() {
		CGraph graph = getCFG();
		// CPaths testPaths = graph.generateAllPaths();
		// System.out.println(testPaths.getPaths().size());
	}

	@Test
	public void testAdjacency() {
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		assertEquals("triangleType", m.getName());

		CFGMethod cfg = new CFGMethod(m);
		CGraph graph = cfg.buildGraph();
		System.out.println(graph.getAdjacency());
	}

	@Test
	public void testAetAdjacentNodeIDs() {
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		assertEquals("triangleType", m.getName());

		CFGMethod cfg = new CFGMethod(m);
		CGraph graph = cfg.buildGraph();
		assertEquals(Arrays.asList(1), graph.getAdjacentNodeIDs(0));

	}

	@Test
	public void testAetAdjacentNodeIDs2() {
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		assertEquals("triangleType", m.getName());

		CFGMethod cfg = new CFGMethod(m);
		CGraph graph = cfg.buildGraph();
		assertEquals(Arrays.asList(19, 14), graph.getAdjacentNodeIDs(13));

	}

	@Test
	public void testAllPaths() {
		int START = 2;
		int END = 5;

		// this graph is directional
		CGraph graph = new CGraph();
		CGraph g = new CGraph();
		CNode node1 = new CNode(1, new CBlock(1));
		g.addCNode(node1);
		CNode node2 = new CNode(2, new CBlock(2));
		g.addCNode(node2);
		CNode node3 = new CNode(3, new CBlock(3));
		g.addCNode(node3);
		CNode node4 = new CNode(4, new CBlock(4));
		g.addCNode(node4);
		CNode node5 = new CNode(5, new CBlock(5));
		g.addCNode(node5);
		CNode node6 = new CNode(6, new CBlock(6));
		g.addCNode(node6);

		g.addCEdge(new CEdge(1, node1, node2));
		g.addCEdge(new CEdge(2, node1, node3));
		g.addCEdge(new CEdge(3, node2, node1));
		g.addCEdge(new CEdge(4, node2, node4));
		g.addCEdge(new CEdge(5, node2, node5));
		g.addCEdge(new CEdge(6, node2, node6));
		g.addCEdge(new CEdge(7, node3, node1));
		g.addCEdge(new CEdge(8, node3, node5));
		g.addCEdge(new CEdge(9, node3, node6));
		g.addCEdge(new CEdge(10, node4, node2));
		g.addCEdge(new CEdge(11, node5, node3));
		g.addCEdge(new CEdge(12, node5, node6));
		g.addCEdge(new CEdge(13, node6, node2));
		g.addCEdge(new CEdge(14, node6, node3));
		g.addCEdge(new CEdge(15, node6, node5));

		LinkedList<LinkedList<Integer>> paths = new LinkedList<LinkedList<Integer>>();
		paths = g.computeAllPaths(START, END);
		g.printPaths(paths);

		LinkedList<LinkedList<Integer>> expectedPaths = new LinkedList<LinkedList<Integer>>();
		LinkedList<Integer> path1 = new LinkedList<Integer>(Arrays.asList(2, END));
		LinkedList<Integer> path4 = new LinkedList<Integer>(Arrays.asList(2, 1, 3, END));
		LinkedList<Integer> path5 = new LinkedList<Integer>(Arrays.asList(2, 1, 3, 6, END));
		LinkedList<Integer> path2 = new LinkedList<Integer>(Arrays.asList(2, 6, END));
		LinkedList<Integer> path3 = new LinkedList<Integer>(Arrays.asList(2, 6, 3, END));
		expectedPaths.add(path1);
		expectedPaths.add(path2);
		expectedPaths.add(path3);
		expectedPaths.add(path4);
		expectedPaths.add(path5);
		assertEquals(expectedPaths, paths);

	}

}

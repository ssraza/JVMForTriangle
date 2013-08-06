package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gannon.jvm.utilities.ConstantsUtility;

public final class CGraph {

	private static final List<CEdge> EMPTY_EDGES = Collections.emptyList();
	private Set<CNode> nodes;
	private Set<CEdge> edges;
	private Map<CNode, Map<CNode, List<CEdge>>> adjacency;// multiple edges?
	private Map<CNode, List<CNode>> setOfDominatorNodes;

	private int nextCNodeId;
	private int nextCEdgeId;

	public CGraph() {
		this.nodes = new HashSet<CNode>();
		this.edges = new HashSet<CEdge>();
		this.adjacency = new HashMap<CNode, Map<CNode, List<CEdge>>>();
		this.setOfDominatorNodes = new HashMap<CNode, List<CNode>>();
	}

	public CGraph(Set<CNode> nodes, Set<CEdge> newEdges) {
		this.nodes = nodes;
		this.edges = new HashSet<CEdge>();
		this.adjacency = new HashMap<CNode, Map<CNode, List<CEdge>>>();
		this.setOfDominatorNodes = new HashMap<CNode, List<CNode>>();
		initEdges(newEdges);
	}

	private void initEdges(Set<CEdge> edges) {
		Iterator<CEdge> it = edges.iterator();
		while (it.hasNext()) {
			CEdge edge = (CEdge) it.next();
			addCEdge(edge);
		}
	}

	public CGraph(String inputText) {
		this.nodes = new HashSet<CNode>();
		this.edges = new HashSet<CEdge>();
		this.adjacency = new HashMap<CNode, Map<CNode, List<CEdge>>>();
		this.setOfDominatorNodes = new HashMap<CNode, List<CNode>>();

		try {
			String[] listOfLines; // Array of strings
			listOfLines = inputText.split("\n"); // splitting the input text to
													// get the list of lines

			int index = 1; // starting from 1 to skip first line

			// Processing all input lines to create all nodes
			while (index < listOfLines.length) {
				String currentLine = listOfLines[index];
				String[] listOfStrings; // Array of strings
				listOfStrings = currentLine.split(" "); // splitting the string
														// to get the list of
														// nodes

				// getting block id from string
				int blockID = Integer.parseInt(listOfStrings[0]);

				// creating new block
				CBlock newNodeBlock = new CBlock(blockID);

				// creating new node
				CNode newNode = newCNode(newNodeBlock);

				// adding node to node list
				nodes.add(newNode);

				index++;
			}

			index = 1;
			// adding all edges
			while (index < listOfLines.length) {
				String currentLine = listOfLines[index];
				String[] listOfStrings; // Array of strings
				listOfStrings = currentLine.split(" "); // splitting the string
														// to get the list of
														// nodes
				// getting parent node for current line which is the first node
				CNode parentNode = getCNode(Integer.parseInt(listOfStrings[0]));

				// Processing all nodes in the current line after the first node
				for (int j = 1; j < listOfStrings.length; j++) {
					// getting block id from string
					int newBlockID = Integer.parseInt(listOfStrings[j]);
					// getting child node
					CNode childNode = getCNode(newBlockID);
					// creating new edge
					newCEdge(parentNode, childNode);
				}

				index++;
			}

			// initializing dominator nodes
			processDominatorNodes();

		} catch (Exception e) {
			System.out.println("Error reading input string");
		}
	}

	public CNode getCNode(int nodeID) {
		for (CNode n1 : nodes) {
			if (n1.getId() == nodeID) {
				return n1;
			}
		}
		System.out.println("From method getCnode: can't find node ID " + nodeID);
		return null;
	}

	public CEdge addCEdge(final CEdge edge) {
		if (this.edges.add(edge)) {
			final CNode src = edge.getSource();
			final CNode tgt = edge.getTarget();
			if (this.nodes.containsAll(Arrays.asList(src, tgt))) {
				Map<CNode, List<CEdge>> srcAdjacency = this.adjacency.get(src);
				if (srcAdjacency == null) {
					srcAdjacency = new HashMap<CNode, List<CEdge>>();
					this.adjacency.put(src, srcAdjacency);
				}
				List<CEdge> adjacencyCEdges = srcAdjacency.get(tgt);
				if (adjacencyCEdges == null) {
					adjacencyCEdges = new LinkedList<CEdge>();
					srcAdjacency.put(tgt, adjacencyCEdges);
				}
				adjacencyCEdges.add(edge);

			} else {
				this.edges.remove(edge);
				throw new IllegalArgumentException("CNode/s in the given edge is/are unknown: " + edge.toString());
			}
		}
		return edge;
	}

	public CNode addCNode(final CNode node) {
		nodes.add(node);
		return node;
	}

	public void detachCNode(final CNode node) {
		final Set<CEdge> tmpCEdges = new HashSet<CEdge>(this.edges);
		for (CEdge e : tmpCEdges) {
			if (node.equals(e.getSource()) || node.equals(e.getTarget())) {
				removeCEdge(e);
			}
		}

	}

	/**
	 * @return the adjacency
	 */
	public final Map<CNode, Map<CNode, List<CEdge>>> getAdjacency() {
		return adjacency;
	}

	/**
	 * @return the edges
	 */
	public final Set<CEdge> getCEdges() {
		return edges;
	}

	public List<CEdge> getCEdges(final CNode srcCNode, final CNode tgtCNode) {
		final Map<CNode, List<CEdge>> srcAdjacency = this.adjacency.get(srcCNode);
		final List<CEdge> adjacencyCEdges;
		if (srcAdjacency != null && (adjacencyCEdges = srcAdjacency.get(tgtCNode)) != null) {
			return adjacencyCEdges;
		}
		return EMPTY_EDGES;
	}

	/**
	 * @return the nodes
	 */
	public final Set<CNode> getCNodes() {
		return this.nodes;
	}

	// not been tested
	public void merge(final Set<CNode> nodes, final Set<CEdge> edges) {
		this.nodes.addAll(nodes);
		this.edges.addAll(edges);
	}

	public CEdge newCEdge(final CNode source, final CNode target) {
		return addCEdge(new CEdge(this.nextCEdgeId++, source, target));
	}

	public CNode newCNode(final CBlock data) {
		return addCNode(new CNode(this.nextCNodeId++, data));
	}

	public void removeCEdge(final CEdge e) {
		if (this.edges.remove(e)) {
			final Set<CNode> srcs = this.adjacency.keySet();
			Map<CNode, List<CEdge>> srcAdjacency;
			Set<CNode> tgts;

			for (CNode src : srcs) {
				srcAdjacency = this.adjacency.get(src);
				tgts = srcAdjacency.keySet();
				for (CNode tgt : tgts) {
					srcAdjacency.get(tgt).remove(e);
				}
			}
		}
	}

	public void removeCNode(final CNode node) {
		this.nodes.remove(node);
	}

	// not been tested
	public void mergeCallingGraph(CGraph g, CNode invokeCNode) {
		Set<CNode> gNodes = g.getCNodes();
		Set<CEdge> gEdges = g.getCEdges();
		merge(gNodes, gEdges);
		addCEdge(newCEdge(invokeCNode, g.getRoot()));
		addCEdge(newCEdge(g.getSink(), invokeCNode));
	}

	public Set<CNode> getTargetNodes() {
		Set<CNode> targetNodes = new HashSet<CNode>();
		Iterator<CEdge> it = edges.iterator();
		while (it.hasNext()) {
			CEdge edge = it.next();
			CNode tat = edge.getTarget();
			targetNodes.add(tat);
		}
		return targetNodes;
	}

	public Set<CNode> getTargetNodes(List<CEdge> edges) {
		Set<CNode> targetNodes = new HashSet<CNode>();
		Iterator<CEdge> it = edges.iterator();
		while (it.hasNext()) {
			CEdge edge = it.next();
			CNode tat = edge.getTarget();
			targetNodes.add(tat);
		}
		return targetNodes;
	}

	public Set<CNode> getSourceNodes() {
		Set<CNode> sourceNode = new HashSet<CNode>();
		Iterator<CEdge> it = edges.iterator();
		while (it.hasNext()) {
			CEdge edge = it.next();
			CNode tat = edge.getSource();
			sourceNode.add(tat);
		}
		return sourceNode;
	}

	public CNode getRoot() {
		Set<CNode> set = new HashSet<CNode>(nodes);
		set.removeAll(getTargetNodes());
		if (set.size() == 1) {
			return set.iterator().next();
		}
		return null;
	}

	public CNode getSink() {
		Set<CNode> set = new HashSet<CNode>(nodes);
		set.removeAll(getSourceNodes());
		if (set.size() == 1) {
			return set.iterator().next();
		}
		return null;
	}

	private CPath processGetLongestPath(CNode endNode, CNode currentNode, CPath currentPath) {
		CPath pathFromCurrentNode = new CPath(0);
		CPaths paths = new CPaths(0);

		if (currentNode.equals(endNode)) {
			pathFromCurrentNode.add(currentNode);
			return pathFromCurrentNode;
		} else if (currentPath.getNodes().contains(currentNode)) {
			return null;
		} else {
			// finding and processing child nodes
			for (CEdge currentEdge : edges) {
				// checking if current node is source node for current edge
				if (currentEdge.getSource().equals(currentNode)) {
					// getting child node
					CNode childNode = currentEdge.getTarget();
					currentPath.add(currentNode);
					CPath childPath = processGetLongestPath(endNode, childNode, currentPath);
					currentPath.getNodes().remove(currentNode);
					if (childPath != null) {
						paths.add(childPath);
					}
				}
			}

			if (paths.getPaths().size() > 0) {
				// finding longest path from child paths
				CPath longestPath = paths.getPaths().get(0);

				for (int j = 1; j < paths.getPaths().size(); j++) {
					CPath newPath = paths.getPaths().get(j);
					if (newPath.getNodes().size() > longestPath.getNodes().size()) {
						longestPath = newPath;
					}
				}

				pathFromCurrentNode.add(currentNode);

				//
				for (int i = 0; i < longestPath.getNodes().size(); i++) {
					pathFromCurrentNode.add(longestPath.getNodes().get(i));

				}
				return pathFromCurrentNode;
			}
		}

		return null;
	}

	public CPath getLongestPath(CNode finishNode, CNode startNode) {
		// creating list of visited nodes
		CPath longestPath = new CPath(1);
		return processGetLongestPath(startNode, finishNode, longestPath);
	}

	private void processDominatorNodes() {
		CNode rootNode = getRoot();
		List<CNode> dominatorNodesList = new ArrayList<CNode>();

		// clearing dominator nodes map
		setOfDominatorNodes.clear();

		setDominatorNodes(rootNode, dominatorNodesList);
	}

	private void setDominatorNodes(CNode currentNode, List<CNode> parentDominatorNodeList) {
		// getting dominator nodes for current node from hashmap
		List<CNode> dominatorNodes = setOfDominatorNodes.get(currentNode);

		if (dominatorNodes != null) {
			dominatorNodes.retainAll(parentDominatorNodeList);
			dominatorNodes.add(currentNode);
			// finding and processing child nodes
			for (CEdge currentEdge : edges) {
				// checking if current node is source node for current edge
				if (currentEdge.getSource() == currentNode) {
					// getting child node
					CNode childNode = currentEdge.getTarget();
					// getting set of dominator nodes for current node
					if (!dominatorNodes.contains(childNode)) {
						setDominatorNodes(childNode, dominatorNodes);
					}
				}
			}
		} else {
			// creating list of dominator nodes for current node
			dominatorNodes = new ArrayList<CNode>();
			dominatorNodes.add(currentNode);

			// adding list of dominator nodes to hash map
			setOfDominatorNodes.put(currentNode, dominatorNodes);

			// adding parent dominator nodes
			dominatorNodes.addAll(parentDominatorNodeList);

			// finding and processing child nodes
			for (CEdge currentEdge : edges) {
				// checking if current node is source node for current edge
				if (currentEdge.getSource() == currentNode) {
					// getting child node
					CNode childNode = currentEdge.getTarget();
					if (!dominatorNodes.contains(childNode)) {
						setDominatorNodes(childNode, dominatorNodes);
					}
				}
			}
		}
	}

	// checking if an edge between two nodes is a loop edge by finding if the
	// node is dominated ny the other node
	public boolean isLoopEdge(CNode startNode, CNode endNode) {
		return setOfDominatorNodes.get(startNode).contains(endNode);
	}

	private int processGetNumberOfPaths(CNode endNode, CNode currentNode, CPath currentPath) {
		int numberOfFoundPaths = 0;

		if (currentNode.equals(endNode)) {
			return 1;
		} else if (currentPath.getNodes().contains(currentNode)) {
			return 0;
		} else {

			// finding and processing child nodes
			for (CEdge currentEdge : edges) {
				// checking if current node is source node for current edge
				if (currentEdge.getSource().equals(currentNode)) {
					// getting child node
					CNode childNode = currentEdge.getTarget();
					currentPath.add(currentNode);
					numberOfFoundPaths += processGetNumberOfPaths(endNode, childNode, currentPath);
					currentPath.getNodes().remove(currentNode);
				}
			}
		}
		return numberOfFoundPaths;
	}

	public int getNumberOfPathsBetweenTwoNodes(CNode node1, CNode node2) {
		// creating list of visited nodes
		CPath visitedPath = new CPath(0);
		return processGetNumberOfPaths(node2, node1, visitedPath);
	}

	@SuppressWarnings("unchecked")
	public List<CNode> getAdjacentNodes(CNode sourceNode) {
		List<CNode> nodes = new ArrayList<CNode>();
		Iterator<CEdge> it = edges.iterator();
		while (it.hasNext()) {
			CEdge edge = (CEdge) it.next();
			if (edge.getSource().equals(sourceNode)) {
				nodes.add(edge.getTarget());
			}
		}
		return nodes;
	}

	public List<Integer> getAdjacentNodeIDs(int nodeID) {
		CNode sourceNode = getCNode(nodeID);
		List<CNode> neighbors = getAdjacentNodes(sourceNode);
		List<Integer> nodeIDs = new ArrayList<Integer>();
		for (CNode node : neighbors) {
			nodeIDs.add(node.getId());
		}
		return nodeIDs;

	}

	// ============================ for displaying=================
	public String printEdgesToString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n====== Edges ======\n");
		Iterator<CEdge> it = edges.iterator();
		while (it.hasNext()) {
			CEdge edge = (CEdge) it.next();
			sb.append(edge + "\n");
		}
		return sb.toString();
	}

	public String printNodesToString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n====== Nodes ======\n");
		Iterator<CNode> it = nodes.iterator();
		while (it.hasNext()) {
			CNode node = (CNode) it.next();
			sb.append(node + "\n");
		}
		return sb.toString();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(printNodesToString());
		sb.append(printEdgesToString());
		return sb.toString();
	}

	// ============== Generate all paths========================

	public LinkedList<LinkedList<Integer>> computeAllPaths(int startNodeID, int endNodeID) {
		LinkedList<LinkedList<Integer>> paths = new LinkedList<LinkedList<Integer>>();
		LinkedList<Integer> visited = new LinkedList<Integer>();
		visited.add(startNodeID);
		breadthFirst(visited, endNodeID, paths);
		return paths;
	}

	public void breadthFirst(LinkedList<Integer> visited, int endNodeID, LinkedList<LinkedList<Integer>> paths) {
		List<Integer> nodes = getAdjacentNodeIDs(visited.getLast());
		// examine adjacent nodes
		for (Integer node : nodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.equals(endNodeID)) {
				visited.add(node);
				// get a new copy of the visited list before add to paths,
				// otherwise, it will be removed by next statement
				paths.add(new LinkedList<Integer>(visited));
				visited.removeLast();
				break;
			}
		}
		// in breadth-first, recursion needs to come after visiting adjacent
		// nodes
		for (Integer node : nodes) {
			if (visited.contains(node) || node.equals(endNodeID)) {
				continue;
			}
			visited.addLast(node);
			breadthFirst(visited, endNodeID, paths);
			visited.removeLast();
		}
	}

	private void printPath(LinkedList<Integer> visited) {
		for (Integer node : visited) {
			System.out.print(node);
			System.out.print("   ");
		}
		System.out.println();
	}

	public void printPaths(LinkedList<LinkedList<Integer>> paths) {
		for (LinkedList<Integer> path : paths) {
			printPath(path);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public CPath constructPathFormNodeIDs (int id, List<Integer> nodeIDs){
		CPath path=new CPath(id);
		for(Integer nodeId:nodeIDs){
			path.add(getCNode(nodeId));
		}
		return path;
	}

	public CPaths constructPaths (){
		int pathID=1;
		CPaths paths=new CPaths(1);//need set coverage and method name later
		LinkedList<LinkedList<Integer>> pathsWithIDs=computeAllPaths(getRoot().getId(), getSink().getId()); 
		for(LinkedList<Integer> p:pathsWithIDs){
			paths.add(constructPathFormNodeIDs(pathID++,p));
		}
		return paths;
	}
}

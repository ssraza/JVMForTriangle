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

import com.gannon.program.cfg.Node;
import com.gannon.program.cfg.TEdge;
import com.gannon.program.cfg.TNode;

public final class CGraph {
	private static final List<CEdge> EMPTY_EDGES = Collections.emptyList();
	private final Set<CNode> nodes;
	private final Set<CEdge> edges;
	private Map<CNode, Map<CNode, List<CEdge>>> adjacency;

	private int nextCNodeId;
	private int nextCEdgeId;

	public CGraph() {
		this.nodes = new HashSet<CNode>();
		this.edges = new HashSet<CEdge>();
		this.adjacency = new HashMap<CNode, Map<CNode, List<CEdge>>>();
	}
	
	public CGraph(String inputText) {
		super();
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
				int blockID= Integer.parseInt(listOfLines[0]);
				
				// creating new block
				Block newNodeBlock = new Block(blockID);
				
				// creating new node
				CNode newNode = newCNode(newNodeBlock);
				
				index++;
			}
			
			index = 1;
			// adding all edges
			while (index < listOfLines.length) 
			{
				String currentLine = listOfLines[index];
				String[] listOfStrings; // Array of strings
				listOfStrings = currentLine.split(" "); // splitting the string
														// to get the list of
														// nodes
				// getting parent node for current line which is the first node
				CNode parentNode = getCNode(Integer.parseInt(listOfLines[0]));;
				
				// Processing all nodes in the current line after the first node
				for (int j = 1; j < listOfStrings.length; j++) {
					// getting block id from string
					int newBlockID= Integer.parseInt(listOfLines[j]);
					//getting child node
					CNode childNode = getCNode(newBlockID);
					// creating new edge
					newCEdge(parentNode, childNode);
				}

			}

		} catch (Exception e) {
			System.out.println("Error reading input string");
		}
	}

	public CNode getCNode(int nodeID){
		 for (CNode n1 : this.nodes){
			if(n1.equals(nodeID)){
				return n1;
			}
		}
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

	public void merge(final Set<CNode> nodes, final Set<CEdge> edges) {
		this.nodes.addAll(nodes);
		this.edges.addAll(edges);
	}

	public CEdge newCEdge(final CNode source, final CNode target) {
		return addCEdge(new CEdge(this.nextCEdgeId++, source, target));
	}

	public CNode newCNode(final Block data) {
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

	
	
	public Set<CNode> getNodes() {
		return nodes;
	}

	public Set<CEdge> getEdges() {
		return edges;
	}

	public void mergeCallingGraph(CGraph g, CNode invokeCNode) {
		Set<CNode> gNodes = g.getNodes();
		Set<CEdge> gEdges = g.getEdges();
		merge(gNodes,gEdges);
		addCEdge(newCEdge(invokeCNode, g.getRoot())); 
	 //	addCEdge(newCEdge( g.getSink(), invokeCNode));
	}
	
	public Set<CNode> getTargetNodes(){
		Set<CNode> targetNodes=new HashSet<CNode>();
		Iterator<CEdge> it = edges.iterator();	 
		while(it.hasNext()){
			CEdge edge=(CEdge)it.next();
			CNode tat=edge.getTarget();
			targetNodes.add(tat);
		}
		return targetNodes;
	}
	
	public Set<CNode> getSourceNodes(){
		Set<CNode> sourceNode=new HashSet<CNode>();
		Iterator<CEdge> it = edges.iterator();	 
		while(it.hasNext()){
			CEdge edge=(CEdge)it.next();
			CNode tat=edge.getSource();
			sourceNode.add(tat);
		}
		return sourceNode;
	}
	
	public CNode getRoot(){
		Set<CNode> set=new HashSet<CNode>(nodes);
		set.removeAll(getTargetNodes());
		if(set.size()==1){
			return (CNode)set.iterator().next();
		}
		return null; 
	}
	
	public CNode getSink(){
		Set<CNode> set=new HashSet<CNode>(nodes);
		set.removeAll(getSourceNodes());
		if(set.size()==1){
			return (CNode)set.iterator().next();
		}
		return null; 
	}
	
	public CPath getLongestPath(){
		
	}
	
	private Set<CNode> findDominatorNodes(CNode node){
		
	}
	
	public int getNumberOfPathsBetweenTwoNodes(CNode node1, CNode node2) {
		
	}
	
	public List<CNode> getListOfChildNodes(CNode parentNode) {
		Map<CNode, List<CEdge>> srcAdjacency = this.adjacency.get(parentNode);
		if (srcAdjacency == null) {
			srcAdjacency = new HashMap<CNode, List<CEdge>>();
			return (List<CNode>) srcAdjacency.keySet();
			
		}
		return null;
		
	}
}

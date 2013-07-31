package com.gannon.Graph.GraphObjects;

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

public final class Graph {
	private static final List<CEdge> EMPTY_EDGES = Collections.emptyList();
	private final Set<CNode> nodes;
	private final Set<CEdge> edges;
	private Map<CNode, Map<CNode, List<CEdge>>> adjacency;

	private int nextCNodeId;
	private int nextCEdgeId;

	public Graph() {
		this.nodes = new HashSet<CNode>();
		this.edges = new HashSet<CEdge>();
		this.adjacency = new HashMap<CNode, Map<CNode, List<CEdge>>>();
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

	public void mergeGraph(Graph g, CNode invokeCNode) {
		Set<CNode> gNodes = g.getNodes();
		Set<CEdge> gEdges = g.getEdges();
		merge(gNodes,gEdges);
		addCEdge(new CEdge(invokeCNode, gNodes.get(0)));
		addCEdge(new CEdge(gNodes.get(gNodes.size() - 1), invokeCNode));
	}
	
	public Set<CNode> getTargetNodest(){
		Set<CNode> targetNodes=new HashSet<CNode>();
		Iterator<CEdge> it = edges.iterator();	 
		while(it.hasNext()){
			CEdge edge=(CEdge)it.next();
			CNode tat=edge.getTarget();
			targetNodes.add(tat);
		}
		return targetNodes;
	}
	
	
}

package com.gannon.Graph.GraphObjects;

import java.util.*;

public class Graph {
	private ArrayList<Block> nodes = new ArrayList<Block>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();

	public Graph() {
		nodes = new ArrayList<Block>();
		edges = new ArrayList<Edge>();
	}

	public Graph(ArrayList<Block> nodes, ArrayList<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}

	public void addNode(Block block) {
		nodes.add(block);
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	public int getNumberOfNodes() {
		return nodes.size();
	}

	public int getNumberOfEdges() {
		return edges.size();
	}

	public ArrayList<Block> getNodes() {
		return nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void mergeGraph(Graph g, Block invokeBlock) {
		ArrayList<Block> gNodes = g.getNodes();
		ArrayList<Edge> gEdges = g.getEdges();
		for (int i = 0; i < gNodes.size(); i++) {
			nodes.add(gNodes.get(i));
		}
		for (int j = 0; j < gEdges.size(); j++) {
			edges.add(gEdges.get(j));
		}
		edges.add(new Edge(invokeBlock, gNodes.get(0)));
		edges.add(new Edge(gNodes.get(gNodes.size() - 1), invokeBlock));
	}

	public static class Edge {
		public Block s; // Starting vertex of the edge
		public Block e; // Ending vertex of the edge

		public Edge(Block s, Block e) {
			this.s = s;
			this.e = e;
		}

		public Block getNodeS() {
			return s;
		}

		public Block getNodeE() {
			return e;
		}
	}
}

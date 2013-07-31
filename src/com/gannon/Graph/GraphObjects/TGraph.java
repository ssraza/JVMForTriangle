package com.gannon.Graph.GraphObjects;

import java.util.ArrayList;

public class TGraph {
	private ArrayList<CNode> nodes = new ArrayList<CNode>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();

	public TGraph() {
		nodes = new ArrayList<CNode>();
		edges = new ArrayList<Edge>();
	}

	public TGraph(ArrayList<CNode> nodes, ArrayList<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}

	public void addNode(CNode block) {
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

	public ArrayList<CNode> getNodes() {
		return nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void mergeGraph(Graph g, CNode invokeCNode) {
		ArrayList<CNode> gNodes = g.getNodes();
		ArrayList<CEdge> gEdges = g.getEdges();
		for (int i = 0; i < gNodes.size(); i++) {
			nodes.add(gNodes.get(i));
		}
		for (int j = 0; j < gEdges.size(); j++) {
			edges.add(gEdges.get(j));
		}
		addEdges(new CEdge(invokeCNode, gNodes.get(0)));
		addEdge(new CEdge(gNodes.get(gNodes.size() - 1), invokeCNode));
	}

	
}

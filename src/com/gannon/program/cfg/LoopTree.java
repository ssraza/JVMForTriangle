package com.gannon.program.cfg;

import java.util.ArrayList;

public class LoopTree {

	ArrayList<TNode> listOfTNodes = new ArrayList<TNode>();
	ArrayList<TEdge> listOfEdges = new ArrayList<TEdge>();
	
	ArrayList<TNode> listOfSearchedTNode = new ArrayList<TNode>();
	private int totalPaths;

	public LoopTree(String inputText) {

		try {
			String[] listOfLines; // Array of strings
			listOfLines = inputText.split("\n"); // splitting the input text to
													// get the list of lines

			int index = 1; // starting from 1 to skip first line

			// Processing all input lines
			while (index < listOfLines.length) {
				String currentLine = listOfLines[index];
				String[] listOfStrings; // Array of strings
				listOfStrings = currentLine.split(" "); // splitting the string
														// to get the list of
														// nodes

				// creating new tnode
				TNode newTNode = findTNode(listOfStrings[0]);
				if (newTNode == null) {
					newTNode = new TNode(listOfStrings[0]);
					listOfTNodes.add(newTNode);
				}

				// Processing all nodes in the current line
				for (int j = 1; j < listOfStrings.length; j++) {
					TNode childTNode = findTNode(listOfStrings[j]);
					if (childTNode == null) {
						childTNode = new TNode(listOfStrings[j]);
						listOfTNodes.add(childTNode);
					}

					// creating new TEdge
					TEdge newTEdge = new TEdge(newTNode, childTNode);
					// adding new TEdge to list of edges
					listOfEdges.add(newTEdge);
				}

				index++;

			}

		} catch (Exception e) {
		}
	}

	// function to search for the root node
	public TNode getRootTNode() {
		boolean isTargetTNode;
		// processing all nodes
		for (int i = 0; i < listOfTNodes.size(); i++) {
			// setting current tnode
			TNode currentTNode = listOfTNodes.get(i);
			// setting target node flag
			isTargetTNode = false;
			// processing all edges
			for (int k = 0; k < listOfEdges.size() && !isTargetTNode; k++) {
				// checking if current node is a target node for current edge
				if (listOfEdges.get(k).getTargetTNode() == currentTNode) {
					isTargetTNode = true;
				}
			}
			if (isTargetTNode == false) {
				return currentTNode;
			}
		}

		return null;
	}

	public TNode findTNode(String nodeName) {
		for (int i = 0; i < listOfTNodes.size(); i++) {
			if (listOfTNodes.get(i).getName().equals(nodeName)) {
				return listOfTNodes.get(i);
			}
		}
		return null;
	}

	public boolean isLeafTNode(TNode node) {
		// processing all edges
		for (int i = 0; i < listOfEdges.size(); i++) {
			// checking if node is a source node for current edge
			if (listOfEdges.get(i).getSourceTNode() == node) {
				return false;
			}
		}
		return true;
	}

	public boolean searchTNode(TNode parentTNode, String nodeName) {
		if (listOfSearchedTNode.indexOf(parentTNode) >= 0
				|| parentTNode == null) {
			return false;
		} else {
			listOfSearchedTNode.add(parentTNode);

			if (parentTNode.getName().equals(nodeName)) {
				return true;
			} else {
				boolean isFound = false;
				for (int j = 0; j < listOfEdges.size(); j++) {
					TEdge currentEdge = listOfEdges.get(j);
					if (currentEdge.getSourceTNode() == parentTNode) {
						isFound |= searchTNode(currentEdge.getTargetTNode(),
								nodeName);
					}
				}

				return isFound;
			}
		}
	}

	public boolean isChildOfTNode(TNode parentTNode, TNode startTNode,
			String nodeName) {
		listOfSearchedTNode.clear();
		listOfSearchedTNode.add(parentTNode);
		return searchTNode(startTNode, nodeName);
	}

	public ArrayList<TNode> getListOfTNodes() {
		return listOfTNodes;
	}

	public void processDominatorNodes() {
		TNode rootNode = getRootTNode();
		ArrayList<String> dominatorNodesList = new ArrayList<String>();

		setDominatorNodes(rootNode, dominatorNodesList);
	}

	public void setDominatorNodes(TNode currentNode,
			ArrayList<String> parentDominatorNodeList) {
		if (currentNode.getIsSetOfDominatorNodesAdded()) {
			currentNode.getSetOfDominatorNodes().retainAll(
					parentDominatorNodeList);
			currentNode.getSetOfDominatorNodes().add(currentNode.getName());
			// finding and processing child nodes
			for (int j = 0; j < listOfEdges.size(); j++) {
				TEdge currentEdge = listOfEdges.get(j);
				// checking if current node is source node for current edge
				if (currentEdge.getSourceTNode() == currentNode) {
					// getting child node
					TNode childNode = currentEdge.getTargetTNode();
					if (!currentNode.getSetOfDominatorNodes().contains(
							childNode.getName())) {
						setDominatorNodes(childNode,
								currentNode.getSetOfDominatorNodes());
					}
				}
			}
		} else {
			currentNode.setIsSetOfDominatorNodesAdded(true);
			currentNode.getSetOfDominatorNodes().add(currentNode.getName());
			currentNode.getSetOfDominatorNodes()
					.addAll(parentDominatorNodeList);

			// finding and processing child nodes
			for (int j = 0; j < listOfEdges.size(); j++) {
				TEdge currentEdge = listOfEdges.get(j);
				// checking if current node is source node for current edge
				if (currentEdge.getSourceTNode() == currentNode) {
					// getting child node
					TNode childNode = currentEdge.getTargetTNode();
					if (!currentNode.getSetOfDominatorNodes().contains(
							childNode.getName())) {
						setDominatorNodes(childNode,
								currentNode.getSetOfDominatorNodes());
					}
				}
			}
		}
	}

	private void processGetNumberOfNodes(TNode endNode, TNode currentNode,
			ArrayList<TNode> currentPath) {
		// finding and processing child nodes
		for (int j = 0; j < listOfEdges.size(); j++) {
			TEdge currentEdge = listOfEdges.get(j);
			// checking if current node is source node for current edge
			if (currentEdge.getSourceTNode() == currentNode) {
				// getting child node
				TNode childNode = currentEdge.getTargetTNode();
				if (childNode == endNode) {
					totalPaths += 1;
				} else if (!currentPath.contains(childNode)) {
					currentPath.add(currentNode);
					processGetNumberOfNodes(endNode, childNode, currentPath);
					currentPath.remove(currentNode);
				}
			}
		}
		return;
	}

	public int getNumberOfPathsBetweenTwoNodes(TNode node1, TNode node2) {
		// creating list of visited nodes
		ArrayList<TNode> currentPath = new ArrayList<TNode>();
		totalPaths = 0;
		processGetNumberOfNodes(node2, node1, currentPath);
		return totalPaths;

	}

	private int processGetLongestPath(TNode endNode, TNode currentNode,
			ArrayList<TNode> currentPath, int longestPath) {
		int newLongestPath = 0;

		// finding and processing child nodes
		for (int j = 0; j < listOfEdges.size(); j++) {
			TEdge currentEdge = listOfEdges.get(j);
			// checking if current node is source node for current edge
			if (currentEdge.getSourceTNode() == currentNode) {
				// getting child node
				TNode childNode = currentEdge.getTargetTNode();

				if (childNode == endNode) {
					// checking if current path count is bigger than longestPath
					if (currentPath.size() > longestPath) {
						longestPath = currentPath.size();
					}

				} else if (!currentPath.contains(childNode)) {
					currentPath.add(currentNode);
					newLongestPath = processGetLongestPath(endNode, childNode,
							currentPath, longestPath);
					currentPath.remove(currentNode);
				}
			}

			if (newLongestPath > longestPath) {
				longestPath = newLongestPath;
			}
		}
		return longestPath;
	}

	public int findLongestBetweenTwoNodes(TNode node1, TNode node2) {
		// creating list of visited nodes
		ArrayList<TNode> currentPath = new ArrayList<TNode>();
		return processGetLongestPath(node2, node1, currentPath, 0);
	}

	public ArrayList<TNode> getListOfChildNodes(TNode parentNode) {
		ArrayList<TNode> listOfChildNodes = new ArrayList<TNode>();
		// finding and processing child nodes
		for (int j = 0; j < listOfEdges.size(); j++) {
			TEdge currentEdge = listOfEdges.get(j);
			// checking if current node is source node for current edge
			if (currentEdge.getSourceTNode() == parentNode) {
				// adding child node to list of nodes
				listOfChildNodes.add(currentEdge.getTargetTNode());
			}
		}
		return listOfChildNodes;
	}
}

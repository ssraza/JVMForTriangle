package com.gannon.program.cfg;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gannon.bytecode.controlflowgraph.CGraph;
import com.gannon.bytecode.controlflowgraph.CNode;
import com.gannon.bytecode.controlflowgraph.CPath;



public class CFGPanel extends GraphPanel {

    private final ArrayList<Node> listOfNodes;
    private CGraph cGraph;    // contains list of cfg node
    CNode rootNode;  // root node of the cfg tree

    public Node findNode(int nodeID) {
        for (int i = 0; i < listOfNodes.size(); i++) {
            Node node = listOfNodes.get(i);
            if (node.getData().getLabel().equals( String.valueOf(nodeID))) {
                return node;
            }
        }
        return null;
    }

    

    public void drawCFGTree() {
        // getting list of nodes and adding them to the graph
        Set<CNode> listOfCNodes =cGraph.getCNodes();
        for (Iterator<CNode> iterator = listOfCNodes.iterator(); iterator.hasNext(); ) {
        	CNode nodeToInsert =  iterator.next();
        	//adding tnode to graph panel
            Node newNode = addNode( String.valueOf( nodeToInsert.getId()), Color.BLACK, Color.GREEN, Color.BLUE, 20.0f);
            // adding new node to listOfNodes
            listOfNodes.add(newNode);
        }

        // adding edges between nodes
        for (Iterator<CNode> iterator = listOfCNodes.iterator(); iterator.hasNext(); ) {
        	CNode parentNode =  iterator.next();
            // getting node from listOfNodes
            Node node = findNode(parentNode.getId());

            // getting list of child node for tnode
            List<CNode> listOfChildNodes =  cGraph.getAdjacentNodes(parentNode);// .getAdjacentNodes(tnode);
            
            // processing child nodes
            for (int j = 0; j < listOfChildNodes.size(); j++) {
            	CNode childNode = listOfChildNodes.get(j);
            	// getting node from listOfNodes
                Node graphNode = findNode(childNode.getId());
                
                CPath longestPath =  cGraph.getLongestPath(parentNode,childNode);
                
                int  lengthOfLongestPath = longestPath.getNodes().size();// cGraph.getLongestPath(childNode, cnode)"path between " + tnode.getName() + " and " + tnode.getLeft().getName() + " is : " + numberOfPaths);
                
                int numberOfPaths  = cGraph.getNumberOfPathsBetweenTwoNodes(parentNode, childNode);
                //System.out.println("Longest path between " + tnode.getName() + " and " + tnode.getLeft().getName() + " is : " + longestPath);
                
               
                if(cGraph.isLoopEdge(parentNode, childNode)){
                    // Finding max path for loop edge
                  //  int longestPathForLoop = cfgTree.findLongestBetweenTwoNodes( childNode,tnode);
                     // adding an  invisible edge between the two nodes
                    addEdgeWithNumberOfInvisibleNodes(node, graphNode, Color.BLUE, Color.YELLOW, 2.0f, true, "",lengthOfLongestPath);
                }
                //  checking if the edge is a branch statement (goto,if-then)
                else if(cGraph.isLoopEdge( childNode,parentNode) && (numberOfPaths > 1))
                {
                    // adding an edge between the two nodes
                    addEdgeWithNumberOfInvisibleNodes(node, graphNode, Color.BLUE, Color.YELLOW, 2.0f, true, "",lengthOfLongestPath);
                    
                }else{
                    addEdge(node, graphNode, Color.BLUE, Color.YELLOW, 2.0f, true, "");
                }   
            }
        }

        // setting root node flag for the root node
        findNode(rootNode.getId()).getData().setIsRoot(true);
    }

    public CFGPanel(CGraph cGraph, int width,int height) {
    	super(new ForceDirectedLayout(), width, height);
    	// initializing graph panel
    	
    	// initializing list of nodes
        this.listOfNodes = new ArrayList<Node>();

        // reading CFG file to get nodes and edges
        this.cGraph = cGraph;
        
        // getting root node
        rootNode = this.cGraph.getRoot();
        

        // drawing CFG
        drawCFGTree();
    }

    public void addEdge(Node node, Node otherNode, Color edgeColor, Color edgeLabelColor, float weight, boolean directed, String edgeLabel) {
        this.addEdge(node, otherNode,
                new EdgeData().color(edgeColor).labelColor(edgeLabelColor).weight(weight)
                .directional(directed).label(edgeLabel));
    }
    
    public void addEdgeWithInvisibleNode(Node node, Node otherNode, Color edgeColor, Color edgeLabelColor, float weight, boolean directed, String edgeLabel) {
        // Adding invicible node
        Node invicibleNode = addInvicibleNode("", Color.BLACK, Color.BLACK, Color.WHITE, 1);
        // adding edge between first connected node and the invicible node
        this.addEdge(node, invicibleNode,
                new EdgeData().color(edgeColor).labelColor(edgeLabelColor).weight(weight)
                .directional(false).label(edgeLabel));
         // adding edge between the invicible node and the second connected node
        this.addEdge(invicibleNode, otherNode,
                new EdgeData().color(edgeColor).labelColor(edgeLabelColor).weight(weight)
                .directional(directed).label(edgeLabel));

    }

    public void addEdgeWithNumberOfInvisibleNodes(Node node, Node otherNode, Color edgeColor, Color edgeLabelColor, float weight, boolean directed, String edgeLabel,int numberOfNodes) {
        numberOfNodes = numberOfNodes*1;
        // Adding invicible node
        
        Node invicibleNode = addInvicibleNode("", Color.BLACK, Color.BLACK, Color.WHITE, 1);
        // adding edge between first connected node and the invicible node
        this.addEdge(node, invicibleNode,
                new EdgeData().color(edgeColor).labelColor(edgeLabelColor).weight(weight)
                .directional(false).label(edgeLabel));
        
        for(int i=0;i<numberOfNodes-1;i++){
            Node newInvicibleNode = addInvicibleNode("", Color.BLACK, Color.BLACK, Color.WHITE, 1);
            
            // adding edge between the invicible node and the new invisible node 
            this.addEdge(invicibleNode, newInvicibleNode,
                new EdgeData().color(edgeColor).labelColor(edgeLabelColor).weight(weight).directional(false).label(edgeLabel).isInvisible(true));
            
            invicibleNode = newInvicibleNode;
        }
        
         // adding edge between the invicible node and the second connected node
        this.addEdge(invicibleNode, otherNode,
                new EdgeData().color(edgeColor).labelColor(edgeLabelColor).weight(weight)
                .directional(directed).label(edgeLabel));

    }
    
    public Node addNode(String label, Color labelColor, Color nodeColor, Color boxColor, float mass) {
        final Node node = this.addNode(new NodeData().label(label).labelColor(labelColor)
                .backgroundColor(nodeColor).boxColor(boxColor).mass(mass));
        return node;
    }

    public Node addInvicibleNode(String label, Color labelColor, Color nodeColor, Color boxColor, float mass) {
        final Node node = this.addNode(new NodeData().label(label).labelColor(labelColor)
                .backgroundColor(nodeColor).boxColor(boxColor).mass(mass));
        node.getData().setIsInvicible(true);
        return node;
    }
}

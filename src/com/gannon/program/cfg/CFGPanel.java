package com.gannon.program.cfg;

import java.awt.Color;
import java.util.ArrayList;

import com.gannon.bytecode.controlflowgraph.CGraph;
import com.gannon.bytecode.controlflowgraph.CNode;



public class CFGPanel extends GraphPanel {

    private final ArrayList<Node> listOfNodes;
    private CGraph cGraph;    // contains list of cfg node
    CNode rootNode;  // root node of the cfg tree

    public Node findNode(String label) {
        for (int i = 0; i < listOfNodes.size(); i++) {
            Node node = listOfNodes.get(i);
            if (node.getData().getLabel().equals(label)) {
                return node;
            }
        }
        return null;
    }

    public void ReadCFG() {
    	String inputText = "start_node end_nodes\n" +
    			"B0 B1 B3\n" +
    			"B1 B3\n" +
    			"B3 B5 B20\n" +
    			"B4 B6 B7\n" +
    			"B5 B10 B11\n" +
    			"B6 B8 B9\n" +
    			"B8\n" +
    			"B7\n" +
    			"B9 B4\n" +
    			"B10 B12\n" +
    			"B11\n" +
    			"B12 B11 B13\n" +
    			"B13 B14 B15\n" +
    			"B14 B11 B17\n" +
    			"B15 B12\n" +
    			"B17 B18 B15\n" +
    			"B18\n" +
    			"B19 B0\n" +
    			"B20 B4\n";

        // creating loop algorithm tree
    	cGraph= new CGraph(inputText);

        // getting root node
        rootNode = cGraph.getRoot();
    }

    public void drawCFGTree() {
        // getting list of nodes and adding them to the graph
        ArrayList<TNode> listOfTNodes =cGraph.getCNodes();
        for (int k = 0; k < listOfTNodes.size(); k++) {
            TNode nodeToInsert = listOfTNodes.get(k);
            //adding tnode to graph panel
            Node newNode = addNode(nodeToInsert.getName(), Color.BLACK, Color.GREEN, Color.BLUE, 20.0f);
            // adding new node to listOfNodes
            listOfNodes.add(newNode);
        }

        // adding edges between nodes
        for (int k = 0; k < listOfTNodes.size(); k++) {
            TNode tnode = listOfTNodes.get(k);
            // getting node from listOfNodes
            Node node = findNode(tnode.getName());

            // getting list of child node for tnode
            ArrayList<TNode> listOfChildNodes = cfgTree.getListOfChildNodes(tnode);
            
            // processing child nodes
            for (int j = 0; j < listOfChildNodes.size(); j++) {
            	TNode childNode = listOfChildNodes.get(j);
            	// getting node from listOfNodes
                Node graphNode = findNode(childNode.getName());
                
                int numberOfPaths = cfgTree.findLongestBetweenTwoNodes(tnode,childNode);
               // System.out.println("path between " + tnode.getName() + " and " + tnode.getLeft().getName() + " is : " + numberOfPaths);
                
                int longestPath = cfgTree.findLongestBetweenTwoNodes(tnode, childNode);
                //System.out.println("Longest path between " + tnode.getName() + " and " + tnode.getLeft().getName() + " is : " + longestPath);
                
               
                if(tnode.getSetOfDominatorNodes().contains(childNode.getName())){
                    // Finding max path for loop edge
                    int longestPathForLoop = cfgTree.findLongestBetweenTwoNodes( childNode,tnode);
                     // adding an  invisible edge between the two nodes
                    addEdgeWithNumberOfInvisibleNodes(node, graphNode, Color.BLUE, Color.YELLOW, 2.0f, true, "",longestPathForLoop);
                }
                //  checking if the edge is a branch statement (goto,if-then)
                else if(childNode.getSetOfDominatorNodes().contains(tnode.getName()) && (numberOfPaths > 1))
                {
                    // adding an edge between the two nodes
                    addEdgeWithNumberOfInvisibleNodes(node, graphNode, Color.BLUE, Color.YELLOW, 2.0f, true, "",longestPath);
                    
                }else{
                    addEdge(node, graphNode, Color.BLUE, Color.YELLOW, 2.0f, true, "");
                }   
            }
        }

        // setting root node flag for the root node
        findNode(rootNode.getName()).getData().setIsRoot(true);
    }

    public CFGPanel(int width,int height) {
    	super(new ForceDirectedLayout(), width, height);
    	// initializing graph panel
    	
    	// initializing list of nodes
        this.listOfNodes = new ArrayList<Node>();

        // reading CFG file to get nodes and edges
        ReadCFG();
        
        cfgTree.processDominatorNodes();
        
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

package com.gannon.program.cfg;

import java.awt.Color;
import java.util.ArrayList;

public class LoopTree {

    ArrayList<TNode> listOfTNodes = new ArrayList<TNode>();
    ArrayList<String> nodeNameList = new ArrayList<String>();
    ArrayList<TNode> listOfTraversedTNodes = new ArrayList<TNode>();
    int xLevel;
    ArrayList<TNode> listOfSearchedTNode = new ArrayList<TNode>();
    ArrayList<TNode> listOfTwoWayTNodes = new ArrayList<TNode>();
    ArrayList<TNode> listOfTNodesToBeTraversed = new ArrayList<TNode>();
    private int totalPaths;

    public LoopTree(String inputText) {

        try {
            String[] listOfLines; // Array of strings
            listOfLines = inputText.split("\n"); // splitting the input text to get the list of lines 

            int index = 1; // starting from 1 to skip first line

            while (index < listOfLines.length) {
            	String currentLine = listOfLines[index];
                String[] listOfStrings; // Array of strings
                listOfStrings = currentLine.split(" "); // splitting the string to get the list of nodes 

                // adding first string to nodeNameList
                nodeNameList.add(listOfStrings[0]);

                // creating new tnode
                TNode newTNode = findTNode(listOfStrings[0]);
                if (newTNode == null) {
                    newTNode = new TNode(listOfStrings[0]);
                    listOfTNodes.add(newTNode);
                }

                for (int j = 1; j < listOfStrings.length; j++) {
                    nodeNameList.add(listOfStrings[j]);

                    TNode childTNode = findTNode(listOfStrings[j]);
                    if (childTNode == null) {
                        childTNode = new TNode(listOfStrings[j]);
                        listOfTNodes.add(childTNode);
                    }

                    if (newTNode.getLeft() == null) {
                        newTNode.setLeft(childTNode);
                    } else {
                        newTNode.setRight(childTNode);
                    }
                }
                
                index++;

            }

       

        } catch (Exception e) {
        }
    }

    // function to search for the root node
    public TNode getRootTNode() {
        boolean isChildTNode;
        for (int i = 0; i < nodeNameList.size(); i++) {
            isChildTNode = false;
            for (int k = 0; k < listOfTNodes.size() && !isChildTNode; k++) {
                TNode leftTNode = listOfTNodes.get(k).getLeft();
                TNode rightTNode = listOfTNodes.get(k).getRight();
                if (leftTNode != null) {
                    if (leftTNode.getName().equals(nodeNameList.get(i))) {
                        isChildTNode = true;
                    }
                }
                if (rightTNode != null) {
                    if (rightTNode.getName().equals(nodeNameList.get(i))) {
                        isChildTNode = true;
                    }

                }
            }
            if (isChildTNode == false) {
                TNode rootTNode = findTNode(nodeNameList.get(i));
                LoopTree(0, rootTNode, false);
                rootTNode.setColor(Color.GRAY);
                return rootTNode;
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

    public void initLoopTree() {
        listOfTraversedTNodes.clear();
        xLevel = 0;
    }

    public boolean isLeafTNode(TNode node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return true;
        }
        return false;

    }

    public Boolean isAlreadyTraversed(TNode node) {
        if (listOfTraversedTNodes.indexOf(node) >= 0) {
            return true;
        }
        return false;

    }

    public boolean isChildOfTraversedTNode(TNode node) {
        for (int i = 0; i < listOfTraversedTNodes.size(); i++) {
            TNode parentTNode = listOfTraversedTNodes.get(i);
            if (parentTNode.getLeft() == node) {
                return true;
            } else if (parentTNode.getRight() == node) {
                return true;

            }
        }
        return false;

    }

    public void LoopTree(int yLevel, TNode currentTNode, boolean isInsideLoop) {

        if (isAlreadyTraversed(currentTNode)
                || isGoingToBeTraversedByParentTNode(currentTNode)) {
            currentTNode.setColor(Color.RED);
            return;
        } else {
            listOfTraversedTNodes.add(currentTNode);
        }

        if (isLeafTNode(currentTNode)) {
            currentTNode.setColor(Color.GREEN);
        }

        // checking if current node is a child of one of the two way points
        if (listOfTwoWayTNodes.indexOf(currentTNode) >= 0) {
            listOfTwoWayTNodes.remove(currentTNode);
            isInsideLoop = false;
        }

        currentTNode.setLevel(xLevel, yLevel);

        if ((currentTNode.getLeft() != null) && (currentTNode.getRight() != null)
                && !isGoingToBeTraversedByParentTNode(currentTNode.getRight())
                && !isGoingToBeTraversedByParentTNode(currentTNode.getLeft())) {
            // if right node is child of left node
            if (isChildOfTNode(currentTNode, currentTNode.getLeft(), currentTNode
                    .getRight().getName())
                    && !isInsideLoop) {
                ++xLevel;
                // adding node to list of two way nodes
                listOfTwoWayTNodes.add(currentTNode.getRight());

                if (isInsideLoop) {
                    LoopTree(++yLevel, currentTNode.getLeft(), false);
                } else {
                    LoopTree(yLevel, currentTNode.getLeft(), true);
                }
            } else // if left node is child of right node
            if (isChildOfTNode(currentTNode, currentTNode.getRight(), currentTNode
                    .getLeft().getName())
                    && !isInsideLoop) {
                ++xLevel;
                // adding node to list of two way nodes
                listOfTwoWayTNodes.add(currentTNode.getLeft());

                if (isInsideLoop) {
                    LoopTree(++yLevel, currentTNode.getRight(), false);
                } else {
                    LoopTree(yLevel, currentTNode.getRight(), true);
                }
            } else {
                ++xLevel;
                if (isLeafTNode(currentTNode.getRight())) {
                    listOfTNodesToBeTraversed.add(currentTNode.getRight());
                    LoopTree(yLevel + 1, currentTNode.getLeft(), isInsideLoop);
                    listOfTNodesToBeTraversed.remove(currentTNode.getRight());
                    LoopTree(yLevel, currentTNode.getRight(), isInsideLoop);

                } else {
                    // ---
                    listOfTNodesToBeTraversed.add(currentTNode.getLeft());
                    LoopTree(yLevel + 1, currentTNode.getRight(), false);
                    listOfTNodesToBeTraversed.remove(currentTNode.getLeft());
                    LoopTree(yLevel, currentTNode.getLeft(), false);

                }

            }

        } else if (currentTNode.getLeft() != null) {
            if (isGoingToBeTraversedByParentTNode(currentTNode.getLeft())) {
                ++xLevel;
                LoopTree(yLevel, currentTNode.getRight(), isInsideLoop);
            } else {
                ++xLevel;
                LoopTree(yLevel, currentTNode.getLeft(), isInsideLoop);
            }

        }

    }

    public boolean searchTNode(TNode parentTNode, String nodeName) {
        if (listOfSearchedTNode.indexOf(parentTNode) >= 0 || parentTNode == null) {
            return false;
        } else {
            listOfSearchedTNode.add(parentTNode);

            if (parentTNode.getName().equals(nodeName)) {
                return true;
            } else {
                if (parentTNode.getLeft() != null
                        && parentTNode.getRight() != null) {
                    return searchTNode(parentTNode.getLeft(), nodeName)
                            || searchTNode(parentTNode.getRight(), nodeName);
                } else if (parentTNode.getLeft() != null) {
                    return searchTNode(parentTNode.getLeft(), nodeName);
                }

            }
            return false;
        }
    }

    public boolean isGoingToBeTraversedByParentTNode(TNode node) {
        return listOfTNodesToBeTraversed.indexOf(node) >= 0;
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

    public void setDominatorNodes(TNode currentNode, ArrayList<String> parentDominatorNodeList) {
        if (currentNode.getIsSetOfDominatorNodesAdded()) {
            currentNode.getSetOfDominatorNodes().retainAll(parentDominatorNodeList);
            currentNode.getSetOfDominatorNodes().add(currentNode.getName());
            if (currentNode.getLeft() != null && !currentNode.getSetOfDominatorNodes().contains(currentNode.getLeft().getName())) {
                setDominatorNodes(currentNode.getLeft(), currentNode.getSetOfDominatorNodes());
            }
            if (currentNode.getRight() != null && !currentNode.getSetOfDominatorNodes().contains(currentNode.getRight().getName())) {
                setDominatorNodes(currentNode.getRight(), currentNode.getSetOfDominatorNodes());
            }
        } else {
            currentNode.setIsSetOfDominatorNodesAdded(true);
            currentNode.getSetOfDominatorNodes().add(currentNode.getName());
            currentNode.getSetOfDominatorNodes().addAll(parentDominatorNodeList);
            if (currentNode.getLeft() != null && !currentNode.getSetOfDominatorNodes().contains(currentNode.getLeft().getName())) {
                setDominatorNodes(currentNode.getLeft(), currentNode.getSetOfDominatorNodes());
            }
            if (currentNode.getRight() != null && !currentNode.getSetOfDominatorNodes().contains(currentNode.getRight().getName())) {
                setDominatorNodes(currentNode.getRight(), currentNode.getSetOfDominatorNodes());
            }
        }

    }

    private void processGetNumberOfNodes(TNode endNode,TNode currentNode,ArrayList<TNode> currentPath){
         // processing left child node
        if(currentNode.getLeft() != null){
            if(currentNode.getLeft() == endNode){
                totalPaths += 1;
            }else if(!currentPath.contains(currentNode.getLeft())){
                currentPath.add(currentNode);
                processGetNumberOfNodes(endNode, currentNode.getLeft(), currentPath);
                currentPath.remove(currentNode);        
            }
        }
        // processing right child node
        if(currentNode.getRight()!= null){
            if(currentNode.getRight() == endNode){
                totalPaths += 1;
            }else if(!currentPath.contains(currentNode.getRight())){
                currentPath.add(currentNode);
                processGetNumberOfNodes(endNode, currentNode.getRight(), currentPath);
                currentPath.remove(currentNode);        
            }
        }
         
        return ;
    }
    
    public int getNumberOfPathsBetweenTwoNodes(TNode node1, TNode node2) {
        // creating list of visited nodes
        ArrayList<TNode> currentPath = new ArrayList<TNode>();
       totalPaths =0;
       processGetNumberOfNodes(node2,node1,currentPath);
       return totalPaths;
        
    }
    
      private int processGetLongestPath(TNode endNode,TNode currentNode,ArrayList<TNode> currentPath, int longestPath){
         int newLongestPath = 0;
         
         // processing left child node
        if(currentNode.getLeft() != null){
            if(currentNode.getLeft() == endNode){
                // checking if current path count is bigger than longestPath
                if(currentPath.size() > longestPath){
                    longestPath = currentPath.size();
                }
                
            }else if(!currentPath.contains(currentNode.getLeft())){
                currentPath.add(currentNode);
                newLongestPath = processGetLongestPath(endNode, currentNode.getLeft(), currentPath,longestPath);
                currentPath.remove(currentNode);        
            }
        }
        
        if(newLongestPath > longestPath){
            longestPath = newLongestPath;
        }
         
        // processing right child node
        if(currentNode.getRight()!= null){
            if(currentNode.getRight() == endNode){
                // checking if current path count is bigger than longestPath
                if(currentPath.size() > longestPath){
                    longestPath = currentPath.size();
                }
            }else if(!currentPath.contains(currentNode.getRight())){
                currentPath.add(currentNode);
                newLongestPath = processGetLongestPath(endNode, currentNode.getRight(), currentPath,longestPath);
                currentPath.remove(currentNode);        
            }
        }
         
        if(newLongestPath > longestPath){
            longestPath = newLongestPath;
        }
        
        return longestPath;
    }
      
    public int findLongestBetweenTwoNodes(TNode node1,TNode node2){
           // creating list of visited nodes
        ArrayList<TNode> currentPath = new ArrayList<TNode>();
       return processGetLongestPath(node2,node1,currentPath,0);
    }

}

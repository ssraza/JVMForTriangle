package com.gannon.program.cfg;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Bader
 */
public class TNode {
	private String name; // node name
	private TNode left; // connected left child node
	private TNode right; // connected right child node
	private int yLevel; // y position of the node
	private int xLevel; // x position of the node
	private Color color; // node color
        private ArrayList <String>setOfDominatorNodes;
        private boolean isSetOfDominatorNodesAdded=false;

        
        
	public TNode(String name) {
		this.name = name;
		left = null;
		right = null;
		color = Color.YELLOW;
                setOfDominatorNodes = new ArrayList<String>();
	}
        
        public ArrayList <String>getSetOfDominatorNodes(){
            return setOfDominatorNodes;
        }

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getyLevel() {
		return yLevel;
	}

	public void setLevel(int xLevel, int yLevel) {
		this.yLevel = yLevel;
		this.xLevel = xLevel;
	}

	public int getxLevel() {
		return xLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TNode getLeft() {
		return left;
	}

	public void setLeft(TNode left) {
		this.left = left;
	}

	public TNode getRight() {
		return right;
	}

	public void setRight(TNode right) {
		this.right = right;
	}
        
        public void setIsSetOfDominatorNodesAdded(boolean t){
            isSetOfDominatorNodesAdded = t;
        }
        
        public boolean getIsSetOfDominatorNodesAdded(){
            return isSetOfDominatorNodesAdded;
        }
	

}

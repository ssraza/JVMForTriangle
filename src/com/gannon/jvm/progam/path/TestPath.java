package com.gannon.jvm.progam.path;

import java.util.ArrayList;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BInstruction;

public class TestPath {
	private int pathId;
	private BMethod bMethod;// the method to where the path belongs
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Object> inputs = new ArrayList<Object>();

	public TestPath() {
		super();
	}

	public TestPath(int pathId) {
		super();
		this.pathId = pathId;
	}

	public int getPathId() {
		return pathId;
	}

	public void setPathId(int pathId) {
		this.pathId = pathId;
	}

	public BMethod getbMethod() {
		return bMethod;
	}

	public void setbMethod(BMethod bMethod) {
		this.bMethod = bMethod;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public void add(Node node){
		this.nodes.add(node);
	}

	public ArrayList<Object> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<Object> inputs) {
		this.inputs = inputs;
	}

	public ArrayList<Integer> getExecutedInsIDs() {
		ArrayList<Integer> ids=new ArrayList<Integer>();
		for(Node node: nodes){
			ids.add(node.getInstructionLineNumber());
		}
		return ids;
	}

	public Node getNode(int lineNumber){
		for(Node node: nodes){
			if(node.getInstruction().getLineNumber()==lineNumber){
				return node;
			}
		}
		return null;
	}

	public ArrayList<BInstruction> getInstrucitons(){
		ArrayList<BInstruction> instructions=new ArrayList<BInstruction>();
		for(Node node: nodes){
			instructions.add(node.getInstruction());
		}
		return instructions;
	}

	public boolean equals(Object obj) {
		boolean result=true;
		if(!(obj instanceof TestPath)){
			return false;
		}
		if(nodes.size()!=((TestPath)obj).getNodes().size()){
			return false;
		}
		for(int i=0;i<nodes.size();i++){
			if(!nodes.get(i).equals(((TestPath)obj).getNodes().get(i))){
				return false;
			}
		}
		return result;
	}

	public int hashCode() {
		return bMethod.hashCode();
	}
}

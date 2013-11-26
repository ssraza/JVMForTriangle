package com.gannon.bytecode.controlflowgraph;

import java.awt.Color;
import java.util.ArrayList;

public class CNode {
	private final int id;
	private String methodName;
	private String className;
	private CBlock block;
	private Color color;

	public Color getColor() {
		return color;
	}

	public CNode(int id) {
		super();
		this.id = id;
	}

	public CNode(final int id, CBlock blcok, Color color) {
		super();
		this.id = id;
		this.block = blcok;
		this.color = color;
	}
	
	public CNode(final int id, String methodName, String className,CBlock blcok, Color color) {
		super();
		this.id = id;
		this.methodName = methodName;
		this.className = className;
		this.block = blcok;
		this.color = color;
	}

	public int getId() {
		return id;
	}
	 public String getMethodName(){
		 return this.methodName;
	 }

	public CBlock getBlock() {
		return block;
	}

	@Override
	public String toString() {
		return "CNode [id=" + id  + " Method Name= " + methodName + "Class Name= " + className + "], block [id=" + block.getId() + "]";
	}

	public void setBlock(CBlock blcok) {
		this.block = blcok;
	}
	
	public boolean hasIfStatement(){
		return block.hasIfStatement();
	}

	public boolean hasInvoke() {
		return block.hasInvoke();
	}

	
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((methodName == null) ? 0 : methodName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CNode other = (CNode) obj;
		if (id != other.id)
			return false;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		return true;
	}
	*/
	

	public static CNode retreaveNode(ArrayList<CNode> nodes, int id, String methodName, String className, CBlock blcok){
		for (CNode node: nodes){
			if (node.id == id && node.methodName == methodName && node.className == className){
				return node;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((methodName == null) ? 0 : methodName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CNode other = (CNode) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (id != other.id)
			return false;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		return true;
	}

	public static boolean nodeExists(ArrayList<CNode> nodes, int id, String methodName, String className, CBlock blcok){
		for (CNode node: nodes){
			if (node.id == id && node.methodName == methodName && node.methodName == className){
				return true;
			}
		}
		return false;
	}

}

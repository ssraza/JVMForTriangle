package com.gannon.jvm.execution.method;

import java.util.ArrayList;

public class BLocalVarTable {
	ArrayList<Object> localVars = new ArrayList<Object>();

	public BLocalVarTable() {
		super();
	}

	public BLocalVarTable(ArrayList<Object> localVar) {
		super();
		this.localVars = localVar;
	}

	public ArrayList<Object> getLocalVars() {
		return localVars;
	}

	public void setLocalVar(ArrayList<Object> localVar) {
		this.localVars = localVar;
	}

	public void add(int index, Object v) {
		localVars.add(index, v);
	}

	public void add(Object v) {
		localVars.add(v);
	}

	public Object getLocalVariable(int index) {
		return localVars.get(index);
	}

	public Integer size() {
		return localVars.size();
	}

	public void setElementAt(int index, Object element) {
		localVars.set(index, element);
	}

	public Object elementAt(int location) {
		return localVars.get(location);
	}

	public void clear(){
		localVars.clear();
	}

}

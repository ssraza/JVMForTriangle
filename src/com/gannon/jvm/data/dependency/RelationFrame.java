package com.gannon.jvm.data.dependency;

import java.util.ArrayList;
import java.util.Stack;

import com.gannon.jvm.progam.path.TestPath;

/**
 * @author Frank Xu
 * 
 *         A relation frame is similar to a BFrame, it only keeps relations
 *         during runtime analyzing. No concept of the active frame necessary
 *         because we don't need to deal with method to method calling
 * 
 */
public class RelationFrame {
	//a given path for analyzing predicate dependency 
	private TestPath targetPath;
	private ArrayList<Relation> relations = new ArrayList<Relation>();
	// This stack works same as operand Stack for generating relations.
	private Stack<String> tempVariableStack = new Stack<String>();

	public RelationFrame() {
		super();
	}

	public TestPath getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(TestPath targetPath) {
		this.targetPath = targetPath;
	}



	public ArrayList<Relation> getRelations() {
		return relations;
	}

	public void setRelations(ArrayList<Relation> relations) {
		this.relations = relations;
	}

	public Stack<String> getTempVariableStack() {
		return tempVariableStack;
	}

	public void setTempVariableStack(Stack<String> tempVariableStack) {
		this.tempVariableStack = tempVariableStack;
	}
}

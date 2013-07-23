package com.gannon.jvm.data.dependency;

import java.util.ArrayList;
import java.util.Stack;

import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.ClassUtility;

/**
 * @author Frank Xu
 *
 *         A relation frame is similar to a BFrame, it only keeps relations
 *         during runtime analyzing. No concept of the active frame necessary
 *         because we don't need to deal with method to method calling
 *
 */
public class RelationFrame {
	// a given path for analyzing predicate dependency
	private TestPath targetPath;
	private Relations relations=new Relations();;
	// This stack works same as operand Stack for generating relations.
	private Stack<String> tempVariableStack = new Stack<String>();

	public RelationFrame() {
		super();
	}

	// for triangle , assume tempVariableStack[1-3] are used for storing inputs
	// variables
	// we convert each variable into a relation
	public void initParameterRelation() {
		if (targetPath == null) {
			System.out.print("Add a target path to the relation frame before analyzing dependency!");
		} else {
			int numberOfPara = targetPath.getbMethod().getNumberOfParameter();
			for (int i = 0; i <= numberOfPara; i++) {
				relations.addRelation(new Relation(new BinNode("i")));
			}
		}
	}

	public TestPath getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(TestPath targetPath) {
		this.targetPath = targetPath;
	}

	public Relations getRelations() {
		return relations;
	}

	public void setRelations(Relations relations) {
		this.relations = relations;
	}

	public Stack<String> getTempVariableStack() {
		return tempVariableStack;
	}

	public void setTempVariableStack(Stack<String> tempVariableStack) {
		this.tempVariableStack = tempVariableStack;
	}
}

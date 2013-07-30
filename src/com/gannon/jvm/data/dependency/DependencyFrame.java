package com.gannon.jvm.data.dependency;

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
public class DependencyFrame {
	// a given path for analyzing predicate dependency
	private TestPath targetPath;
	private Dependencies relations=new Dependencies();;
	// This stack works same as operand Stack for generating relations.
	private Stack<String> tempVariableStack = new Stack<String>();

	public DependencyFrame() {
		super();
	}

	// for triangle , assume tempVariableStack[1-3] are used for storing inputs
	// variables
	// we convert each variable into a relation
	public void initParameterRelation() {
		if (targetPath == null) {
			System.out.print("Add a target path to the relation frame before analyzing dependency!");
		} else {
			//int numberOfPara = targetPath.getbMethod().getNumberOfParameter();
			int numberOfPara = targetPath.getbMethod().getMethodParameter();
			System.out.println("numberOfPara "+numberOfPara);
			
			//0 is never used
			for (int i = 0; i <= numberOfPara; i++) {
				relations.add(new Dependency(new BinNode(Integer.toString(i))));
			}
		}
	}

	public TestPath getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(TestPath targetPath) {
		this.targetPath = targetPath;
	}

	public Dependencies getRelations() {
		return relations;
	}

	public void setRelations(Dependencies relations) {
		this.relations = relations;
	}

	public Stack<String> getTempVariableStack() {
		return tempVariableStack;
	}

	public void setTempVariableStack(Stack<String> tempVariableStack) {
		this.tempVariableStack = tempVariableStack;
	}
}

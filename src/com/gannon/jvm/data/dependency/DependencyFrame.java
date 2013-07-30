package com.gannon.jvm.data.dependency;

import java.util.Stack;

import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;
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
	private Dependencies relations = new Dependencies();;
	// intermediate variables
	private Stack<String> tempVariableStack = new Stack<String>();

	// for operand
	private Stack<Object> operandStack = new Stack<Object>();

	private BLocalVarTable localVariableTable = new BLocalVarTable();

	public DependencyFrame() {
		super();
	}

	// for triangle , assume tempVariableStack[1-3] are used for storing inputs
	// variables
	// we convert each variable into a relation
	public void initParameterRelation() {
		if (targetPath == null) {
			System.out
					.print("Add a target path to the relation frame before analyzing dependency!");
		} else {
			// int numberOfPara =
			// targetPath.getbMethod().getNumberOfParameter();
			int numberOfPara = targetPath.getbMethod().getNumberOfParameter();
			System.out.println("numberOfPara " + numberOfPara);

			// 0 is never used
			for (int i = 0; i <= numberOfPara; i++) {
				relations.add(new Dependency(new BinNode(Integer.toString(i))));
			}
		}
	}

	// for triangle , assume tempVariableStack[1-3] are used for storing inputs
	// variables
	// we convert each variable into a relation
	public void initInputs() {
		if (targetPath == null) {
			System.out.print("Add a target path to the relation frame before analyzing dependency!");
		} else {
			// if we dont't care about values
			if (localVariableTable.size() == 0) {
				int numberOfPara = targetPath.getbMethod().getNumberOfParameter();

				// 0 is never used
				for (int i = 0; i <= numberOfPara; i++) {
					localVariableTable.add(0);
				}
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

	public Stack<String> getIntermediateVariableStack() {
		return tempVariableStack;
	}

	public void setIntermediateVariableStack(Stack<String> tempVariableStack) {
		this.tempVariableStack = tempVariableStack;
	}

	public Stack<Object> getOperandStack() {
		return operandStack;
	}

	public void setOperandStack(Stack<Object> operandStack) {
		this.operandStack = operandStack;
	}

	public BLocalVarTable getLocalVariableTable() {
		return localVariableTable;
	}

	public void setLocalVariableTable(BLocalVarTable localVariableTable) {
		this.localVariableTable = localVariableTable;
	}
}

package com.gannon.jvm.execution.path;

import java.util.Stack;

import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;
import com.sun.org.apache.bcel.internal.classfile.LocalVariableTable;

public class PathFrame {
	private TestPath testPath;
	private BLocalVarTable localVariableTable;
	private Stack operandStack ;

	public PathFrame(TestPath testPath, BLocalVarTable localVariableTable) {
		super();
		this.testPath = testPath;
		this.operandStack = new Stack();
		this.localVariableTable= localVariableTable;
	}

	public TestPath getTestPath() {
		return testPath;
	}

	public void setTestPath(TestPath testPath) {
		this.testPath = testPath;
	}

	public BLocalVarTable getLocalVariableTable() {
		return localVariableTable;
	}

	public void setLocalVariableTable(BLocalVarTable localVariableTable) {
		this.localVariableTable = localVariableTable;
	}

	public Stack getOperandStack() {
		return operandStack;
	}

	public void setOperandStack(Stack operandStack) {
		this.operandStack = operandStack;
	}




}

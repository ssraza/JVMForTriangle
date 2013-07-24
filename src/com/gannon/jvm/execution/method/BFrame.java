package com.gannon.jvm.execution.method;

import java.util.Stack;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.utilities.ConstantsUtility;

public class BFrame {
	private BMethod method;
	private BLocalVarTable localVariableTable;
	private int lineNumber = ConstantsUtility.INIT_PROGRAM_LINE_NUMBER;
	private Stack operandStack = new Stack();

	public BFrame(int lineNumber, BLocalVarTable localVariableTable, Stack operandStack) {
		super();
		this.lineNumber = lineNumber;
		this.localVariableTable = localVariableTable;
		this.operandStack=operandStack;
	}


	public BFrame(BMethod method, int lineNumber, BLocalVarTable localVariableTable, Stack operandStack) {
		super();
		this.method = method;
		this.lineNumber = lineNumber;
		this.localVariableTable = localVariableTable;
		this.operandStack=operandStack;

	}

	public BMethod getMethod() {
		return method;
	}

	public void setMethod(BMethod method) {
		this.method = method;
	}

	public BLocalVarTable getVarTable() {
		return this.localVariableTable;
	}

	public void setVarTable(BLocalVarTable varTable) {
		this.localVariableTable = varTable;
	}

	public int getLineNumber() {
		return this.lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Stack getOperandStack() {
		return this.operandStack;
	}

	public void setOperandStack(Stack operandStack) {
		this.operandStack = operandStack;
	}

}

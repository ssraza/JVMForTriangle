package com.ganon.jvm.shared;

import java.util.Stack;

import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.utilities.ConstantsUtility;

//can be deleted, it used now.will be the parent of all frame later
public class Frame {
	private BClass bClass;
	private BMethod bMethod;
	private Stack operandStack = new Stack();
	private BLocalVarTable localVariableTable;

	public Frame() {
		super();
	}

	public Frame(BClass bClass, BMethod bMethod, Stack operandStack, BLocalVarTable localVariableTable) {
		super();
		this.bClass = bClass;
		this.bMethod = bMethod;
		this.operandStack = operandStack;
		this.localVariableTable = localVariableTable;
	}

	public Frame(BClass bClass, Stack operandStack, BLocalVarTable localVariableTable) {
		super();
		this.bClass = bClass;
		this.operandStack = operandStack;
		this.localVariableTable = localVariableTable;
	}

	public Frame(Stack operandStack, BLocalVarTable localVariableTable) {
		super();
		this.operandStack = operandStack;
		this.localVariableTable = localVariableTable;
	}

	public BClass getbClass() {
		return bClass;
	}

	public void setbClass(BClass bClass) {
		this.bClass = bClass;
	}

	public BMethod getbMethod() {
		return bMethod;
	}

	public void setbMethod(BMethod bMethod) {
		this.bMethod = bMethod;
	}

	public Stack getOperandStack() {
		return operandStack;
	}

	public void setOperandStack(Stack operandStack) {
		this.operandStack = operandStack;
	}

	public BLocalVarTable getLocalVariableTable() {
		return localVariableTable;
	}

	public void setLocalVariableTable(BLocalVarTable localVariableTable) {
		this.localVariableTable = localVariableTable;
	}

}

package com.gannon.jvm;

import java.util.HashMap;
import java.util.Stack;

import com.gannon.asm.components.*;
import com.gannon.jvm.instructions.BInstruction;

public class BFrame {
	private BMethod method;
	private BLocalVarTable localVariableTable;
	private int PC = 0;
	private Stack operandStack = new Stack();

	private HashMap<String, Integer> labelMap = new HashMap<String, Integer>();
	private HashMap<Integer, BInstruction> instructionMap = new HashMap<Integer, BInstruction>();

	public BFrame(int PC, BLocalVarTable localVariableTable, Stack operandStack) {
		super();
		this.PC = PC;
		this.localVariableTable = localVariableTable;
		this.operandStack=operandStack;
	}


	public BFrame(BMethod method, int PC, BLocalVarTable localVariableTable, Stack operandStack) {
		super();
		this.method = method;
		this.PC = PC;
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

	public int getPC() {
		return this.PC;
	}

	public void setPC(int pC) {
		this.PC = pC;
	}

	public Stack getOperandStack() {
		return this.operandStack;
	}

	public void setOperandStack(Stack operandStack) {
		this.operandStack = operandStack;
	}

	public HashMap<String, Integer> getLabelMap() {
		return labelMap;
	}

	public void setLabelMap(HashMap<String, Integer> labelMap) {
		this.labelMap = labelMap;
	}

	public HashMap<Integer, BInstruction> getInstructionMap() {
		return instructionMap;
	}

	public void setInstructionMap(HashMap<Integer, BInstruction> instructionMap) {
		this.instructionMap = instructionMap;
	}

}

package com.gannon.Executor.JVMExecutionObjects;

import java.util.HashMap;
import java.util.Stack;
import com.gannon.ASM.BytecodeComponent.*;
import com.gannon.Executor.Instruction.BInstruction;

public class BFrame {
	private BMethod method;
	private BLocalVarTable localVariableTable;
	private int PC = 0;
	private Stack operandStack = new Stack();

	private HashMap<String, Integer> labelMap = new HashMap<String, Integer>();
	private HashMap<Integer, BInstruction> instructionMap = new HashMap<Integer, BInstruction>();


	public BFrame(BLocalVarTable varTable, int pC, Stack operandStack) {
		super();
		this.localVariableTable = varTable;
		PC = pC;
		this.operandStack = operandStack;
	}

	public BFrame(BMethod method, int pC, BLocalVarTable localVariableTable) {
		super();
		this.method = method;
		this.PC = pC;
		this.localVariableTable = localVariableTable;
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

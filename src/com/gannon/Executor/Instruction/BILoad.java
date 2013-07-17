package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.GannonJVM.BFrame;
import com.gannon.Executor.GannonJVM.BLocalVarTable;
import com.gannon.Utility.HardBytecode;

public class BILoad extends BInstruction {
	private int operand1;

	public BILoad(int operand1) {
		super();
		this.operand1 = operand1;
	}

	public BILoad(int operand1, int lineNumber) {
		setLineNumber(lineNumber);
		this.operand1 = operand1;
	}

	public String toString() {
		return super.toString() + " "  + getOpcodeCommand()+" " + operand1;
	}

    //
	public Object execute(BFrame activeFrame) {
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Stack<Integer> operandStack = activeFrame.getOperandStack();

		Integer pc = activeFrame.getPC();
		// push local value to top of stack
		operandStack.push((Integer) myLocalVariableTable.getLocalVariable(operand1));

		//point to next instruction
		activeFrame.setPC((++pc));
		return null;
	}

	public int getOpcode() {
		return 21;
	}

	public Integer getOperand() {
		return operand1;
	}

	@Override
	public String getOpcodeCommand() {
		return "iload";
	}
}

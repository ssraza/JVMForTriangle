package com.gannon.jvm.instructions;

import java.util.HashMap;
import java.util.Stack;

import javax.swing.JOptionPane;

import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.Utility.HardBytecode;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;

import org.objectweb.asm.Label;

public class BIFicmpeq extends BInstruction {
	private Label label;

	public BIFicmpeq() {
		super();
	}

	public BIFicmpeq(Label label) {
		super();
		this.label = label;
	}

	public BIFicmpeq(Label label, int lineNumber) {
		setLineNumber(lineNumber);
		this.label = label;
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();

		Integer programCounter = activeFrame.getPC();// next instruction will
														// fetched for execution

		Integer firstValue = (Integer) myOperandStack.pop();
		Integer secondValue = (Integer) myOperandStack.pop();

		if (firstValue == secondValue) {
			BBlock b = activeFrame.getMethod().findBBlock(label);
			programCounter = b.getbLable().getLineNumber();
			activeFrame.setPC(programCounter);
		} else {
			System.out
					.println("Condition is not satisfied, first value is not equal to second value "
							+ firstValue + "  " + secondValue);
			myOperandStack.clear();
			activeFrame.setPC(++programCounter);// set next instruction to
												// executed
		}

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setVarTable(myLocalVariableTable);

		return activeFrame;
	}

	public String toString() {
		return super.toString() + " " + label.toString();
	}

	public int getOpcode() {
		return 159;
	}

	public Label getOperand() {
		return this.label;
	}

	@Override
	public String getOpcodeCommand() {
		return "if_cmpeq";
	}
}

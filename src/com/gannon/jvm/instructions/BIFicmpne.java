package com.gannon.jvm.instructions;

import java.util.HashMap;
import java.util.Stack;

import javax.swing.JOptionPane;

import com.gannon.asm.components.BBlock;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;

import org.objectweb.asm.Label;

public class BIFicmpne extends BInstruction {

	private Label label;

	public BIFicmpne() {
		super();
	}

	public BIFicmpne(Label label) {
		super();
		this.label = label;
	}

	public BIFicmpne(Label label, int lineNumber) {
		setLineNumber(lineNumber);
		this.label = label;
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Integer programCounter = activeFrame.getPC();

		Integer secondValue = (Integer) myOperandStack.pop();
		Integer firstValue = (Integer) myOperandStack.pop();


		if (!firstValue.equals(secondValue)) {
			BBlock b = activeFrame.getMethod().findBBlock(label);
			programCounter = b.getbLable().getLineNumber();

			System.out.println("line nubmer "+programCounter);
		}
		else{
			System.out.println("Condition is not satisfied, first value is equal to second value "+firstValue+"  "+secondValue);
			myOperandStack.clear();
			++programCounter;
		}

		activeFrame.setPC(programCounter);
		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setVarTable(myLocalVariableTable);
		return activeFrame;
	}

	public String toString() {
		return super.toString() + " " + label.toString();
	}

	public int getOpcode() {
		return 160;
	}

	public String getOpcodeCommand() {
		return "if_cmpne" ;
	}

	public Label getOperand() {
		return this.label;
	}

}

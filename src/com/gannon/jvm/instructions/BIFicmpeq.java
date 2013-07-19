package com.gannon.jvm.instructions;

import java.util.Stack;

import org.objectweb.asm.Label;

import com.gannon.asm.components.BBlock;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;
import com.gannon.jvm.data.dependency.RelationCollector;

public class BIFicmpeq extends BPredicateInstruction {
	private Label label;

	public BIFicmpeq(Label label, int lineNumber) {
		setLineNumber(lineNumber);
		this.label = label;
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();

		// next instruction will fetched for execution
		Integer programCounter = activeFrame.getPC();

		Integer firstValue = (Integer) myOperandStack.pop();
		Integer secondValue = (Integer) myOperandStack.pop();

		boolean predicateResult=firstValue.equals(secondValue);
		if (predicateResult) {
			BBlock b = activeFrame.getMethod().findBBlock(label);
			programCounter = b.getbLable().getLineNumber();
			activeFrame.setPC(programCounter);
		} else {
			myOperandStack.clear();
			// set next instruction to executed
			activeFrame.setPC(++programCounter);
		}

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setVarTable(myLocalVariableTable);

		return predicateResult;
	}

	public String toString() {
		return super.toString()+" "+getOperand();
	}

	public int getOpcode() {
		return 159;
	}

	public Label getOperand() {
		return this.label;
	}

	@Override
	public void analyzing(RelationCollector dependency) {
		// TODO Auto-generated method stub

	}
}

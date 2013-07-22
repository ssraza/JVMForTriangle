package com.gannon.jvm.instructions;

import java.util.Stack;

import org.objectweb.asm.Label;

import com.gannon.asm.components.BBlock;
import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.BFrame;
import com.gannon.jvm.execution.BLocalVarTable;

public class BIFicmpne extends BPredicateInstruction {
	private Label label;

	public BIFicmpne(Label label, int lineNumber) {
		super(lineNumber);
		this.label = label;
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Integer programCounter = activeFrame.getPC();

		Integer secondValue = (Integer) myOperandStack.pop();
		Integer firstValue = (Integer) myOperandStack.pop();

		boolean predicateResult = !firstValue.equals(secondValue);
		if (predicateResult) {
			BBlock b = activeFrame.getMethod().findBBlock(label);
			programCounter = b.getbLable().getLineNumber();

			System.out.println("line nubmer " + programCounter);
		} else {
			System.out
					.println("Condition is not satisfied, first value is equal to second value "
							+ firstValue + "  " + secondValue);
			myOperandStack.clear();
			++programCounter;
		}

		activeFrame.setPC(programCounter);
		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setVarTable(myLocalVariableTable);
		return predicateResult;
	}

	public String toString() {
		return super.toString() + " " + getOperand();
	}

	public int getOpcode() {
		return 160;
	}

	public Label getOperand() {
		return this.label;
	}

	@Override
	public void analyzing(RelationFrame rFrame) {
		// TODO Auto-generated method stub

	}

}

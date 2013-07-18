/**
 *
 */
package com.gannon.jvm.instructions;

import java.util.Stack;
import org.objectweb.asm.Label;

import com.gannon.asm.components.BBlock;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;

/**
 * @author Pratik
 *
 */
public class BIFicmpge extends BPredicateInstruction {
	private Label label;

	public BIFicmpge() {
		super();
	}

	public BIFicmpge(Label label) {
		super();
		this.label = label;
	}

	public BIFicmpge(Label label, int lineNumber) {
		setLineNumber(lineNumber);
		this.label = label;
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Integer pc = activeFrame.getPC();

		Integer secondValue = (Integer)myOperandStack.pop();
		Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = firstValue >= secondValue;
		if(predicateResult){
			BBlock b = activeFrame.getMethod().findBBlock(label);
			pc = b.getbLable().getLineNumber();
			activeFrame.setPC(pc);
		}
		else{
			myOperandStack.clear();
			activeFrame.setPC(++pc);
		}

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setVarTable(myLocalVariableTable);
		return predicateResult;
	}

	public String toString() {
		return super.toString()+" "+getOperand();
	}

	public int getOpcode() {
		return 162;
	}

	public Label getOperand() {
		return this.label;
	}
}

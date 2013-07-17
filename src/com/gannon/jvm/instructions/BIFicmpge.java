/**
 *
 */
package com.gannon.jvm.instructions;

import java.util.HashMap;
import java.util.Stack;
import org.objectweb.asm.Label;

import com.gannon.asm.components.BBlock;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;

/**
 * @author Pratik
 *
 */
public class BIFicmpge extends BInstruction {
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


		if(firstValue >= secondValue){
			System.out.println(firstValue+" >= " + secondValue);
			BBlock b = activeFrame.getMethod().findBBlock(label);
			pc = b.getbLable().getLineNumber();

			activeFrame.setPC(pc);
		}
		else{
			System.out.println(firstValue+" < " + secondValue);
			myOperandStack.clear();
			activeFrame.setPC(++pc);
		}

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setVarTable(myLocalVariableTable);
		return activeFrame;
	}

	public String toString() {
		return super.toString() + " " + label.toString();
	}

	public int getOpcode() {
		return 162;
	}

	public String getOpcodeCommand() {
		return "if_cmpge";
	}

	public Label getOperand() {
		return this.label;
	}
}

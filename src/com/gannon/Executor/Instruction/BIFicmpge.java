/**
 *
 */
package com.gannon.Executor.Instruction;

import java.util.HashMap;
import java.util.Stack;

import javax.swing.JOptionPane;

import org.objectweb.asm.Label;

import com.gannon.Executor.GannonJVM.BFrame;
import com.gannon.Executor.GannonJVM.BLocalVarTable;

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
		HashMap<String, Integer> labelMapping = activeFrame.getLabelMap();
		HashMap<Integer, BInstruction> instructionMapping = activeFrame.getInstructionMap();
		Integer firstValue = (Integer)myOperandStack.pop();
		Integer secondValue = (Integer)myOperandStack.pop();
		activeFrame.setPC(++pc);

		if(firstValue < secondValue){
			System.out.println("Condition satisfied first value is greter than second "+firstValue+"  "+secondValue);
			pc = labelMapping.get(this.label.toString());
			System.out.println("New program counter "+ pc);
			activeFrame.setPC(pc);
		}
		else{
			System.out.println("Condition is not satisfied, first value is not greater than second value "+firstValue+"  "+secondValue);
			sendFeedBackToUser(firstValue,secondValue, activeFrame);
			myOperandStack.clear();
		}

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setVarTable(myLocalVariableTable);
		return activeFrame;
	}

	private void sendFeedBackToUser(Integer firstValue, Integer secondValue,
			BFrame activeFrame) {

		JOptionPane.showMessageDialog(null, "Condition is not satisfied, first value ("+firstValue+") is not greater than " +
				" second value("+secondValue+") !");
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

package com.gannon.Executor.Instruction;

import java.util.HashMap;
import java.util.Stack;

import javax.swing.JOptionPane;

import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;

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
		//Integer pc = activeFrame.getPC();
		HashMap<String, Integer> labelMapping = activeFrame.getLabelMap();
		HashMap<Integer, BInstruction> instructionMapping = activeFrame.getInstructionMap();
		Integer programCounter = activeFrame.getPC();

		Integer firstValue = (Integer)myOperandStack.pop();
		Integer secondValue = (Integer)myOperandStack.pop();
		activeFrame.setPC(++programCounter);

		if(firstValue == secondValue){
			System.out.println("Condition satisfied first value is equal to second"+firstValue+"  "+secondValue);
			programCounter = labelMapping.get(this.label.toString());
			System.out.println("New program counter "+ programCounter);
			activeFrame.setPC(programCounter);
		}
		else{
			System.out.println("Condition is not satisfied, first value is not equal to second value "+firstValue+"  "+secondValue);
			sendFeedBackToUser(firstValue,secondValue, activeFrame);
			myOperandStack.clear();
		}

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setVarTable(myLocalVariableTable);

		return activeFrame;
	}



	private void sendFeedBackToUser(Integer firstValue, Integer secondValue,
			BFrame activeFrame) {

		JOptionPane.showMessageDialog(null, "Condition is not satisfied, first value ("+firstValue+") is not equal " +
				"to second value("+secondValue+") !");
	}



	public String toString() {
		return super.toString() + " " + label.toString();
	}

	public int getOpcode() {
		return 159;
	}

	public String getOpcodeCommand() {
		return "if_cmpeq" + " " + label.toString();
	}

	public Label getOperand() {
		return this.label;
	}
}

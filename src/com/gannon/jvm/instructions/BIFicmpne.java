package com.gannon.jvm.instructions;

import java.util.Stack;

import org.objectweb.asm.Label;

import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BLabel;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.BinPredicateNode;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.OpcodeUtility;

public class BIFicmpne extends BPredicateInstruction {
	public BIFicmpne(BLabel label, int lineNumber) {
		super(label, lineNumber);
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
			programCounter = getOperand().getGoToLineNumber();
			activeFrame.setPC(programCounter);
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

	@Override
	public void analyzing(DependencyFrame rFrame) {
		Stack<String> myOperandStack = rFrame.getTempVariableStack();
		BinNode rightNode= new BinNode(myOperandStack.pop());
		BinNode leftNode= new BinNode(myOperandStack.pop());
		BinPredicateNode rootNode=new BinPredicateNode(Integer.toString(OpcodeUtility.getNextID()));
		Dependency relation=new Dependency(rootNode, this);
		relation.insertToLeft(leftNode);
		relation.insertToRight(rightNode);

		myOperandStack.push(rootNode.getLocalVariableName());
		rFrame.getRelations().add(relation);
		rFrame.setTempVariableStack(myOperandStack);
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Object> myOperandStack = pathFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();

		Integer secondValue = (Integer) myOperandStack.pop();
		Integer firstValue = (Integer) myOperandStack.pop();

		boolean predicateResult = !firstValue.equals(secondValue);
		if (predicateResult) {

		} else {

		}

		pathFrame.setOperandStack(myOperandStack);
		pathFrame.setLocalVariableTable(myLocalVariableTable);
		return predicateResult;
	}

}

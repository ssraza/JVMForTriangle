package com.gannon.jvm.instructions;

import java.util.Stack;

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
		Integer programCounter = activeFrame.getLineNumber();

		Integer secondValue = (Integer) myOperandStack.pop();
		Integer firstValue = (Integer) myOperandStack.pop();

		boolean predicateResult = !firstValue.equals(secondValue);
		if (predicateResult) {
			programCounter = getOperand().getGoToLineNumber();
			activeFrame.setLineNumber(programCounter);
		} else {
			System.out
					.println("Condition is not satisfied, first value is equal to second value "
							+ firstValue + "  " + secondValue);
			myOperandStack.clear();
			++programCounter;
		}

		activeFrame.setLineNumber(programCounter);
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
		Stack<Object>  valueStack= rFrame.getValueStack();
		Integer secondValue = (Integer) valueStack.pop();
		Integer firstValue = (Integer) valueStack.pop();
		boolean predicateResult=!firstValue.equals(secondValue);
		
		Stack<String> nameStack = rFrame.getIntermediateVariableStack();
		BinNode rightNode= new BinNode(nameStack.pop(),secondValue);
		BinNode leftNode= new BinNode(nameStack.pop(),firstValue);
		BinPredicateNode rootNode=new BinPredicateNode(Integer.toString(OpcodeUtility.getNextID()));
		rootNode.setVariableValue(predicateResult); 
		Dependency relation=new Dependency(rootNode, this);
		relation.insertToLeft(leftNode);
		relation.insertToRight(rightNode);

		nameStack.push(rootNode.getVariableName());
		valueStack.push(predicateResult);
		rFrame.getRelations().add(relation);
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Object> myOperandStack = pathFrame.getOperandStack();

		Integer secondValue = (Integer) myOperandStack.pop();
		Integer firstValue = (Integer) myOperandStack.pop();

		boolean predicateResult = !firstValue.equals(secondValue);

		return predicateResult;
	}

}

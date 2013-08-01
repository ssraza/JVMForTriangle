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

public class BIFicmpeq extends BPredicateInstruction {
	public BIFicmpeq(BLabel label, int lineNumber) {
		super(label, lineNumber);
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();

		// next instruction will fetched for execution
		Integer programCounter = activeFrame.getLineNumber();

		Integer firstValue = (Integer) myOperandStack.pop();
		Integer secondValue = (Integer) myOperandStack.pop();

		boolean predicateResult=firstValue.equals(secondValue);
		if (predicateResult) {
			programCounter = getOperand().getGoToLineNumber();
			activeFrame.setLineNumber(programCounter);
		} else {
			myOperandStack.clear();
			// set next instruction to executed
			activeFrame.setLineNumber(++programCounter);
		}

		return predicateResult;
	}

	@Override
	public String toString() {
		return super.toString()+" "+getOperand();
	}

	@Override
	public int getOpcode() {
		return 159;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {
		Stack<Object>  valueStack= rFrame.getValueStack();
		Integer secondValue = (Integer) valueStack.pop();
		Integer firstValue = (Integer) valueStack.pop();
		boolean predicateResult=firstValue.equals(secondValue);

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

		Integer firstValue = (Integer) myOperandStack.pop();
		Integer secondValue = (Integer) myOperandStack.pop();

		boolean predicateResult=firstValue.equals(secondValue);

		return predicateResult;
	}

}

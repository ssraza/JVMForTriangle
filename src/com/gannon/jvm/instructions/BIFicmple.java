package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.asm.components.BLabel;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.BinPredicateNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.OpcodeUtility;

public class BIFicmple extends BPredicateInstruction {

	public BIFicmple(BLabel label, int lineNumber) {
		super(label, lineNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getLocalVariableTable();
		Integer pc = activeFrame.getLineNumber();

		Integer secondValue = (Integer)myOperandStack.pop();
		Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = firstValue <= secondValue;
		if(predicateResult){
			pc = getOperand().getGoToLineNumber();
			activeFrame.setLineNumber(pc);
		}
		else{
			myOperandStack.clear();
			activeFrame.setLineNumber(++pc);
		}

		return predicateResult;
	}
	
	@Override
	public String toString() {
		return super.toString()+" "+getOperand();
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Object> myOperandStack = pathFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();

		Integer secondValue = (Integer)myOperandStack.pop();
		Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = firstValue <= secondValue;

		return predicateResult;
	}

	@Override
	public int getOpcode() {
		// TODO Auto-generated method stub
		return 164;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {

		Stack<Object>  valueStack= rFrame.getOperandStack();
		Integer secondValue = (Integer) valueStack.pop();
		Integer firstValue = (Integer) valueStack.pop();
		boolean predicateResult=(firstValue <= secondValue);
		valueStack.push(predicateResult);

		Stack<String> nameStack = rFrame.getIntermediateVariableNameStack();
		BinNode rightNode= new BinNode(nameStack.pop(),secondValue);
		BinNode leftNode= new BinNode(nameStack.pop(),firstValue);
		BinPredicateNode rootNode=new BinPredicateNode(Integer.toString(OpcodeUtility.getNextID()));
		rootNode.setVariableValue(predicateResult);
		nameStack.push(rootNode.getVariableName());	
		
		Dependency relation=new Dependency(rootNode, this);
		relation.insertToLeft(leftNode);
		relation.insertToRight(rightNode);

		Dependencies relations=rFrame.getRelations();
		relations.expendTheRelations(relation);
		relations.add(relation);	
	}

	
}

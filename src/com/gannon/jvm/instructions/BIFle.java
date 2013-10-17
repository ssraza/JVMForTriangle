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

public class BIFle extends BPredicateInstruction {

	public BIFle(BLabel operand, int lineNumber) {
		super(operand, lineNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getLocalVariableTable();
		Integer pc = activeFrame.getLineNumber();

		Integer value = (Integer)myOperandStack.pop();
		//Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = value <= 0;
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

		Integer value = (Integer)myOperandStack.pop();
		//Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = value <= 0;

		return predicateResult;
	}

	@Override
	public int getOpcode() {
		// TODO Auto-generated method stub
		return 158;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {

		Stack<Object>  valueStack= rFrame.getOperandStack();
		Integer value = (Integer) valueStack.pop();
		boolean predicateResult=(value <= 0);
		valueStack.push(predicateResult);

		Stack<String> nameStack = rFrame.getIntermediateVariableNameStack();
		BinNode rightNode= new BinNode(nameStack.pop(),0);
		BinNode leftNode= new BinNode(nameStack.pop(),value);
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

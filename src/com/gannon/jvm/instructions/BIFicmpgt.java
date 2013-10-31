/**
 *
 */
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

/**
 * @author Pratik
 *
 */
public class BIFicmpgt extends BPredicateInstruction {
	public BIFicmpgt(BLabel label, int lineNumber) {
		super(label, lineNumber);
	}

	@Override
	public Object execute(BFrame activeFrame) {
		//System.out.println("execute activeframe");
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getLocalVariableTable();
		Integer pc = activeFrame.getLineNumber();

		Integer secondValue = (Integer)myOperandStack.pop();
		Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = firstValue > secondValue;
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
		//System.out.println("tostring");
		return super.toString() + " " + getOperand();
	}

	@Override
	public int getOpcode() {
		return 163;
	}

	//
	@Override
	public void analyzing(DependencyFrame rFrame) {
		//System.out.println("analyze dependencyframe");
		Stack<Object>  valueStack= rFrame.getOperandStack();
		Integer secondValue = (Integer) valueStack.pop();
		Integer firstValue = (Integer) valueStack.pop();
		boolean predicateResult=(firstValue > secondValue);
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

	@Override
	public Object execute(PathFrame pathFrame) {
		//System.out.println("execute pathframe");
		Stack<Object> myOperandStack = pathFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();

		Integer secondValue = (Integer)myOperandStack.pop();
		Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = firstValue > secondValue;

		return predicateResult;
	}
}

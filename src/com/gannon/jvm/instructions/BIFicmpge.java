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
public class BIFicmpge extends BPredicateInstruction {
	public BIFicmpge(BLabel label, int lineNumber) {
		super(label, lineNumber);
	}

	@Override
	public Object execute(BFrame activeFrame) {
		Stack<Object> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Integer pc = activeFrame.getLineNumber();

		Integer secondValue = (Integer)myOperandStack.pop();
		Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = firstValue >= secondValue;
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
	public int getOpcode() {
		return 162;
	}

	//
	@Override
	public void analyzing(DependencyFrame rFrame) {
		//test if left>=right, bottom value> =up value
		Stack<Object>  valueStack= rFrame.getValueStack();
		Integer secondValue = (Integer) valueStack.pop();
		Integer firstValue = (Integer) valueStack.pop();
		boolean predicateResult=(firstValue >= secondValue);

		Stack<String> myOperandStack = rFrame.getIntermediateVariableStack();
		BinNode rightNode= new BinNode(myOperandStack.pop(),secondValue);
		BinNode leftNode= new BinNode(myOperandStack.pop(),firstValue);
		BinPredicateNode rootNode=new BinPredicateNode(Integer.toString(OpcodeUtility.getNextID()));
		Dependency relation=new Dependency(rootNode, this);
		rootNode.setVariableValue(predicateResult);
		relation.insertToLeft(leftNode);
		relation.insertToRight(rightNode);

		//Dr. Xu: expend the relation to a complete VDT
		Dependencies relations=rFrame.getRelations();
		relations.expendTheRelations(relation);
		//after expending, add the VDT to the relations
		relations.add(relation);

		myOperandStack.push(rootNode.getVariableName());
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		Stack<Object> myOperandStack = pathFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = pathFrame.getLocalVariableTable();

		Integer secondValue = (Integer)myOperandStack.pop();
		Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = firstValue >= secondValue;

		return predicateResult;
	}
}

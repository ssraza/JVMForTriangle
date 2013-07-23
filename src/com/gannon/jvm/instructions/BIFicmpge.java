/**
 *
 */
package com.gannon.jvm.instructions;

import java.util.Stack;

import org.objectweb.asm.Label;

import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BLabel;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.BinPredicateNode;
import com.gannon.jvm.data.dependency.Relation;
import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.Utility;

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
		Integer pc = activeFrame.getPC();

		Integer secondValue = (Integer)myOperandStack.pop();
		Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = firstValue >= secondValue;
		if(predicateResult){
			pc = getOperand().getGoToLineNumber();
			activeFrame.setPC(pc);
		}
		else{
			myOperandStack.clear();
			activeFrame.setPC(++pc);
		}

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setVarTable(myLocalVariableTable);
		return predicateResult;
	}

	public String toString() {
		return super.toString()+" "+getOperand();
	}

	public int getOpcode() {
		return 162;
	}

	@Override
	public void analyzing(RelationFrame rFrame) {
		Stack<String> myOperandStack = rFrame.getTempVariableStack(); 
		BinNode rightNode= new BinNode(myOperandStack.pop());
		BinNode leftNode= new BinNode(myOperandStack.pop());
		BinPredicateNode rootNode=new BinPredicateNode(Integer.toString(Utility.getNextID()));
		Relation relation=new Relation(rootNode, this);
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
		
		Integer secondValue = (Integer)myOperandStack.pop();
		Integer firstValue = (Integer)myOperandStack.pop();

		boolean predicateResult = firstValue >= secondValue;
		if(predicateResult){
			
		}
		else{
			
		}

		pathFrame.setOperandStack(myOperandStack);
		pathFrame.setLocalVariableTable(myLocalVariableTable);
		return predicateResult;
	}
}

/**
 *
 */
package com.gannon.jvm.instructions;

import java.util.Stack;

import org.objectweb.asm.Label;

import com.gannon.asm.components.BBlock;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Relation;
import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.BFrame;
import com.gannon.jvm.execution.BLocalVarTable;
import com.gannon.jvm.utilities.Utility;

/**
 * @author Pratik
 *
 */
public class BIFicmpge extends BPredicateInstruction {
	private Label label;

	public BIFicmpge(Label label, int lineNumber) {
		super(lineNumber);
		this.label = label;
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
			BBlock b = activeFrame.getMethod().findBBlock(label);
			pc = b.getbLable().getLineNumber();
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

	public Label getOperand() {
		return this.label;
	}

	@Override
	public void analyzing(RelationFrame rFrame) {
		Stack<String> myOperandStack =rFrame.getTempVariableStack(); 
		BinNode rightNode= new BinNode(myOperandStack.pop());
		BinNode leftNode= new BinNode(myOperandStack.pop());
		BinNode rootNode=new BinNode(Integer.toString(Utility.getNextID()));
		Relation relation=new Relation(rootNode, this);
		relation.insertToLeft(leftNode); 
		relation.insertToRight(rightNode);

		myOperandStack.push(rootNode.getId());
		rFrame.getRelations().add(relation);
		rFrame.setTempVariableStack(myOperandStack);

	}
}

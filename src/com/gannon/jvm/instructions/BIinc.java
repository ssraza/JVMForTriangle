package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.BinPredicateNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.OpcodeUtility;

public class BIinc extends BInstruction{
	
	private int inc;
	
	public BIinc(int lineNumber, int inc) {
		super(lineNumber);
		this.inc = inc;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(BFrame activeFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object execute(PathFrame pathFrame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOpcode() {
		// TODO Auto-generated method stub
		return 132;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {
		
		System.out.println("IINC Analyzing");

		Stack<Object>  valueStack= rFrame.getOperandStack();
		Integer value = (Integer) valueStack.pop();
		Integer sum=value + inc;
		valueStack.push(sum);

		Stack<String> nameStack = rFrame.getIntermediateVariableNameStack();
		BinNode rightNode= new BinNode(nameStack.pop(),inc);
		BinNode leftNode= new BinNode(nameStack.pop(),value);
		BinPredicateNode rootNode=new BinPredicateNode(Integer.toString(OpcodeUtility.getNextID()));
		rootNode.setVariableValue(sum); 
		nameStack.push(rootNode.getVariableName());
		
		Dependency relation=new Dependency(rootNode, this);
		relation.insertToLeft(leftNode);
		relation.insertToRight(rightNode);

		Dependencies relations=rFrame.getRelations();
		relations.expendTheRelations(relation);
		relations.add(relation);
		
	}

}

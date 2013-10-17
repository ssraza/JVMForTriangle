package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.utilities.OpcodeUtility;

public class BIdiv extends BInstruction {
	
	public BIdiv(int lineNumber) {
		super(lineNumber);
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
		return 108;
	}

	@Override
	public void analyzing(DependencyFrame rFrame) {

		Stack<Object>  valueStack= rFrame.getOperandStack();
		Integer secondValue = (Integer) valueStack.pop();
		Integer firstValue = (Integer) valueStack.pop();
		Integer div = firstValue/secondValue;
		valueStack.push(div);
		
		Stack<String> nameStack =rFrame.getIntermediateVariableNameStack();
		BinNode rightNode= new BinNode(nameStack.pop(),secondValue);
		BinNode leftNode= new BinNode(nameStack.pop(),firstValue);
		BinNode rootNode=new BinNode(Integer.toString(OpcodeUtility.getNextID()), div);
		nameStack.push(rootNode.getVariableName());

		Dependency relation=new Dependency(rootNode, this);		
		relation.insertToLeft(leftNode);
		relation.insertToRight(rightNode);
		
		Dependencies relations=rFrame.getRelations();
		relations.expendTheRelations(relation);
		relations.add(relation);
	}

}

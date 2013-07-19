package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;
import com.gannon.jvm.data.dependency.RelationCollector;

public class BIConst_5 extends BInstruction {

	public BIConst_5(int lineNumber) {
		setLineNumber(lineNumber);
    }

    public Object execute(BFrame activeFrame) {
        Stack<Integer> myOperandStack = activeFrame.getOperandStack();

        Integer pc = activeFrame.getPC();
        myOperandStack.push(5);

		activeFrame.setOperandStack(myOperandStack);
		activeFrame.setPC(++pc);
        return null;
    }

    public int getOpcode() {
        return 8;
    }

    public Integer getOperand(){
		return 5;
	}

	@Override
	public void analyzing(RelationCollector dependency) {
		Stack<String> myOperandStack = dependency.getTempVariableStack();
		myOperandStack.add(new Integer(getOperand()).toString());
		dependency.setTempVariableStack(myOperandStack);
	}

}

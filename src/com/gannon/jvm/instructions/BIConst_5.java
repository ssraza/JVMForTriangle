package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.data.dependency.RelationFrame;
import com.gannon.jvm.execution.BFrame;

public class BIConst_5 extends BInstruction {

	public BIConst_5(int lineNumber) {
		super(lineNumber);
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
	public void analyzing(RelationFrame fFrame) {
		Stack<String> myOperandStack = fFrame.getTempVariableStack();
		myOperandStack.add(new Integer(getOperand()).toString());
		fFrame.setTempVariableStack(myOperandStack);
	}

}

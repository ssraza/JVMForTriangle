package com.gannon.jvm.instructions;

import java.util.Stack;

import com.gannon.jvm.BFrame;

public class BIConst_5 extends BInstruction {

    public BIConst_5() {
		super();
	}

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

}

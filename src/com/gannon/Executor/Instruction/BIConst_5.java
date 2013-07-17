package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.GannonJVM.BFrame;

public class BIConst_5 extends BInstruction {

    public BIConst_5() {
		super();
	}

	public BIConst_5(int lineNumber) {
		setLineNumber(lineNumber);
    }

    public String toString() {
        return super.toString();
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

    public String getOpcodeCommand() {
        return "iconst_5";
    }

    public Integer getOperand(){
		return 5;
	}

}

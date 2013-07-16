package com.gannon.Executor.Instruction;

import java.util.Stack;

import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;
import com.gannon.Executor.JVMExecutionObjects.JVMStackSingleton;
import com.gannon.Main.Main;

public class BIReturn extends BInstruction {
	public BIReturn() {
		super();
	}

	public BIReturn(int lineNumber) {
		setLineNumber(lineNumber);
	}

	public String toString() {
		return super.toString();
	}

	// public Integer execute(BFrame activeFrame, BJVMStack jStack) {
	// jStack.pop();
	// BFrame buttonFrame=jStack.peek();
	// buttonFrame.getOperandStack().push(activeFrame.getOperandStack().pop());
	//
	// }

	public Object execute(BFrame activeFrame) {
		Stack<Integer> myOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myLocalVariableTable = activeFrame.getVarTable();
		Integer pc = activeFrame.getPC();
		activeFrame.setPC(++pc);
		// myLocalVariableTable.clear();
		Main.setReturnType("integer");

		JVMStackSingleton.getInstance().removeMethodFrame();
		if (JVMStackSingleton.getInstance().size() != 0)
			JVMStackSingleton.getInstance().getActiveBFrame().getOperandStack()
					.push((Integer) myOperandStack.lastElement());

		System.out.println("JVMSingleton size: "+ JVMStackSingleton.getInstance().getJavaStackSize());
		System.out.println("Return value frm iReturn "+ myOperandStack.lastElement());
		return (Integer) myOperandStack.pop();

	}

	public int getOpcode() {
		return 172;
	}

	public String getOpcodeCommand() {
		return "ireturn";
	}

	public String getOperand() {
		return "-1";
	}

}

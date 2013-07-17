package com.gannon.jvm.instructions;

import java.util.ArrayList;
import java.util.Stack;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;
import com.gannon.jvm.JVMStackSingleton;

public class BInvokeVirtual extends BInstruction {

	private String owner;
	private String name;
	private String desc;

	public BInvokeVirtual(String owner, String name, String desc) {
		super();
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public BInvokeVirtual(String owner, String name, String desc, int lineNumber) {
		setLineNumber(lineNumber);
		this.owner = owner;
		this.name = name;
		this.desc = desc;
	}

	public Integer execute(BFrame activeFrame) {
		Stack<Object> myCurrentOperandStack = activeFrame.getOperandStack();
		BLocalVarTable myCurrentLocalVariableTable = activeFrame.getVarTable();
		Integer pc = activeFrame.getPC();
		activeFrame.setPC(++pc);// increment pc before invoking virtual, so as
								// to avoid re-entering this instruction

		BClass bClass = JVMStackSingleton.getInstance().getbClass();

		BMethod nextMethod = getNextMethod(owner, name, bClass);

		System.out.println("nextMethod name " + nextMethod.getName());

		BLocalVarTable newActiveFrameVariableTable = copyCallerOpstackToCalleeLocal(myCurrentOperandStack);

		BFrame newFrame = new BFrame(nextMethod, 0, newActiveFrameVariableTable);

		System.out.println("newFrame method name "
				+ newFrame.getMethod().getName());

		JVMStackSingleton.getInstance().addMethodFrame(newFrame);

		return null;// return a 1 letting the main know to execute next method

	}

	// Caller save parameters in opstack
	// the function copies values in opstack to callee's local variable table
	// local variable table is an arrayList
	//
	// | |
	// | objectRef |
	// |-----| --------------------
	// | arg1| | objectRef |arg1|arge2|
	// |-----| ----------------------
	// |_arg2|
	//
	// after operation, opstack is empty
	private BLocalVarTable copyCallerOpstackToCalleeLocal(
			Stack<Object> callerOperandStack) {
		Stack<Object> tempStack = new Stack<Object>();

		System.out.println("callerStack   " + callerOperandStack);

		// set the order to store arguments in local var table.
		// while (!callerOperandStack.empty()){
		// tempStack.add(callerOperandStack.pop());
		// }
		// System.out.println("tempStack "+tempStack);
		// BLocalVarTable calleeLocalTable = new BLocalVarTable();
		// // calleeLocalTable.add(0);
		// while (!tempStack.empty()){
		// calleeLocalTable.add(tempStack.pop());
		// }

		BLocalVarTable calleeLocalTable = new BLocalVarTable();
		calleeLocalTable.add(0);
		while (!callerOperandStack.empty()) {
			calleeLocalTable.add(callerOperandStack.pop());
		}
		System.out.println("final Stack  " + calleeLocalTable.getLocalVar());
		return calleeLocalTable;
	}

	public BMethod getNextMethod(String Owner, String name, BClass bClass) {

		ArrayList<BMethod> methodList = bClass.getMethods();
		Integer nextMethodIndex = bClass.getIndexOf(this.name);

		BMethod nextMethod = methodList.get(nextMethodIndex);
		System.out.println("Next called Method is " + nextMethod.getName());
		return nextMethod;
	}

	public int getOpcode() {
		return 182;
	}

	public String getOpcodeCommand() {
		return "invokevirtual" + " " + owner + " " + name + " " + desc;
	}

	public String toString() {
		return super.toString() + " " + owner + " " + name + " " + desc;
	}

	public String getOperand() {
		return name;
	}

}
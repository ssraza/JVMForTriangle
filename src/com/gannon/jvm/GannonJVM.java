package com.gannon.jvm;

import java.util.ArrayList;
import java.util.Stack;

import com.gannon.asm.components.BMethod;
import com.gannon.treeStructure.Path;

public class GannonJVM {
	private JVMStackSingleton jvmStack;
	private MethodExecutor executor;
	private Path executedPath;

	public GannonJVM(JVMStackSingleton jvmStack, MethodExecutor executor) {
		super();
		this.jvmStack = jvmStack;
		this.executor = executor;
	}

	public Object run(BMethod method, ArrayList<Object> inputs) {
		// be sure to push the active frame to stack
		preExecution(method,inputs);
		Object result=executor.execute(jvmStack);
		executedPath= collectingExecutedPath(inputs);
		return result;
	}

	public JVMStackSingleton getJvmStack() {
		return jvmStack;
	}

	public void setJvmStack(JVMStackSingleton jvmStack) {
		this.jvmStack = jvmStack;
	}

	public MethodExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(MethodExecutor executor) {
		this.executor = executor;
	}

	//before executing push active frame to stack
	private void preExecution(BMethod method,ArrayList<Object> inputs) {
		//crate active frame
		BLocalVarTable localVariableTable=new BLocalVarTable(inputs);
		int PC = 0;
		Stack operandStack = new Stack();
		BFrame activeFrame = new BFrame(method, PC, localVariableTable, operandStack);
		//push the frame to JVM stack
		jvmStack.pushFrame(activeFrame);
	}

	private Path collectingExecutedPath(ArrayList<Object> inputs) {
		Path executedPath = executor.getExecutedPath();
		executedPath.setInputs(inputs);
		return executedPath;
	}

	private Path getExecutedPath() {
		return executedPath;
	}

}

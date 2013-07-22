package com.gannon.jvm.execution.method;

import java.util.ArrayList;
import java.util.Stack;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.progam.path.TestPath;


public class GannonJVM {
	private JVMStackSingleton jvmStack;
	private MethodExecutor executor;
	private TestPath executedPath;

	public GannonJVM() {
		super();
		this.jvmStack = JVMStackSingleton.getInstance();
		this.jvmStack.clear();
		this.executor = new MethodExecutor();
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

	private TestPath collectingExecutedPath(ArrayList<Object> inputs) {
		TestPath executedPath = executor.getExecutedPath();
		executedPath.setInputs(inputs);
		return executedPath;
	}

	private TestPath getExecutedPath() {
		return executedPath;
	}

}

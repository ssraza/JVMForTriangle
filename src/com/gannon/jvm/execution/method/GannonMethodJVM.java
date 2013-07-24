package com.gannon.jvm.execution.method;

import java.util.ArrayList;
import java.util.Stack;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.ConstantsUtility;

public class GannonMethodJVM {
	private JVMStackSingleton jvmStack;
	private MethodExecutor executor;
	private TestPath executedPath;

	public GannonMethodJVM() {
		super();
		this.jvmStack = JVMStackSingleton.getInstance();
		this.jvmStack.clear();
		this.executor = new MethodExecutor();
	}

	public Object run(BMethod method, ArrayList<Object> inputs) {
		// be sure to push the active frame to stack
		preExecution(method, inputs);
		Object result = executor.execute(jvmStack);
		executedPath = collectingExecutedPath(inputs);
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

	// before executing push active frame to stack
	private void preExecution(BMethod method, ArrayList<Object> inputs) {
		// crate active frame
		BLocalVarTable localVariableTable = new BLocalVarTable(inputs);
		int lineNumber = ConstantsUtility.INIT_PROGRAM_LINE_NUMBER;
		// the first instruction is 1
		Stack operandStack = new Stack();
		BFrame activeFrame = new BFrame(method, lineNumber, localVariableTable, operandStack);
		// push the frame to JVM stack
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

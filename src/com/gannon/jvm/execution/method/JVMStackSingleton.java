package com.gannon.jvm.execution.method;

import java.util.Stack;

public class JVMStackSingleton {
	private Stack<BFrame> javaStack = new Stack<BFrame>();

	private static JVMStackSingleton singletonJVMStack;

	public static JVMStackSingleton getInstance() {
		if (singletonJVMStack == null) {
			singletonJVMStack = new JVMStackSingleton();
		}
		return singletonJVMStack;
	}

	public BFrame peekActiveFrame() {
		return javaStack.peek();
	}

	public BFrame popActivekFrame() {
		return javaStack.pop();
	}

	public int size() {
		return javaStack.size();
	}

	public void pushFrame(BFrame mFrame) {
		javaStack.push(mFrame);
	}

	public void clear(){
		if(javaStack!=null){
			javaStack.clear();
		}
	}
}

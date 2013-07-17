package com.gannon.Executor.GannonJVM;

import java.util.Stack;

public class JVMStackSingleton {
	private Stack<BFrame> javaStack = new Stack<BFrame>();

	private static JVMStackSingleton singletonJVMStack;

	public JVMStackSingleton() {

	}

	public static JVMStackSingleton getInstance() {
		if (singletonJVMStack == null) {
			singletonJVMStack = new JVMStackSingleton();
		}
		return singletonJVMStack;
	}

	public BFrame peekActivekFrame() {
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
}

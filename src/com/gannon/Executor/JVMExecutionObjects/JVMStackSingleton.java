package com.gannon.Executor.JVMExecutionObjects;

import java.util.Stack;

public class JVMStackSingleton {

	private Stack<BFrame> frameList = new Stack<BFrame>();
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

	public void Erase() {
		frameList = new Stack<BFrame>();
		javaStack = new Stack<BFrame>();
	}

	public Stack<BFrame> getJavaStack() {
		return javaStack;
	}

	public int getFrameListSize() {
		return frameList.size();
	}

	public int getJavaStackSize() {
		return javaStack.size();
	}

	public void setJavaStack(Stack<BFrame> javaStack) {
		this.javaStack = javaStack;
	}

	/**
	 * 
	 * @return
	 */
	public BFrame getActiveBFrame() {

		return frameList.lastElement();
	}

	public void createMethodStack(BFrame mframe) {
		frameList.push(mframe);
	}

	public void addMethodFrame(BFrame mFrame) {
		frameList.add(mFrame);
	}

	public void removeMethodFrame() {
		frameList.pop();// remove last element
	}

	public void setElementAt(int index, BFrame mFrame) {
		frameList.set(index, mFrame);
	}

	public Integer size() {
		return frameList.size();
	}

}

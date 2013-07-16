package com.gannon.ASM.BytecodeComponent;

import org.objectweb.asm.Label;
/**
 * This class is a part of data structure created to organize the information
 * about the class being visited.
 * This Class will have the line information of instructions.
 * 
 * 
 * @param lineNumber    lineNumber in class file for the instruction
 * @param startLable
 * 
 * **/
public class BLineNumber {
	private int pc;
	private Label startLable;
	private int lineNumber;
	
	public BLineNumber() {
		super();
	}

	public BLineNumber(int lineNumber, Label startLable) {
		super();
		this.startLable = startLable;
		this.lineNumber = lineNumber;
	}
	
	public Label getStartLable() {
		return startLable;
	}

	public void setStartLable(Label startLable) {
		this.startLable = startLable;
	}

	public BLineNumber(int pc, int lineNumber) {
		super();
		this.pc = pc;
		this.lineNumber = lineNumber;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	/**Display line numbers and start labels**/
	public void display(){
		System.out.println("Line "+lineNumber + ": " +startLable);
	}

	
}

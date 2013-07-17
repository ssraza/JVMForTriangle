package com.gannon.ASM.BytecodeComponent;

import org.objectweb.asm.Label;

// e.g, L0, L1
public class BLabel {
	private Label lb;// L234343
	private char ch;
	private int ID;
	private int lineNumber;

	public BLabel(Label lb, int ID) {
		super();
		this.lb = lb;
		this.ch = 'L';
		this.ID = ID;
		this.lineNumber=0;
	}

	public Label getLabel() {
		return lb;
	}

	public char getCh() {
		return ch;
	}

	public int getID() {
		return ID;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String toString() {
		StringBuilder s=new StringBuilder();
		s.append("================");
		s.append("" + ch + ID + "("+lb.toString()+ ") Jump Line Number "+ lineNumber+" \n");
		return s.toString();
	}
}

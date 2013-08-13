package com.gannon.gui;

import java.util.Set;

import javax.swing.JTextArea;

import com.gannon.jvm.data.input.Input;
import com.gannon.jvm.utilities.Utility;

public class GeneratedInputsArea extends JTextArea {

	public GeneratedInputsArea() {
		super();
	}

	public GeneratedInputsArea(Set<Input> inputs) {
		this.setText("");
		this.append(Utility.toNiceString(inputs));
	}

}

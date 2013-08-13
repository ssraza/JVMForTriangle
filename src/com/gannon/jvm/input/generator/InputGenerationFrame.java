package com.gannon.jvm.input.generator;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.progam.path.TestPath;

public class InputGenerationFrame extends PathFrame{
	private int numberOfResultsNeeded=0;

	public InputGenerationFrame(TestPath testPath, BLocalVarTable localVariableTable, int numberOfResultsNeeded) {
		super(testPath, localVariableTable);
		this.numberOfResultsNeeded=numberOfResultsNeeded;
	}

	public int getNumberOfResultsNeeded() {
		return numberOfResultsNeeded;
	}

	public void setNumberOfResultsNeeded(int numberOfResultsNeeded) {
		this.numberOfResultsNeeded = numberOfResultsNeeded;
	}

}

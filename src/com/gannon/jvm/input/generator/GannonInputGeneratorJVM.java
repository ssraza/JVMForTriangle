package com.gannon.jvm.input.generator;

import java.util.ArrayList;
import java.util.Set;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.input.Input;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.path.PathExecutor;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.progam.path.TestPath;

public class GannonInputGeneratorJVM {
	private InputGenerationFrame inputGenerationFrame;
	private InputGenerateExecutor executor;

	public GannonInputGeneratorJVM() {
		super();
	}

	private void init(TestPath targetPath, Input input, int numberOfResultsNeeded) {
		// crate active frame
		BLocalVarTable localVariableTable = new BLocalVarTable(input);
		inputGenerationFrame = new InputGenerationFrame(targetPath, localVariableTable, numberOfResultsNeeded);
		// init pathExecutor
		executor = new InputGenerateExecutor(input);

	}

	public Set<Input> run(TestPath targetPath, Input input, int numberOfResultsNeeded) {
		init(targetPath, input, numberOfResultsNeeded);
		Set<Input> result = executor.execute(inputGenerationFrame);
		return result;
	}

}

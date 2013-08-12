package com.gannon.jvm.input.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.GannonPredicateTreeBuilderJVM;
import com.gannon.jvm.data.input.Input;
import com.gannon.jvm.data.input.InputCollection;
import com.gannon.jvm.data.input.InputGenerationFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.method.GannonMethodJVM;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.instructions.BIFicmpeq;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BIFicmpne;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.ConstantsUtility;
import com.gannon.jvm.utilities.Utility;
import com.gannon.rule.Rule;
import com.gannon.rule.RuleIFcmpeq;
import com.gannon.rule.RuleIFcmpge;
import com.gannon.rule.RuleIFcmpne;

public class InputGenerateExecutor<T> {


	private Input input;

	public InputGenerateExecutor(Input input) {
		super();
		this.input = input;
	}

	public Set<Input> execute(InputGenerationFrame inputGenerationFrame) {
		//inputs pass all IF statements
		Queue<Input> potentialGoodReusltQueue = new LinkedList<Input>();
		//the number of generated inputs
		int generatedCounter = 0;

		// store final generated input for a given path
		Set<Input> results = new HashSet<Input>();

		TestPath path = inputGenerationFrame.getTestPath();
		// random generate input

		potentialGoodReusltQueue.add(this.input);

		while (!potentialGoodReusltQueue.isEmpty()) {
			// clear all ignore flag
			path.clearIgnoreFlags();

			// add the new input from queue to Path Frame
			input = (Input) potentialGoodReusltQueue.poll();
			BLocalVarTable vt = new BLocalVarTable();
			ArrayList<Object> vars = input.getOldInput();
			inputGenerationFrame.setLocalVariableTable(new BLocalVarTable(vars));

			// get dependency tree
			GannonPredicateTreeBuilderJVM pathjvm = new GannonPredicateTreeBuilderJVM();
			pathjvm.run(path, vars);
			Dependencies dps = pathjvm.getRelationFrame().getRelations();

			boolean endOfPathFlag = false;

			while (!endOfPathFlag) {
				// for each predicate node, i.e., the node contains a predicate
				// instruction (a>b), if the run-time predicate result does not
				// equal to the expected results, the program re-executes the
				// program, however, the predicate will be skipped to avoid
				// localized search

				for (Node node : path.getNodes()) {
					Object result = node.getInstruction().execute(inputGenerationFrame);
					// if reaching the end of the program, we have a potential
					// good input, the program stops and push the input to a
					// "potential good" input queue
					if (node.hasReturnInstruction()) {
						endOfPathFlag = true;
						if (potentialGoodReusltQueue.isEmpty()) {
							potentialGoodReusltQueue.add(Input.generateRandom(0, 3));
						}
						break;
					} else if (node.isBPredicateNode() && !((PredicateNode) node).isIgnore()) {
						((PredicateNode) node)
								.setActualPredicateResult((Boolean) result.equals(true) ? ConstantsUtility.EXPECTED_TRUE
										: ConstantsUtility.EXPECTED_FALSE);
						// System.out.println("If instruction" + node);
						// if the actual predicate result is not equal to
						// expected results , we need to generate a new value to
						// pass the node and the predicate as ignored for next
						// time execution
						if (!((PredicateNode) node).hasPassed() && ((PredicateNode) node).isIgnore() != true) {
							InputCollection newGeneratedInputs = adjustInput(dps, node);

							// put new generated results to queue
							potentialGoodReusltQueue.addAll(newGeneratedInputs.getInputs());

							// force to ignore this IF statement during next
							// execution
							((PredicateNode) node).setIgnore(true);
							break;
						}
					}
				}
			}

			// execute the methodJVM to see if get the same path, if so save the
			// result
			GannonMethodJVM jvm = new GannonMethodJVM();
			jvm.run(inputGenerationFrame.getTestPath().getbClass(), inputGenerationFrame.getTestPath().getbMethod(),
					vars);
			jvm.getExecutedPath();
			TestPath resultPath = jvm.getExecutedPath();

			//check if we have enough test inputs
			if (resultPath.equals(path)) {
				if (generatedCounter < inputGenerationFrame.getNumberOfResultsNeeded()) {
					results.add(input);
					System.out.println(input);
					generatedCounter++;
				}else{
					break;
				}
			}

		}
		return results;
	}

	private InputCollection adjustInput(Dependencies dps, Node node) {
		// Apply rules to dependency to change input values. The root of the
		// dependency is BinNode. We need to find the BinNode from the node
		// comes from test path.
		BInstruction instruction = node.getInstruction();
		Dependency dp = dps.findRelation(instruction);

		BinNode leftNode = dp.getLeftNode();
		BinNode rightNode = dp.getRightNode();

		boolean expectedPredicateResult = ((PredicateNode) node).getExpectedPredicateResult() > 0 ? true : false;

		// a collection to collect new generated data
		InputCollection newDataList = new InputCollection(1);
		Rule rule = null;

		if (instruction instanceof BIFicmpge) {
			rule = new RuleIFcmpge(expectedPredicateResult, input, dps, leftNode, rightNode, newDataList);
		} else if (instruction instanceof BIFicmpne) {
			rule = new RuleIFcmpne(expectedPredicateResult, input, dps, leftNode, rightNode, newDataList);
		} else if (instruction instanceof BIFicmpeq) {
			rule = new RuleIFcmpeq(expectedPredicateResult, input, dps, leftNode, rightNode, newDataList);
		}
		rule.dataGeneration();
		return newDataList;
	}

}

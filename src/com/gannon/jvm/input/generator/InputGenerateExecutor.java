package com.gannon.jvm.input.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.GannonPredicateTreeBuilderJVM;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.method.GannonMethodJVM;
import com.gannon.jvm.execution.path.PathFrame;
import com.gannon.jvm.input.Input;
import com.gannon.jvm.input.InputCollection;
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
	private Queue<Input> potentialGoodReusltQueue = new LinkedList<Input>();

	public ArrayList<Object> aInput;
	private Input input;

	public InputGenerateExecutor(ArrayList<Object> aInput) {
		super();
		this.aInput = aInput;
		this.input = new Input(1, aInput);
	}

	public InputGenerateExecutor(Input input) {
		super();
		this.input = input;
	}

	public Object execute(PathFrame pathFrame) {
		Object result = null;
		TestPath path = pathFrame.getTestPath();
		int count = 0;
		// random generate input

		potentialGoodReusltQueue.add(this.input);

		while (!potentialGoodReusltQueue.isEmpty()) {
			path.clearIgnoreFlags();
			// Loop Issue here, new data is not updated?
			input = (Input) potentialGoodReusltQueue.poll();

			// add the new input from queue to Path Frame
			BLocalVarTable vt = new BLocalVarTable();
			ArrayList<Object> vars = input.getOldInput();
			pathFrame.setLocalVariableTable(new BLocalVarTable(vars));

			// get dependency tree
			GannonPredicateTreeBuilderJVM pathjvm = new GannonPredicateTreeBuilderJVM();
			pathjvm.run(path, vars);
			Dependencies dps = pathjvm.getRelationFrame().getRelations();

			boolean endOfPathFlag = false;
			// clear all ignore flag
			while (!endOfPathFlag) {
				// for each predicate node, i.e., the node contains a predicate
				// instruction (a>b), if the run-time predicate result does not
				// equal to the expected results, the program re-executes the
				// program, however, the predicate will be skipped to avoid
				// localized search

				for (Node node : path.getNodes()) {
					result = node.getInstruction().execute(pathFrame);
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
						// if the actual predicate result is not equal to
						// expected results , we need to generate a new value to
						// pass the node and the predicate as ignored for next
						// time execution
						if (!((PredicateNode) node).hasPassed() && ((PredicateNode) node).isIgnore() != true) {
							InputCollection newDataList = adjustInput(dps, node);

							// put new generated results to queue
							List<Input> inputList = newDataList.getInputs();
							for (int j = 0; j < inputList.size(); j++) {
								potentialGoodReusltQueue.add(inputList.get(j));
							}

							// set ignore flag in the node so next time to skip
							// RE-Evaluate the predicate instruction
							((PredicateNode) node).setIgnore(true);
							break;
						}
					}
				}
			}

			// get Method instructions

			BClass myclass = BClassGenerator.getBClass("Triangle.class");
			BMethod m = myclass.getMethod("triangleType");

			GannonMethodJVM jvm = new GannonMethodJVM();
			jvm.run(myclass, m, vars);
			jvm.getExecutedPath();
			TestPath resultPath = jvm.getExecutedPath();
			System.out.println("Result Test Data");

			if (resultPath.equals(path) && count < 10) {
				System.out.println(count + " " + vars.get(1) + "," + vars.get(2) + "," + vars.get(3));
				count++;
				// break;
			}
			if (count == 10)
				break;

			// execute the methodJVM to see if get the same path, if so save the
			// result

		}
		return result;
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

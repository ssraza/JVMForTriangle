package com.gannon.rule;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.objectweb.asm.Label;

import com.gannon.asm.components.BLabel;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.input.Input;
import com.gannon.jvm.input.InputCollection;
import com.gannon.jvm.input.Parameter;
import com.gannon.jvm.instructions.BIFicmpeq;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.utilities.ConstantsUtility;

public class RuleIFcmpeqTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	//
	// _____100 (T)
	// ___ /   \
	// __1(3) 2 (4)
	//
	//
	@Test
	public void testEqualTrueLeftBigger() {
		// create an input
		Parameter p1 = new Parameter(1, 3);
		Parameter p2 = new Parameter(2, 4);
		Input input = new Input(1);
		input.add(p1);
		input.add(p2);

		// create first three dependency
		BinNode node1 = new BinNode("1", 0);
		Dependency i1 = new Dependency(node1);
		BinNode node2 = new BinNode("2", 0);
		Dependency i2 = new Dependency(node2);
		BinNode node3 = new BinNode("3", 0);
		Dependency i3 = new Dependency(node3);

		// fourth dependency
		BinNode root1 = new BinNode("100", 0);
		BLabel lable = new BLabel(new Label());
		Dependency i4 = new Dependency(root1, new BIFicmpeq(lable, 5));
		BinNode leftNode = new BinNode("1", 0);
		leftNode.setVariableValue(3);
		BinNode rightNode = new BinNode("2", 0);
		rightNode.setVariableValue(4);
		i4.insertToLeft(leftNode);
		i4.insertToRight(rightNode);

		// add four dependencies to Dependencies
		Dependencies dependencies = new Dependencies();
		dependencies.add(i1);
		dependencies.add(i2);
		dependencies.add(i3);
		dependencies.add(i4);

		// create input collection to collect generated inputs
		InputCollection inputs = new InputCollection(1);

		// set expected predicate result
		boolean expectedPredicateResult = true;

		// init
		RuleIFcmpeq rule = new RuleIFcmpeq(expectedPredicateResult, input, dependencies, leftNode, rightNode, inputs);
		rule.dataGeneration();
		assertEquals(4,inputs.getInputs().get(0).getParamters().get(0).getValue());
		assertEquals(3,inputs.getInputs().get(1).getParamters().get(0).getValue());
		assertEquals(inputs.getInputs().get(0).getParamters().get(0).getValue(),inputs.getInputs().get(0).getParamters().get(1).getValue());
		assertEquals(inputs.getInputs().get(1).getParamters().get(0).getValue(),inputs.getInputs().get(1).getParamters().get(1).getValue());

	}


	//
	// _____100 (T)
	// ___ /   \
	// __1(4) 2 (3)
	//
	//
	@Test
	public void testEqualTrueRightBigger() {
		// create an input
		Parameter p1 = new Parameter(1, 4);
		Parameter p2 = new Parameter(2, 3);
		Input input = new Input(1);
		input.add(p1);
		input.add(p2);

		// create first three dependency
		BinNode node1 = new BinNode("1", 0);
		Dependency i1 = new Dependency(node1);
		BinNode node2 = new BinNode("2", 0);
		Dependency i2 = new Dependency(node2);
		BinNode node3 = new BinNode("3", 0);
		Dependency i3 = new Dependency(node3);

		// fourth dependency
		BinNode root1 = new BinNode("100", 0);
		BLabel lable = new BLabel(new Label());
		Dependency i4 = new Dependency(root1, new BIFicmpeq(lable, 5));
		BinNode leftNode = new BinNode("1", 0);
		leftNode.setVariableValue(4);
		BinNode rightNode = new BinNode("2", 0);
		rightNode.setVariableValue(3);
		i4.insertToLeft(leftNode);
		i4.insertToRight(rightNode);

		// add four dependencies to Dependencies
		Dependencies dependencies = new Dependencies();
		dependencies.add(i1);
		dependencies.add(i2);
		dependencies.add(i3);
		dependencies.add(i4);

		// create input collection to collect generated inputs
		InputCollection inputs = new InputCollection(1);

		// set expected predicate result
		boolean expectedPredicateResult = true;

		// init
		RuleIFcmpeq rule = new RuleIFcmpeq(expectedPredicateResult, input, dependencies, leftNode, rightNode, inputs);
		rule.dataGeneration();
		assertEquals(3,inputs.getInputs().get(0).getParamters().get(0).getValue());
		assertEquals(4,inputs.getInputs().get(1).getParamters().get(0).getValue());
		assertEquals(inputs.getInputs().get(0).getParamters().get(0).getValue(),inputs.getInputs().get(0).getParamters().get(1).getValue());
		assertEquals(inputs.getInputs().get(1).getParamters().get(0).getValue(),inputs.getInputs().get(1).getParamters().get(1).getValue());

	}
	

	//
	// _____100 (F)
	// ___ /   \
	// __1(4) 2 (4)
	//
	//
	@Test
	public void testEqualFalse() {
		// create an input
		Parameter p1 = new Parameter(1, 4);
		Parameter p2 = new Parameter(2, 4);
		Input input = new Input(1);
		input.add(p1);
		input.add(p2);

		// create first three dependency
		BinNode node1 = new BinNode("1", 0);
		Dependency i1 = new Dependency(node1);
		BinNode node2 = new BinNode("2", 0);
		Dependency i2 = new Dependency(node2);
		BinNode node3 = new BinNode("3", 0);
		Dependency i3 = new Dependency(node3);

		// fourth dependency
		BinNode root1 = new BinNode("100", 0);
		BLabel lable = new BLabel(new Label());
		Dependency i4 = new Dependency(root1, new BIFicmpeq(lable, 5));
		BinNode leftNode = new BinNode("1", 0);
		leftNode.setVariableValue(4);
		BinNode rightNode = new BinNode("2", 0);
		rightNode.setVariableValue(4);
		i4.insertToLeft(leftNode);
		i4.insertToRight(rightNode);

		// add four dependencies to Dependencies
		Dependencies dependencies = new Dependencies();
		dependencies.add(i1);
		dependencies.add(i2);
		dependencies.add(i3);
		dependencies.add(i4);

		// create input collection to collect generated inputs
		InputCollection inputs = new InputCollection(1);

		// set expected predicate result
		boolean expectedPredicateResult = false;

		// init
		RuleIFcmpeq rule = new RuleIFcmpeq(expectedPredicateResult, input, dependencies, leftNode, rightNode, inputs);
		rule.dataGeneration();
		System.out.println(inputs);
		assertTrue(inputs.getInputs().get(0).getParamters().get(0).getValue()!=inputs.getInputs().get(0).getParamters().get(1).getValue());
		assertTrue(inputs.getInputs().get(1).getParamters().get(0).getValue()!=inputs.getInputs().get(1).getParamters().get(1).getValue());

	}


}




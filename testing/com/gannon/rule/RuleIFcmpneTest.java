package com.gannon.rule;

import static org.junit.Assert.*;

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
import com.gannon.jvm.data.input.Input;
import com.gannon.jvm.data.input.InputCollection;
import com.gannon.jvm.data.input.Parameter;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BIFicmpne;

public class RuleIFcmpneTest {
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
	// __1(4) 2 (4)
	//
	//
	@Test
	public void testNotEqualTrue() {
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
		Dependency i4 = new Dependency(root1, new BIFicmpne(lable, 5));
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
		boolean expectedPredicateResult = true;

		// init
		RuleIFcmpne rule = new RuleIFcmpne(expectedPredicateResult, input, dependencies, leftNode, rightNode, inputs);
		rule.dataGeneration();
		
		System.out.println(inputs);
		assertEquals(4,inputs.getInputs().get(0).getParameterByIndex(2).getValue());
		assertEquals(4,inputs.getInputs().get(1).getParameterByIndex(2).getValue());
		assertEquals(4,inputs.getInputs().get(2).getParameterByIndex(1).getValue());
		assertEquals(4,inputs.getInputs().get(3).getParameterByIndex(1).getValue());
		
		assertNotEquals(inputs.getInputs().get(0).getParameterByIndex(1).getValue(),inputs.getInputs().get(0).getParameterByIndex(2).getValue());
		assertNotEquals(inputs.getInputs().get(1).getParameterByIndex(1).getValue(),inputs.getInputs().get(1).getParameterByIndex(2).getValue());
		assertNotEquals(inputs.getInputs().get(2).getParameterByIndex(1).getValue(),inputs.getInputs().get(2).getParameterByIndex(2).getValue());
		assertNotEquals(inputs.getInputs().get(3).getParameterByIndex(1).getValue(),inputs.getInputs().get(3).getParameterByIndex(2).getValue());

	}

	//
	// _____100 (F)
	// ___ /   \
	// __1(40) 2 (4)
	//
	//
	@Test
	public void testNotEqualFalse() {
		// create an input
		Parameter p1 = new Parameter(1, 40);
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
		Dependency i4 = new Dependency(root1, new BIFicmpge(lable, 5));
		BinNode leftNode = new BinNode("1", 0);
		leftNode.setVariableValue(40);
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
		RuleIFcmpne rule = new RuleIFcmpne(expectedPredicateResult, input, dependencies, leftNode, rightNode, inputs);
		rule.dataGeneration();
		
		System.out.println(inputs);
		assertEquals(4,inputs.getInputs().get(0).getParameterByIndex(1).getValue());
		assertEquals(4,inputs.getInputs().get(0).getParameterByIndex(2).getValue());
		assertEquals(40,inputs.getInputs().get(1).getParameterByIndex(1).getValue());
		assertEquals(40,inputs.getInputs().get(1).getParameterByIndex(2).getValue());
		
		assertTrue(inputs.getInputs().get(1).getParameterByIndex(1).getValue()==inputs.getInputs().get(1).getParameterByIndex(2).getValue());
	}


}

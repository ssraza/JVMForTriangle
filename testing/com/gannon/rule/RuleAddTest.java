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
import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.instructions.BIFicmpeq;
import com.gannon.jvm.instructions.BIFicmpne;

public class RuleAddTest {

	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	// _______ i4 () add distance 9
	// __________/ \
	// _________i2(20) i3 (30)
	//
	//
	 @Test
	 public void testAddIncreaseDistance9() {
	 // create an input
	 int distance = 9;
	 int oldValue_i2 = 20;
	 int oldValue_i3 = 30;
	 Parameter p1 = new Parameter(1, 10);
	 Parameter p2 = new Parameter(2, oldValue_i2);
	 Parameter p3 = new Parameter(3, oldValue_i3);
	 Input input = new Input(1);
	 input.add(p1);
	 input.add(p2);
	 input.add(p3);
	
		// fourth dependency
		BinNode i4 = new BinNode("4", 0);
		Dependency d4 = new Dependency(i4, new BIAdd(5));
		BinNode i2 = new BinNode("2", 0);
		i2.setVariableValue(oldValue_i2);
		BinNode i3 = new BinNode("3", 0);
		i3.setVariableValue(oldValue_i3);
		d4.insertToLeft(i2);
		d4.insertToRight(i3);

		// add four dependencies to Dependencies
		Dependencies dependencies = new Dependencies();
		dependencies.add(d4);

		// create input collection to collect generated inputs
		InputCollection inputs = new InputCollection(1);

		// set expected predicate result
		boolean expectedPredicateResult = true;

		// init, add node increases 10
		RuleIAdd rule = new RuleIAdd(expectedPredicateResult, input, dependencies, i2, i3, inputs, distance);
		rule.dataGeneration();

		System.out.println(inputs);
		assertEquals(oldValue_i2 + distance, inputs.getInputs().get(0).getParameterByIndex(2).getValue());
		assertEquals(oldValue_i3 + distance, inputs.getInputs().get(1).getParameterByIndex(3).getValue());

	}

	//
	// _____i1002 (T) IFcompEqual
	// ___ / \
	// __i1(10) i001 (50) add
	// __________/ \
	// _________i2(20) i3 (30)
	//
	//
	@Test
	public void testAddIFEqual() {
		// create an input
		int distance = 9;
		int oldValue_i1 = 10;
		int oldValue_i2 = 20;
		int oldValue_i3 = 30;
		int oldValue_i4 = 50;
		Parameter p1 = new Parameter(1, oldValue_i1);
		Parameter p2 = new Parameter(2, oldValue_i2);
		Parameter p3 = new Parameter(3, oldValue_i3);
		Input input = new Input(1);
		input.add(p1);
		input.add(p2);
		input.add(p3);

		// fourth dependency
		BinNode i1001 = new BinNode("10001", 0);
		i1001.setVariableValue(oldValue_i4);
		Dependency d4 = new Dependency(i1001, new BIAdd(5));
		BinNode i2 = new BinNode("2", 0);
		i2.setVariableValue(oldValue_i2);
		BinNode i3 = new BinNode("3", 0);
		i3.setVariableValue(oldValue_i3);
		d4.insertToLeft(i2);
		d4.insertToRight(i3);

		// add four dependencies to Dependencies
		Dependencies dependencies = new Dependencies();
		dependencies.add(d4);

		// fifth dependency
		BinNode root = new BinNode("1000", 0);
		BLabel lable = new BLabel(new Label());
		Dependency d5 = new Dependency(root, new BIFicmpeq(lable, 5));
		BinNode i1 = new BinNode("1", 0);
		i1.setVariableValue(10);
		d5.insertToLeft(i1);
		d5.insertToRight(d4.getTheBTRootNode());

		// add four dependencies to Dependencies
		dependencies.add(d5);

		// create input collection to collect generated inputs
		InputCollection inputs = new InputCollection(1);

		// set expected predicate result
		boolean expectedPredicateResult = true;

		// init
		RuleIFcmpeq rule = new RuleIFcmpeq(expectedPredicateResult, input, dependencies, i1, i1001, inputs);
		rule.dataGeneration();

		System.out.println(inputs);
		assertEquals(oldValue_i4, inputs.getInputs().get(0).getParameterByIndex(1).getValue());
		assertEquals(oldValue_i2, inputs.getInputs().get(0).getParameterByIndex(2).getValue());
		assertEquals(oldValue_i3, inputs.getInputs().get(0).getParameterByIndex(3).getValue());

		assertEquals(oldValue_i1, inputs.getInputs().get(1).getParameterByIndex(1).getValue());
		assertEquals(-20, inputs.getInputs().get(1).getParameterByIndex(2).getValue());
		assertEquals(oldValue_i3, inputs.getInputs().get(1).getParameterByIndex(3).getValue());
		
		assertEquals(oldValue_i1, inputs.getInputs().get(2).getParameterByIndex(1).getValue());
		assertEquals(20, inputs.getInputs().get(2).getParameterByIndex(2).getValue());
		assertEquals(-10, inputs.getInputs().get(2).getParameterByIndex(3).getValue());

	}

}

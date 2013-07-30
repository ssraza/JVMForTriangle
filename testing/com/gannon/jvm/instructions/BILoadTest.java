package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;

public class BILoadTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	@Test
	public void testGetOpcode() {
		BAStore instance = new BAStore(1,10);
		int expResult = 58;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		BAStore instance = new BAStore(1,12);

		String expResult = "astore";
		String result = instance.getOpCodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFramePostion2() {
		BILoad bILoad = new BILoad(2,5);// Initialize BILoad, pass 0 as operand value
		// init local Variable table
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(10); // add value 10 to index 0 of operand stack
		operandStack.add(1);
		operandStack.add(9);
		varTable.add(7);
		varTable.add(2);
		varTable.add(5);

		BFrame activeFrame = new BFrame(0, varTable, operandStack);

		// Before calling the execute method,  LocalVariableTable will have 7 on its 0th position, 2 on 1st and 5 on 2nd.
		// Expectation is, BILoad(2) should load what ever is there on 2nd position of local variable table,
		// to the TOP of the stack of operand.

		bILoad.execute(activeFrame);

		Stack<Object> resultOperandStack = activeFrame.getOperandStack();

		Object result = resultOperandStack.lastElement();

		assertEquals(result, new Integer(5));
	}

	@Test
	public void testExecuteBFramePostion1() {
		BILoad bILoad = new BILoad(1,5);// Initialize BILoad, pass 0 as operand value
		// init local Variable table
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(10); // add value 10 to index 0 of operand stack
		operandStack.add(1);
		operandStack.add(9);
		varTable.add(7);
		varTable.add(2);
		varTable.add(5);

		BFrame activeFrame = new BFrame(0, varTable, operandStack);

		// Before calling the execute method,  LocalVariableTable will have 7 on its 0th position, 2 on 1st and 5 on 2nd.
		// Expectation is, BILoad(1) should load what ever is there on 1st position of local variable table,
		// to the TOP of the stack of operand.

		bILoad.execute(activeFrame);

		Stack<Object> resultOperandStack = activeFrame.getOperandStack();

		Object result = resultOperandStack.lastElement();

		assertEquals(result, new Integer(2));
	}

	@Test
	public void testExecuteBFramePostion3() {
		// create local variable table
		// varTable index starts from 0
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(7);
		varTable.add(2);
		varTable.add(5);
		varTable.add(9);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		//create a frame
		BFrame activeFrame = new BFrame(0, varTable, operandStack);

		//invoke iload 3
		BILoad bILoad = new BILoad(3,6);
		bILoad.execute(activeFrame);

		//check the stack after exeuction
		Stack<Object> resultOperandStack = activeFrame.getOperandStack();
		Integer actualResult = (Integer)resultOperandStack.peek();

		//compare results
		assertEquals(actualResult, new Integer(9));
	}
	
	@Test
	public void testDependency() {
		
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		dependency.initInputs();
		BILoad iLoad = new BILoad(2,0); 
		iLoad.analyzing(dependency);
		Stack<String> resultStack = dependency.getIntermediateVariableStack();
		Stack<String> expectedStack = new Stack<String>();
		expectedStack.push("2");
		assertEquals(expectedStack, resultStack);
	}
}

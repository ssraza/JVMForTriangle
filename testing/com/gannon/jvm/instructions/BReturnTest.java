package com.gannon.jvm.instructions;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.DependencyFrame;
import com.gannon.jvm.execution.method.BFrame;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.progam.path.TestPath;

public class BReturnTest {

	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BReturn instance = new BReturn(10);
		int expResult = 177;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BReturn instance = new BReturn(10);

		String expResult = "return";
		String result = instance.getOpcodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteOneMethodOnStack() {
		BReturn bReturn = new BReturn(10);

		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(new Integer(1)); // add value 1 to index 0

		BFrame activeFrame = new BFrame(operandStack, varTable, 0);
		
	//	RelationFrame.getInstance().pushFrame(activeFrame);
		
		// Before calling the execute method,  operand stack of active frame has 1 on its TOS. JVMStack has only one method.
		// Expectation is, BIReturn should return null
		// and unload(POP) that active method frame form JVMStack.
		
		Object returnedObject = bReturn.execute(activeFrame);
	//	Integer resultedJVMStack = (Integer)RelationFrame.getInstance().size();
		assertEquals(returnedObject, null);
	}
	
	@Test
	public void testExecuteTwoMethodsOnStack() {
		BReturn bReturn = new BReturn(10);

		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
		operandStack.add(new Integer(10)); // add value 1 to index 0

		BFrame activeFrame = new BFrame(operandStack, varTable, 0);
		
	//	RelationFrame.getInstance().pushFrame(activeFrame);
		
		BLocalVarTable secondVarTable = new BLocalVarTable();
		Stack<Integer> secondOperandStack = new Stack<Integer>();
		operandStack.add(new Integer(7)); // add value 1 to index 0

		BFrame secondActiveFrame = new BFrame(operandStack, varTable, 0);
		
	//	RelationFrame.getInstance().pushFrame(secondActiveFrame);
		
		// Before calling the execute method,  operand stack of active frame has 7 on its TOS. JVMStack has only two method frame on its stack.
		// the Method frame on TOP of the JVM stack will be called Active Method Frame.
		// Expectation is, BIReturn should return null
		// and unload(POP) that active method frame form JVMStack. JVMStack will then consider the TOS Method Frame as active method Frame.

		Object returnedObject = bReturn.execute(activeFrame);
		assertEquals(returnedObject, null);

	}

	@Test
	public void testDependency() {
		DependencyFrame dependency = new DependencyFrame();
		TestPath targetPath = new TestPath();
		BMethod method = new BMethod(1, "", "(III)I");
		targetPath.setbMethod(method);
		dependency.setTargetPath(targetPath);
		dependency.initParameterRelation();
		dependency.getIntermediateVariableNameStack().push("6");
		BReturn retrn = new BReturn(10);
		retrn.analyzing(dependency);
		Stack<String> resultStack = dependency.getIntermediateVariableNameStack();
		//String result = resultStack.peek();
		assertEquals(new Stack(), resultStack);
	}
}

package com.gannon.jvm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;
import com.gannon.jvm.JVMStackSingleton;
import com.gannon.jvm.MethodExecutor;

public class MethodExecutorTest {

	@Test
	public void testEquilateral () {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		//System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(5);
		varTable.add(5);
		varTable.add(5);
		varTable.add(5);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(m, 0, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		//assertion
		assertEquals(new Integer(1), new MethodExecutor().execute(JVMStackSingleton.getInstance()));
	}

	@Test
	public void testScalene() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(5);
		varTable.add(6);
		varTable.add(7);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(m, 0, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		//assertion
		assertEquals(new Integer(2), new MethodExecutor().execute(JVMStackSingleton.getInstance()));
	}

	@Test
	public void testIsosceles1() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(5);
		varTable.add(6);
		varTable.add(6);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(m, 0, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		//assertion
		assertEquals(new Integer(3), new MethodExecutor().execute(JVMStackSingleton.getInstance()));
	}


	@Test
	public void testIsosceles2() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(7);
		varTable.add(7);
		varTable.add(6);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(m, 0, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		//assertion
		assertEquals(new Integer(3), new MethodExecutor().execute(JVMStackSingleton.getInstance()));
	}

	@Test
	public void testInotATriangle() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(7);
		varTable.add(7);
		varTable.add(77);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(m, 0, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		//assertion
		assertEquals(new Integer(4), methodExecutor.execute(JVMStackSingleton.getInstance()));
	}

	@Test
	public void testInotATriangleExePath() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(770);
		varTable.add(7);
		varTable.add(7);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(m, 0, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
	    expectedExeIDs.add(0);
	    expectedExeIDs.add(1);
	    expectedExeIDs.add(2);
	    expectedExeIDs.add(3);
	    expectedExeIDs.add(4);
	    expectedExeIDs.add(36);
	    expectedExeIDs.add(37);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}
}

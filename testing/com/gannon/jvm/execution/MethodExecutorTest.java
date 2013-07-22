package com.gannon.jvm.execution;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Stack;
import org.junit.Test;
import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.execution.BFrame;
import com.gannon.jvm.execution.BLocalVarTable;
import com.gannon.jvm.execution.JVMStackSingleton;
import com.gannon.jvm.execution.MethodExecutor;

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

	@Test
	public void testInotATriangleExePath2() {
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
		varTable.add(77);
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
	    expectedExeIDs.add(5);
	    expectedExeIDs.add(6);
	    expectedExeIDs.add(7);
	    expectedExeIDs.add(8);
	    expectedExeIDs.add(9);
	    expectedExeIDs.add(36);
	    expectedExeIDs.add(37);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testInotATriangleExePath3() {
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

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
	    expectedExeIDs.add(0);
	    expectedExeIDs.add(1);
	    expectedExeIDs.add(2);
	    expectedExeIDs.add(3);
	    expectedExeIDs.add(4);
	    expectedExeIDs.add(5);
	    expectedExeIDs.add(6);
	    expectedExeIDs.add(7);
	    expectedExeIDs.add(8);
	    expectedExeIDs.add(9);
	    expectedExeIDs.add(10);
	    expectedExeIDs.add(11);
	    expectedExeIDs.add(12);
	    expectedExeIDs.add(13);
	    expectedExeIDs.add(14);
	    expectedExeIDs.add(36);
	    expectedExeIDs.add(37);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testEquilateralTriangleExePath3() {
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
	    expectedExeIDs.add(5);
	    expectedExeIDs.add(6);
	    expectedExeIDs.add(7);
	    expectedExeIDs.add(8);
	    expectedExeIDs.add(9);
	    expectedExeIDs.add(10);
	    expectedExeIDs.add(11);
	    expectedExeIDs.add(12);
	    expectedExeIDs.add(13);
	    expectedExeIDs.add(14);
	    expectedExeIDs.add(15);
	    expectedExeIDs.add(16);
	    expectedExeIDs.add(17);
	    expectedExeIDs.add(18);
	    expectedExeIDs.add(19);
	    expectedExeIDs.add(20);
	    expectedExeIDs.add(21);
	    expectedExeIDs.add(22);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testScaleneTriangleExePath1() {
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
		varTable.add(5);
		varTable.add(3);

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
	    expectedExeIDs.add(5);
	    expectedExeIDs.add(6);
	    expectedExeIDs.add(7);
	    expectedExeIDs.add(8);
	    expectedExeIDs.add(9);
	    expectedExeIDs.add(10);
	    expectedExeIDs.add(11);
	    expectedExeIDs.add(12);
	    expectedExeIDs.add(13);
	    expectedExeIDs.add(14);
	    expectedExeIDs.add(15);
	    expectedExeIDs.add(16);
	    expectedExeIDs.add(17);
	    expectedExeIDs.add(23);
	    expectedExeIDs.add(24);
	    expectedExeIDs.add(25);
	    expectedExeIDs.add(26);
	    expectedExeIDs.add(27);
	    expectedExeIDs.add(28);
	    expectedExeIDs.add(29);
	    expectedExeIDs.add(30);
	    expectedExeIDs.add(31);
	    expectedExeIDs.add(32);
	    expectedExeIDs.add(33);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testScaleneTriangleExePath2() {
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
		varTable.add(10);
		varTable.add(5);

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
	    expectedExeIDs.add(5);
	    expectedExeIDs.add(6);
	    expectedExeIDs.add(7);
	    expectedExeIDs.add(8);
	    expectedExeIDs.add(9);
	    expectedExeIDs.add(10);
	    expectedExeIDs.add(11);
	    expectedExeIDs.add(12);
	    expectedExeIDs.add(13);
	    expectedExeIDs.add(14);
	    expectedExeIDs.add(15);
	    expectedExeIDs.add(16);
	    expectedExeIDs.add(17);	 
	    expectedExeIDs.add(23);
	    expectedExeIDs.add(24);
	    expectedExeIDs.add(25);
	    expectedExeIDs.add(26);
	    expectedExeIDs.add(27);
	    expectedExeIDs.add(28);
	    expectedExeIDs.add(29);
	    expectedExeIDs.add(30);
	    expectedExeIDs.add(31);
	    expectedExeIDs.add(32);
	    expectedExeIDs.add(33);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testIsoScaleTriangleExePath1() {
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
		varTable.add(1);

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
	    expectedExeIDs.add(5);
	    expectedExeIDs.add(6);
	    expectedExeIDs.add(7);
	    expectedExeIDs.add(8);
	    expectedExeIDs.add(9);
	    expectedExeIDs.add(10);
	    expectedExeIDs.add(11);
	    expectedExeIDs.add(12);
	    expectedExeIDs.add(13);
	    expectedExeIDs.add(14);
	    expectedExeIDs.add(15);
	    expectedExeIDs.add(16);
	    expectedExeIDs.add(17);
	    expectedExeIDs.add(23);
	    expectedExeIDs.add(24);
	    expectedExeIDs.add(25);
	    expectedExeIDs.add(26);
	    expectedExeIDs.add(27);
	    expectedExeIDs.add(28);
	    expectedExeIDs.add(29);
	    expectedExeIDs.add(30);
	    expectedExeIDs.add(31);
	    expectedExeIDs.add(32);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testIsoscaleTriangleExePath2() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(3);
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
	    expectedExeIDs.add(5);
	    expectedExeIDs.add(6);
	    expectedExeIDs.add(7);
	    expectedExeIDs.add(8);
	    expectedExeIDs.add(9);
	    expectedExeIDs.add(10);
	    expectedExeIDs.add(11);
	    expectedExeIDs.add(12);
	    expectedExeIDs.add(13);
	    expectedExeIDs.add(14);
	    expectedExeIDs.add(15);
	    expectedExeIDs.add(16);
	    expectedExeIDs.add(17);
	    expectedExeIDs.add(18);
	    expectedExeIDs.add(19);
	    expectedExeIDs.add(20);
	    expectedExeIDs.add(21);
	    expectedExeIDs.add(24);
	    expectedExeIDs.add(25);
	    expectedExeIDs.add(26);
	    expectedExeIDs.add(27);
	    expectedExeIDs.add(28);
	    expectedExeIDs.add(29);
	    expectedExeIDs.add(30);
	    expectedExeIDs.add(31);
	    expectedExeIDs.add(32);
	    expectedExeIDs.add(33);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}
}

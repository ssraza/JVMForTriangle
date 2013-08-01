package com.gannon.jvm.execution.method;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.utilities.ConstantsUtility;

public class MethodExecutorTest {

	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

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
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

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
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		//assertion
		assertEquals(new Integer(2), new MethodExecutor().execute(JVMStackSingleton.getInstance()));
	}

	@Test
	public void testIsoscale1() {
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
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

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
		varTable.add(6);
		varTable.add(7);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		//assertion
		assertEquals(new Integer(3), new MethodExecutor().execute(JVMStackSingleton.getInstance()));
	}

	@Test
	public void testIsosceles3() {
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
		varTable.add(5);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

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
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		//assertion
		assertEquals(new Integer(4), methodExecutor.execute(JVMStackSingleton.getInstance()));
	}

	@Test
	public void testInotATriangleExePathID10() {
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
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
	    expectedExeIDs.add(1);
	    expectedExeIDs.add(2);
	    expectedExeIDs.add(3);
	    expectedExeIDs.add(4);
	    expectedExeIDs.add(5);
	    expectedExeIDs.add(37);
	    expectedExeIDs.add(38);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testInotATriangleExePathID11() {
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
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(37);
	    expectedExeIDs.add(38);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testInotATriangleExePathID12() {
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
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(37);
	    expectedExeIDs.add(38);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testEquilateralTriangleExePathID1() {
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
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(23);
	    
		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testScaleneTriangleExePathID8() {
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
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
	  
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
	    expectedExeIDs.add(34);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	@Test
	public void testScaleneTriangleExePathID9() {
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
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(34);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}

	/*@Test
	public void testInvisibleExePath() {
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
		BFrame activeFrame = new BFrame(m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(24);
	    expectedExeIDs.add(25);
	    expectedExeIDs.add(26);
	    expectedExeIDs.add(35);
	    expectedExeIDs.add(36);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}*/

	@Test
	public void testIsoscaleTriangleExePath2_invisible() {
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
		varTable.add(3);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(35);
	    expectedExeIDs.add(36);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}
	
	@Test
	public void testIsoscaleTriangleExePath3() {
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
		varTable.add(7);
		varTable.add(5);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(24);
	    expectedExeIDs.add(25);
	    expectedExeIDs.add(26);
	    expectedExeIDs.add(27);
	    expectedExeIDs.add(28);
	    expectedExeIDs.add(29);
	    expectedExeIDs.add(35);
	    expectedExeIDs.add(36);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}
	
	@Test
	public void testIsoscaleTriangleExePath4() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(4);
		varTable.add(5);
		varTable.add(5);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(24);
	    expectedExeIDs.add(25);
	    expectedExeIDs.add(26);
	    expectedExeIDs.add(27);
	    expectedExeIDs.add(28);
	    expectedExeIDs.add(29);
	    expectedExeIDs.add(30);
	    expectedExeIDs.add(31);
	    expectedExeIDs.add(32);
	    expectedExeIDs.add(35);
	    expectedExeIDs.add(36);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}
	
	@Test
	public void testIsoscaleTriangleExePath5() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(4);
		varTable.add(4);
		varTable.add(5);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(35);
	    expectedExeIDs.add(36);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}
	
	
	@Test
	public void testIsoscaleTriangleExePath6_InvisiblePath() {
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
		varTable.add(4);
		varTable.add(5);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(24);
	    expectedExeIDs.add(25);
	    expectedExeIDs.add(26);
	    expectedExeIDs.add(27);
	    expectedExeIDs.add(28);
	    expectedExeIDs.add(29);
	    expectedExeIDs.add(35);
	    expectedExeIDs.add(36);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}
	
	@Test
	public void testIsoscaleTriangleExePath7_InvisiblePath() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(4);
		varTable.add(5);
		varTable.add(5);

		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);

		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);

		methodExecutor.execute(JVMStackSingleton.getInstance());
	    ArrayList<Integer> expectedExeIDs=new ArrayList<Integer>();
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
	    expectedExeIDs.add(24);
	    expectedExeIDs.add(25);
	    expectedExeIDs.add(26);
	    expectedExeIDs.add(27);
	    expectedExeIDs.add(28);
	    expectedExeIDs.add(29);
	    expectedExeIDs.add(30);
	    expectedExeIDs.add(31);
	    expectedExeIDs.add(32);
	    expectedExeIDs.add(35);
	    expectedExeIDs.add(36);

		//assertion
		assertEquals(expectedExeIDs, methodExecutor.getExecutedInsIDs());
	}
	
	
	@Test
	public void testAdditionSingleMethod() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Hello.class");
		BMethod m = myclass.getMethod("callee2");

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(5);
		varTable.add(4);
		varTable.add(0);
		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);
		System.out.println(activeFrame.getVarTable().getLocalVars());
		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);
		Integer finalAddtion = (Integer)methodExecutor.execute(JVMStackSingleton.getInstance());
		
		
		//assertion
		assertEquals(new Integer(9), finalAddtion);
	}
	
	@Test
	public void testAdditionMethodCallingMethod() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Hello.class");
		BMethod m = myclass.getMethod("intCaller");

		// create local variable table
		// varTable index starts from 0
		// three sides
		BLocalVarTable varTable = new BLocalVarTable();
		varTable.add(0);
		varTable.add(5);
		varTable.add(0);
		varTable.add(0);
		// create a stack
		Stack<Integer> operandStack = new Stack<Integer>();

		// create a frame
		BFrame activeFrame = new BFrame(myclass, m, ConstantsUtility.INIT_PROGRAM_LINE_NUMBER, varTable, operandStack);
		System.out.println(activeFrame.getVarTable().getLocalVars());
		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//push active frame to JVM stack
		JVMStackSingleton.getInstance().clear();
		JVMStackSingleton.getInstance().pushFrame(activeFrame);
		Integer finalAddtion = (Integer)methodExecutor.execute(JVMStackSingleton.getInstance());	
		
		//assertion
		assertEquals(new Integer(25), finalAddtion);
	}
}

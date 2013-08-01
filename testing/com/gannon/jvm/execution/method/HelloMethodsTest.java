package com.gannon.jvm.execution.method;


public class HelloMethodsTest {

	/*@Test
	public void testAdditionMethodCallingMethodTwoLevel() {
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
		assertEquals(new Integer(10), finalAddtion);
	}*/

	
}

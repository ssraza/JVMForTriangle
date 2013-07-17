package com.gannon.jvm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

import com.gannon.ASM.BytecodeClassGenerator.BClassGenerator;
import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.BLocalVarTable;
import com.gannon.jvm.JVMStackSingleton;
import com.gannon.jvm.MethodExecutor;

public class MethodExecutorTest {

	@Test
	public void test() {
		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

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

}

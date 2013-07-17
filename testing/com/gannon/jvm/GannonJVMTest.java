package com.gannon.jvm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;
import org.objectweb.asm.Label;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BLabel;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.GannonJVM;
import com.gannon.jvm.JVMStackSingleton;
import com.gannon.jvm.MethodExecutor;
import com.gannon.jvm.instructions.BALoad;
import com.gannon.jvm.instructions.BBipush;
import com.gannon.jvm.instructions.BILoad;
import com.gannon.jvm.instructions.BIReturn;
import com.gannon.jvm.instructions.BIStore;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BInvokeVirtual;

public class GannonJVMTest {

	@Test
	public void testClassName() {
		BClass myclass=BClassGenerator.getBClass("Triangle.class");
		assertEquals("Triangle", myclass.getShortClassName());
	}


	@Test
	public void testMethodName() {
		BClass myclass=BClassGenerator.getBClass("Triangle.class");
		BMethod m=myclass.getMethod("triangleType");
		assertEquals("triangleType", m.getName());
	}

	@Test
	public void testMethodTriangleTypeEqual() {
		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//create a JVM
		//need two parameters JVMStackSingleton jvmStack, MethodExecutor executor
		GannonJVM jvm=new GannonJVM(JVMStackSingleton.getInstance(),methodExecutor);

		//create input
		ArrayList<Object> inputs=new ArrayList<Object>();
		inputs.add(0);
		inputs.add(5);
		inputs.add(5);
		inputs.add(5);

		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		//assertion
		assertEquals(new Integer(1), jvm.run(m,inputs));
	}

	@Test
	public void testMethodTriangleTypeScalene() {
		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//create a JVM
		//need two parameters JVMStackSingleton jvmStack, MethodExecutor executor
		GannonJVM jvm=new GannonJVM(JVMStackSingleton.getInstance(),methodExecutor);

		//create input
		ArrayList<Object> inputs=new ArrayList<Object>();
		inputs.add(0);
		inputs.add(5);
		inputs.add(8);
		inputs.add(9);

		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		//assertion
		assertEquals(new Integer(2), jvm.run(m,inputs));
	}


	@Test
	public void testMethodTriangleTypeIsosceles() {
		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//create a JVM
		//need two parameters JVMStackSingleton jvmStack, MethodExecutor executor
		GannonJVM jvm=new GannonJVM(JVMStackSingleton.getInstance(),methodExecutor);

		//create input
		ArrayList<Object> inputs=new ArrayList<Object>();
		inputs.add(0);
		inputs.add(8);
		inputs.add(8);
		inputs.add(9);

		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		//assertion
		assertEquals(new Integer(3), jvm.run(m,inputs));
	}


	@Test
	public void testMethodTriangleTypeNotATriangle() {
		// create an executor
		MethodExecutor methodExecutor = new MethodExecutor();

		//create a JVM
		//need two parameters JVMStackSingleton jvmStack, MethodExecutor executor
		GannonJVM jvm=new GannonJVM(JVMStackSingleton.getInstance(),methodExecutor);

		//create input
		ArrayList<Object> inputs=new ArrayList<Object>();
		inputs.add(0);
		inputs.add(8);
		inputs.add(78);
		inputs.add(9);

		//get Method instructions
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		System.out.print(m);

		//assertion
		assertEquals(new Integer(4), jvm.run(m,inputs));
	}
}

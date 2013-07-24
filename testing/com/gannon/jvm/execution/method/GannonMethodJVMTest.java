package com.gannon.jvm.execution.method;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.execution.method.GannonMethodJVM;
import com.gannon.jvm.execution.method.JVMStackSingleton;

public class GannonMethodJVMTest {

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
		GannonMethodJVM jvm=new GannonMethodJVM();

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
		GannonMethodJVM jvm=new GannonMethodJVM();

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
		GannonMethodJVM jvm=new GannonMethodJVM();

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
		GannonMethodJVM jvm=new GannonMethodJVM();

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

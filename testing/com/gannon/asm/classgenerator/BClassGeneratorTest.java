package com.gannon.asm.classgenerator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;

public class BClassGeneratorTest {

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
	public void testMethod2() {
		BClass myclass=BClassGenerator.getBClass("Triangle.class");
		BMethod m=myclass.getMethod("triangleType");
		//System.out.print(m);
	}
}

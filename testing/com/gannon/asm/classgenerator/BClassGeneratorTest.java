package com.gannon.asm.classgenerator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gannon.ASM.components.BClass;
import com.gannon.ASM.components.BMethod;
import com.gannon.asm.classgenerator.BClassGenerator;

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

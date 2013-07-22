package com.gannon.asm.components;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

public class BClassTest {

	@Test
	public void testClassName() {
		Class c = null;
		try {
			c = Class.forName("com.gannon.asm.components.Triangle");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// c.toString();
		assertEquals("Triangle", Triangle.class.getSimpleName());

	}

	@Test
	public void testFindMethodByName() {
		Class c = null;
		try {
			c = Class.forName("com.gannon.asm.components.Triangle");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Method[] methods = c.getMethods();
		Method m = new BClass().findMethodByName(methods, "triangleType");
		assertEquals("triangleType", m.getName());

	}

	@Test
	public void testRetriveNumberOfParameters() {
		Class c = null;
		try {
			c = Class.forName("com.gannon.asm.components.Triangle");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Method[] methods = c.getMethods();
		Method m = new BClass().findMethodByName(methods, "triangleType");
		assertEquals(3, m.getParameterTypes().length);
	}
}

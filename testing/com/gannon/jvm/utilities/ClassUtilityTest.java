package com.gannon.jvm.utilities;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

import com.gannon.jvm.utilities.ClassUtility;
import com.gannon.jvm.utilities.Triangle;


public class ClassUtilityTest {

	@Test
	public void testClassName() {
		Class c = null;
		try {
			c = Class.forName("com.gannon.jvm.utilities.Triangle");
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
			c = Class.forName("com.gannon.jvm.utilities.Triangle");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Method[] methods = c.getMethods();
		Method m = new ClassUtility().findMethodByName(methods, "triangleType");
		assertEquals("triangleType", m.getName());

	}

	@Test
	public void testRetriveNumberOfParameters() {
		Class c = null;
		try {
			c = Class.forName("com.gannon.jvm.utilities.Triangle");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Method[] methods = c.getMethods();
		Method m = new ClassUtility().findMethodByName(methods, "triangleType");
		assertEquals(3, m.getParameterTypes().length);
	}
	
	@Test
	public void testRetriveNumberOfParameters2() {
		int m = new ClassUtility().retriveNumberOfParameters("com.gannon.jvm.utilities.Triangle", "triangleType");
		assertEquals(3, m);
	}
}

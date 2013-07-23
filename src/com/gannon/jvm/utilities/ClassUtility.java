package com.gannon.jvm.utilities;

import java.lang.reflect.Method;

public class ClassUtility {

	public static int retriveNumberOfParameters(String fullClassName, String methodName){
		Class c = null; 
		try {
			c = Class.forName(fullClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
		Method[] methods=c.getMethods(); 
		Method m=findMethodByName(methods, methodName);
		return m.getParameterTypes().length;
		
	}
	
	public static Method findMethodByName(Method[] methods, String targetMethod){
		for (Method m: methods){
			if(m.getName().equals(targetMethod)){
				return m;
			}
		}
		return null;
	}
}

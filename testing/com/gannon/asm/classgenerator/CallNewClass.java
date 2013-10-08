package com.gannon.asm.classgenerator;

public class CallNewClass {
	public TestGoTo callTriangleClass(){
		TestGoTo t = new TestGoTo();
		t.MethodA();//triangleType(4, 4, 4);
		return t;	
	}
}

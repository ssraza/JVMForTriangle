package com.gannon.asm.classgenerator;

public class CallNewClass {
	public Triangle callTriangleClass(){
		Triangle t = new Triangle();
		t.triangleType(4, 4, 4);
		return t;
		
	}

}

package com.gannon.asm.classgenerator;

public class TriangleType {
	public int triangleType(int a, int b, int c) {
		int k;
		if ((a < b + c) && (b < a + c) && (c < a + b)) {
			if ((a == b) && (b == c))
			{
				k = a+b;
				c=k;
				return 1;
			}
			else if (a != b && a != c && b != c)
				return 2;
			else{
				k = a+b-c;
				b=k+2;
				return 3;
			}
		} else {
			return 4;
		}
	}

}

package com.gannon.asm.components;

public class Triangle {
	public int triangleType(int a, int b, int c) {
		if ((a < b + c) && (b < a + c) && (c < a + b)) {
			if ((a == b) && (b == c))
				return 1;
			else if (a != b && a != c && b != c)
				return 2;
			else
				return 3;
		} else {
			return 4;
		}
	}

}

package com.gannon.asm.classgenerator;

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

//	public int test(int score) {
//		int result=0;
//		if (score >= 300) {
//			result=1;
//		} else if (score >= 200) {
//			result=2;
//		} else if (score >= 50) {
//			result=3;
//		} else {
//			result=4;
//		}
//		return result;
//	}

}

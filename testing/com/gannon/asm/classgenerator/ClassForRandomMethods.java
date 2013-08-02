package com.gannon.asm.classgenerator;

public class ClassForRandomMethods {
	public int testGraphMethod(int a, int b, int c) {
		if (a < b) {
			return 1;
		} else if (b == c) {
			return 2;
		} else if (a < c) {
			return 3;
		} else if (a == c) {
			return 4;
		} else {
			return 5;
		}
	}

	public int testGraphMethod2(int a, int b, int c) {
		if (a < b) {
			if (b == c) {
				if (a < c) {
					if (a == c) {
						return 1;
					}
				}
			}
		}
		return 2;
	}
}

package com.gannon.asm.classgenerator;

public class Hello {

	int i = 1;
	int j = 0;

	public int intCaller(int i) {
		int k = plusFive(i);
		return k;
	}

	public int plusFive(int i) {
		int j = i + 10;
		int k = callee(j, 10);
		return k;
	}

	public int callee(int i, int j) {
		int n = i + j;
		return n;
	}

	public int callee2(int i, int j) {
		return i + j;
	}

	public int add1(int i) {
		return (i + 1);
	}

	public int add2(int i) {
		int k;
		int j;
		j = add1(i); // i incremented by 1 now
		k = add1(j); // i incremented by 2 now
		return k; // return i incremented by 2.
	}

	public int tripleCaller(int i) {
		i = add2(i);
		return i;
	}

	public void tripleCallerVoid(int i) {
		i = add2(i);
	}

	// 5,6,6
	public int triangleMethod(int a, int b, int c) {
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
	// if(a==b){
	// return 1;}
	// else{
	// return 2;
	// }

}

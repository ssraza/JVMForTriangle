package com.gannon.asm.classgenerator;

public class ExampleOne {
	public int example(int a, int b, int c) {
			if ((a+b+c)>(b+2)){
				if((a-c+5)>(b+c-2))
				{
					if((a-c+5)>(b+c-2))
						return a;
					else
						return b;
				}
				else
					return c;	
			}
			else
				return b;
	}
}

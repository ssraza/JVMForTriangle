package com.gannon.jvm.utilities;

import java.util.Iterator;
import java.util.Queue;

import com.gannon.jvm.data.input.Input;

public class Utility {

	public static void displayInputQueue(Queue<Input> qe) {
		Iterator<Input>it = qe.iterator();
		while (it.hasNext()) {
			Input iteratorValue = (Input) it.next();
			System.out.println("Input Queue Next Value :" + iteratorValue);
		}
	}
}

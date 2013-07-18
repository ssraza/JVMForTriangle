package com.gannon.Utility;

import java.util.ArrayList;
import java.util.Vector;

import com.gannon.jvm.Instructions.BInstruction;

public class Constant {
	// private Stack myStack;
	Vector<Integer> localVariable;
	
/*This method returns the value for ICONST_0,ICONST_1,ICONST_2,
 * ICONST_3,ICONST_4,ICONST_5,ICONST_6,ICONST_7,ICONST_8*/
	public int getMethodArguments(int opcode, int oprand) {
		// localVariable.clear();
		int argument;
		if (opcode == 16) {
			argument = oprand;
		} else if (opcode == 3) {
			argument = 0;
		} else if (opcode == 4) {
			argument = 1;
		} else if (opcode == 5) {
			argument = 2;
		} else if (opcode == 6) {
			argument = 3;
		} else if (opcode == 7) {
			argument = 4;
		} else if (opcode == 8) {
			argument = 5;
		} else {
			argument = -1;
		}
		return argument;
	}

	public Vector<Integer> getLocalVariables(ArrayList<BInstruction> Instruction) {
		// localVariable.clear();
		for (BInstruction inst : Instruction) {
	//		int opcode = inst.getOpCode();
//			if (opcode == 16) {
//				int local = inst.getOperand1();
//				// localVariable.add(e)
//				localVariable.add(local);
//			} else if (opcode == 3) {
//				localVariable.add(0);
//			} else if (opcode == 4) {
//				localVariable.add(1);
//			} else if (opcode == 5) {
//				localVariable.add(2);
//			} else if (opcode == 6) {
//				localVariable.add(3);
//			} else if (opcode == 7) {
//				localVariable.add(4);
//			} else if (opcode == 8) {
//				localVariable.add(5);
//			}
		}
		return localVariable;
	}

}

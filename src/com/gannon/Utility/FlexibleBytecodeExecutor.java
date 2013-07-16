package com.gannon.Utility;

import java.util.Stack;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.ASM.BytecodeReader.ByteCodeClassGenerator;
import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;
import com.gannon.Executor.JVMExecutionObjects.JVMStackSingleton;
import com.gannon.Executor.MethodExecuter.MethodExecuter;
import com.gannon.Main.InterfaceAPISingleton;

public class FlexibleBytecodeExecutor {
	public static void main(String[] arg){
		//public void execution(){
			System.out.println("Start of Execution");
			ByteCodeClassGenerator clasGen = new ByteCodeClassGenerator("Hello.class");
			clasGen.cparser();
			BClass triangleClass = clasGen.getbFactory().getBClass();
			InterfaceAPISingleton.getInstance().setbClass(triangleClass);
			BMethod actualMethod = triangleClass.getMethods().get(2);
			
			BLocalVarTable localVarTable = new BLocalVarTable();
			localVarTable.add(0,5);
			localVarTable.add(1,3);
			localVarTable.add(2,6);
			localVarTable.add(3,0);
			
			Stack<Object> operandStack = new Stack<Object>();
			operandStack.push(0);
			operandStack.push(5);
					
			Integer pc = new Integer(4);
			System.out.println(localVarTable.getLocalVar());
			System.out.println(operandStack);
			BFrame activeFrame = new BFrame(localVarTable, pc, operandStack);
			activeFrame.setMethod(actualMethod);
			
			JVMStackSingleton.getInstance().addMethodFrame(activeFrame);//add caller's methodFrame
			
			MethodExecuter executor = new MethodExecuter(activeFrame);
			
			executor.execute();
			
		//	System.out.println(executor.getExecutedBytecodeList());
//		}
		}
}
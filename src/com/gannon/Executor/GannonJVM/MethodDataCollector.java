package com.gannon.Executor.GannonJVM;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;

public class MethodDataCollector {

   // private Stack myLocalVariableStack = new Stack();
	private BLocalVarTable myLocalVariabletable = new BLocalVarTable();
    private Stack myFrame = new Stack();
  //  private MethodFrame activeFrame = new MethodFrame();
    private BFrame activeFrame; 
    private BMethod bMethod;
    private ArrayList<BMethod> methodObjectList = new ArrayList<BMethod>();
    

    /*Getter method for activeFrame*/
    public BFrame getActiveFrame() {
        return this.activeFrame;
    }

    /*Constructor*/
    public MethodDataCollector() {
        super();
    }

    /*Setter method for activeFrame*/
    public void setActiveFrame(BFrame myFrame) {
        this.activeFrame = myFrame;
    }

    /*Constructor*/
    public MethodDataCollector(BClass bClass, Integer methodIndex, Vector<Integer> parameterValues) {
        super();
        for (int index = 0; index < parameterValues.size(); index++) {
            this.myLocalVariabletable.add(parameterValues.elementAt(index));
        }
        methodObjectList = bClass.getMethods();
        bMethod = methodObjectList.get(methodIndex);
        activeFrame = new BFrame(bMethod, 0, myLocalVariabletable);

         //   jvmStack = new JVMStack(activeFrame);
        }

    /*Execute method*/
    public int executeMethod() {
        System.out.println("In executeMethod at MethodCollector");
       // BytecodeExecutorDriver executeMethod = new BytecodeExecutorDriver(activeFrame);
        GannonJVM executeMEthod = new GannonJVM(activeFrame);
       // return executeMethod.executeMethod();
        return 0;
    }

    /*Execute method in the JVMStack*/
    public void executeMethodInStack() {
        executeMethod();
    }
}

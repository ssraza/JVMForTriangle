/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gannon.Main;

import java.util.ArrayList;
import java.util.Vector;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.jvm.BFrame;
import com.gannon.jvm.JVMStackSingleton;
import com.gannon.jvm.MethodDataCollector;
import com.gannon.jvm.MethodExecutor;

/**
 *
 * @author Nicholas
 */
public class GUIEventHandler {

    InterfaceAPISingleton interfaceAPI = InterfaceAPISingleton.getInstance();
    ArrayList<BMethod> bMthod = new ArrayList<BMethod>();
    BClass bClass = null;  
    Vector<Integer> overridesList = new Vector<Integer>();
    
    GUIEventHandler() {
 
    }
    
    protected void ExecuteAllSelected(){
        
                    overridesList = interfaceAPI.getOverridesList();
                    bClass = interfaceAPI.getbClass();
                    
                    int selectedMethodIndex = bClass.getIndexOf(interfaceAPI.getSelectedMethod());
//                    System.out.println("Selected method Index: "+ selectedMethodIndex);
                    bMthod = bClass.getMethods();
                    BMethod bMethod = bMthod.get(selectedMethodIndex);
                  //  ArrayList<BLocalVariable> BI = bMethod.getLocalVariables();
                    MethodDataCollector MDataCollector = new MethodDataCollector(bClass,
                            bClass.getIndexOf(interfaceAPI.getSelectedMethod()),overridesList);
                    JVMStackSingleton.getInstance().addMethodFrame((BFrame) MDataCollector.getActiveFrame());//add caller's methodFrame
//                    System.out.println("Size of JVM Stack: "+JVMStackSingleton.getInstance().size());
                    Integer displayValue = null;

                    MethodExecutor executeMethod = new MethodExecutor(JVMStackSingleton.getInstance().getActiveBFrame());
                    executeMethod.execute();
                    displayValue = executeMethod.getReturnValue();
                    interfaceAPI.setDisplayValue(displayValue);

                    JVMStackSingleton.getInstance().Erase();
    }
    
}

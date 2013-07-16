package com.gannon.Main;

import java.util.Vector;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeReader.ByteCodeClassGenerator;

/*
 * This class will pass the .class file name to ClassParser 
 * 
 * @author Pratik
 * @version 1.0  Mar 9, 2013.
 * */

public class InterfaceAPISingleton {
	    private String fileName;//Contains File name
	    private String pathName;
	    private BClass bClass;
	    private String selectedMethod;
	    private Vector<Integer> overridesList = new Vector<Integer>();
	    private Integer displayValue = null;

	    
	    private static InterfaceAPISingleton interfaceApi;
	    
	    public static InterfaceAPISingleton getInstance(){
	    	
	    	if (interfaceApi == null){
	    		
	    			interfaceApi = new InterfaceAPISingleton();
	    			
	    	}
	    	
	    	return interfaceApi;
	    }

	    public Integer getDisplayValue() {
	        return displayValue;
	    }

	    public void setDisplayValue(Integer displayValue) {
	        this.displayValue = displayValue;
	    }

	    public Vector<Integer> getOverridesList() {
	        return overridesList;
	    }

	    public void setOverridesList(Vector<Integer> overridesList) {
	        this.overridesList = overridesList;
	    }

	    public String getPathName() {
	        return pathName;
	    }

	    public void setPathName(String pathName) {
	        this.pathName = pathName;
	    }

	    public BClass getbClass() {
	        return bClass;
	    }

	    public void setbClass(BClass bClass) {
	        this.bClass = bClass;
	    }

	    public String getSelectedMethod() {
	        return selectedMethod;
	    }

	    public void setSelectedMethod(String selectedMethod) {
	        this.selectedMethod = selectedMethod;
	    }

	    public String getFileName() {
	        return fileName;
	    }

	    public void setFileName(String fileName) {
	        this.fileName = fileName;
	    }
	    
	    
	}


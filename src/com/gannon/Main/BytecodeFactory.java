/*
 * This clss will pass the object of BClass. 
 * The Executer class will get the object containing class 
 * and opcode details from this class
 * 
 * @author Pratik
 * @version 1.0  Mar 9, 2013.
 * */

package com.gannon.Main;

import com.gannon.ASM.BytecodeComponent.BClass;

public class BytecodeFactory {
	/**BClass object**/
	private BClass bClass;// BClass object parameter

	/**
	 * Get BClass object
	 * 
	 * **/
	public BClass getBClass() {
		return bClass;
	}

	/**
	 * Set BClass object
	 * 
	 * @param bClass   Object of BClass 
	 * **/
	public void setbClass(BClass bClass) {
		this.bClass = bClass;
	}
	
}

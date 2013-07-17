/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gannon.asm.components;

import com.gannon.Utility.HardBytecode;

import org.objectweb.asm.Label;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Pratik
 */
public class BJumpInstructions {
    
    private int opcode;
    private Label lable;

    public BJumpInstructions(int opcode, Label lable) {
        this.opcode = opcode;
        this.lable = lable;
    }

    public int getOpcode() {
        return opcode;
    }

    public Label getLable() {
        return lable;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public void setLable(Label lable) {
        this.lable = lable;
    }
    
    /**Display the Jump Instructions Details**/
	public void display(){
		System.out.println(new HardBytecode().getStringByteCode(opcode)+"  "+lable);
	}
    
}

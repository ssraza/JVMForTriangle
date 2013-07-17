/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gannon.asm.components;

import com.gannon.jvm.instructions.BInstruction;

/**
 *
 * @author Pratik
 */
public abstract class BFieldInsn extends BInstruction{
   
      public abstract void execute();
}

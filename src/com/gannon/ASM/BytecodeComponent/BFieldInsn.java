/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gannon.ASM.BytecodeComponent;

import com.gannon.Executor.Instruction.BInstruction;

/**
 *
 * @author Pratik
 */
public abstract class BFieldInsn extends BInstruction{
   
      public abstract void execute();
}

package com.gannon.bytecode.controlflowgraph;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.instructions.BILoad;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.NonPredicateNode;
import com.gannon.jvm.progam.path.TestPath;

public class BlockTest {

	@Test
	public void testEqualsSameID() {
		Block b=new Block(0); 
		b.addInstruction(new BILoad(1, 1));
		b.addInstruction(new BILoad(2, 2));
		b.addInstruction(new BILoad(3, 3));
		b.addInstruction(new BIAdd(4));
		
		Block c=new Block(0); 
		c.addInstruction(new BILoad(1, 1));
		c.addInstruction(new BILoad(2, 2));
		c.addInstruction(new BILoad(3, 3));
		c.addInstruction(new BIAdd(4));
			
		assertEquals(b,c);
		
	}
	
	@Test
	public void testEqualsDifferentID() {
		Block b=new Block(0); 
		b.addInstruction(new BILoad(1, 1));
		b.addInstruction(new BILoad(2, 2));
		b.addInstruction(new BILoad(3, 3));
		b.addInstruction(new BIAdd(4));
		
		Block c=new Block(0); 
		c.addInstruction(new BILoad(1, 5));
		c.addInstruction(new BILoad(2, 2));
		c.addInstruction(new BILoad(3, 3));
		c.addInstruction(new BIAdd(4));
			
		assertNotEquals(b,c);
		
	}

}

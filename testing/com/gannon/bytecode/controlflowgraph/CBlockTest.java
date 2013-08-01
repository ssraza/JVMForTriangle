package com.gannon.bytecode.controlflowgraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.instructions.BILoad;

public class CBlockTest {

	@Test
	public void testEqualsSameID() {
		CBlock b=new CBlock(0); 
		b.addInstruction(new BILoad(1, 1));
		b.addInstruction(new BILoad(2, 2));
		b.addInstruction(new BILoad(3, 3));
		b.addInstruction(new BIAdd(4));
		
		CBlock c=new CBlock(0); 
		c.addInstruction(new BILoad(1, 1));
		c.addInstruction(new BILoad(2, 2));
		c.addInstruction(new BILoad(3, 3));
		c.addInstruction(new BIAdd(4));
			
		assertEquals(b,c);
		
	}
	
	@Test
	public void testEqualsDifferentID() {
		CBlock b=new CBlock(0); 
		b.addInstruction(new BILoad(1, 1));
		b.addInstruction(new BILoad(2, 2));
		b.addInstruction(new BILoad(3, 3));
		b.addInstruction(new BIAdd(4));
		
		CBlock c=new CBlock(0); 
		c.addInstruction(new BILoad(1, 5));
		c.addInstruction(new BILoad(2, 2));
		c.addInstruction(new BILoad(3, 3));
		c.addInstruction(new BIAdd(4));
			
		assertNotEquals(b,c);
		
	}

}

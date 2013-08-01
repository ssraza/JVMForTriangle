package com.gannon.bytecode.controlflowgraph;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.objectweb.asm.Label;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BLabel;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BPredicateInstruction;

public class CBlocksTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	@Test
	public void testFindPredicateInstructionTargtBlockId_goto37() {
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		assertEquals("triangleType", m.getName());

		CFGMethod cfg = new CFGMethod(m);
		CBlocks blocks=cfg.buildBlocks();
		BLabel blabel=new BLabel(new Label());
		blabel.setGoToLineNumber(37);//jump to 37 
		BIFicmpge instr=new BIFicmpge(blabel,5);//line number is 5
		int actual=blocks.findPredicateInstructionTargtBlockId(instr);
		assertEquals(19,actual);
	}
	
	
	@Test
	public void testFindPredicateInstructionTargtBlockId_goto35() {
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		assertEquals("triangleType", m.getName());

		CFGMethod cfg = new CFGMethod(m);
		CBlocks blocks=cfg.buildBlocks();
		BLabel blabel=new BLabel(new Label());
		blabel.setGoToLineNumber(35);//jump to 35 
		BIFicmpge instr=new BIFicmpge(blabel,32);//line number is 5
		int actual=blocks.findPredicateInstructionTargtBlockId(instr);
		assertEquals(18,actual);
	}
	
	@Test
	public void testSize() {
		BClass myclass = BClassGenerator.getBClass("Triangle.class");
		BMethod m = myclass.getMethod("triangleType");
		assertEquals("triangleType", m.getName());

		CFGMethod cfg = new CFGMethod(m);
		CBlocks blocks=cfg.buildBlocks();
		//System.out.print(blocks);
		assertEquals(20,blocks.size());
	}

}

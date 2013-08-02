package com.gannon.bytecode.controlflowgraph;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.bytecode.controlflowgraph.CFGMethod;;

public class GenerateAllTestPathTest {

	BClass myclass = BClassGenerator.getBClass("ClassForRandomMethods.class");
	
	BMethod testingMethod = myclass.getMethod("testGraphMethod2");
	
	CFGMethod cfg = new CFGMethod(testingMethod);
	
	
}

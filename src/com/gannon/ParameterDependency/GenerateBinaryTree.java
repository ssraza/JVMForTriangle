package com.gannon.ParameterDependency;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeReader.ByteCodeClassGenerator;
import com.gannon.Main.InterfaceAPISingleton;

public class GenerateBinaryTree {
	public static void main(String[] arg) {
		System.out.println("Start of Execution");
		ByteCodeClassGenerator clasGen = new ByteCodeClassGenerator(
				"Hello.class");
		clasGen.cparser();
		BClass loadedClass = clasGen.getbFactory().getBClass();
		InterfaceAPISingleton.getInstance().setbClass(loadedClass);
		
		DependencyGenerator treeGenertor = new DependencyGenerator(loadedClass.getMethods().get(3));
		
		DependencyDataCollector dataCollector = treeGenertor.dependencyTreeGenerator();
		
		System.out.println(dataCollector.getTempVarAndLocalVarTableMapping());
		
		System.out.println(dataCollector.getTempVarAndPCMapping());
		
		for(DependentObject dpendObject : dataCollector.getListOfDependency()){
			System.out.println(dpendObject.getFirstParameter()+"  "+dpendObject.getSecondParameter()+"  "+dpendObject.getFinalResult());
		}

	}
}
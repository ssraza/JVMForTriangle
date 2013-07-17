package com.gannon.Main;

import java.util.ArrayList;

import com.gannon.ASM.BytecodeClassGenerator.BClassGenerator;
import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;

public class InterfaceDisplay {

	private String className;
	private ArrayList<BMethod> bMethod = new ArrayList<BMethod>();
	private static BClassGenerator cParser;
	private BClass bClass;

	public void readFile(String fileName) {
		cParser = new BClassGenerator(fileName);
		cParser.getBClass();
		InterfaceAPISingleton.getInstance().setbClass(
				cParser.getbFactory().getBClass());
	}

	public String getParentName() {

		className = InterfaceAPISingleton.getInstance().getbClass()
				.getClassName();
		return className;
	}

	public ArrayList<BMethod> getBMethods() {
		bMethod = InterfaceAPISingleton.getInstance().getbClass().getMethods();
		return bMethod;
	}
}

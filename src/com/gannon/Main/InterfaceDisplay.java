package com.gannon.Main;

import java.util.ArrayList;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.ASM.BytecodeReader.ByteCodeClassGenerator;

public class InterfaceDisplay {

	private String className;
	private ArrayList<BMethod> bMethod = new ArrayList<BMethod>();
	private static ByteCodeClassGenerator cParser;
	private BClass bClass;

	public void readFile(String fileName) {
		cParser = new ByteCodeClassGenerator(fileName);
		cParser.cparser();
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

package com.gannon.ASM.BytecodeComponent;

import java.util.ArrayList;
import java.util.HashMap;

import org.objectweb.asm.Label;

import com.gannon.Utility.ByteCodeUtility;

/**
 * This class is a part of data structure created to organize the information
 * about the class being visited. This Class will have the information of method
 * being visited and object list of local variables, instrutions, line details,
 * lable deatils, stack details.
 * 
 * 
 * @param name
 *            Name of the Method
 * @param methodAccess
 *            Access variable for Method
 * @param MethodDesc
 *            In parameters and out parameter details.
 * @param instructions
 *            Object list of BInstruction class
 * @param localVariables
 *            Object list of BLocalVariable class
 * @param lineDetails
 *            Object list of BLineNumber class
 * @param blockList
 *            Object list of BLable class
 * @param stackDetails
 *            Object list of BStackMaxLocals class
 * **/
public class BMethod {
	private String name;
	private int methodAccess;
	private String methodDesc;
	private ArrayList<BLocalVariable> localVariables = new ArrayList<BLocalVariable>();
	private ArrayList<BLineNumber> lineDetails = new ArrayList<BLineNumber>();
	private ArrayList<BBlock> blockList = new ArrayList<BBlock>();
	private ArrayList<BStackMaxLocals> stackDetails = new ArrayList<BStackMaxLocals>();
	//public boolean expectedMethod;

	public BMethod() {

	}

	public BMethod(int access, String name, String desc) {
		this.name = name;
		this.methodAccess = access;
		this.methodDesc = desc;
	}

	public String getName() {
		return name;
	}

	public void addLocalVariableTable(BLocalVariable aLocalVariable) {
		localVariables.add(aLocalVariable);
	}

	public void addStackVariable(BStackMaxLocals aStackVariable) {
		stackDetails.add(aStackVariable);
	}

	public void addLine(BLineNumber aLine) {
		lineDetails.add(aLine);
	}

	public void addBlock(BBlock aBlock) {
		blockList.add(aBlock);
	}
	

	public ArrayList<BLocalVariable> getLocalVariables() {
		return localVariables;
	}

	public void setLocalVariables(ArrayList<BLocalVariable> localVariables) {
		this.localVariables = localVariables;
	}

	public ArrayList<BStackMaxLocals> getStackDetails() {
		return stackDetails;
	}

	public ArrayList<BBlock> getBlockList() {
		return blockList;
	}

	public void setBlockList(ArrayList<BBlock> blocks) {
		this.blockList = blocks;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void display() {

		System.out.println("\naccess flag:" + " " + methodAccess);
		System.out.println("Method " + name + " " + methodDesc);

		for (BBlock lbl : blockList) {
			lbl.display();
		}

	}

	
	/*
	public String displayAsString() {
		StringBuilder result = new StringBuilder();
		for (BInstruction intr : instructions) {
			result.append(intr.toString() + "\n");
		}
		return result.toString();

	}

	public String[] getDisplayStrings() {
		ArrayList<String> result = new ArrayList<String>();
		for (BInstruction intr : instructions) {
			result.add(intr.getOpcodeCommand() + "\n");
		}
		return result.toArray(new String[result.size()]);

	}*/

//	public String[] getDisplayByteCodes(){
//		ArrayList<String> result = new ArrayList<String>();
//		ByteCodeUtility utility = new ByteCodeUtility();
//		for(BBlock lbl: blockList){
//			result.add(lbl.getNewLableName());
//			result.addAll(utility.getModifiedLabelBytecodes(lbl.getInstructions()));
//		}
//		int lastIndex = result.size();
//		result.remove(lastIndex-1);
//		return result.toArray(new String[result.size()]);
//	}
	
	public String[] getBytecodesForDisplay(){
		ArrayList<String> result = new ArrayList<String>();
		ByteCodeUtility utility = new ByteCodeUtility();
		HashMap<String, String> labelNameMapping = utility.createLabelMapping(blockList);
		for(BBlock label: blockList){
			result.add(label.getNewLableName());
			result.addAll(utility.getModifiedLabelBytecodes(label.getInstructions(), labelNameMapping));
		}
		int lastIndex = result.size();
		result.remove(lastIndex-1);
		return result.toArray(new String[result.size()]);
	}

	
}

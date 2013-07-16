package com.gannon.ParameterDependency;

import java.util.ArrayList;
import java.util.HashMap;

public class DependencyDataCollector {

	private ArrayList<DependentObject> listOfDependency = new ArrayList<DependentObject>();
	private HashMap<Integer, String> tempVarAndPCMapping = new HashMap<Integer, String>();
	private HashMap<String, Integer> tempVarAndLocalVarTableMapping = new HashMap<String, Integer>();
	
	private int index = 0;

	public void addDependentObject(DependentObject newDependentObject) {
		listOfDependency.add(newDependentObject);
	}

//	public void generateTempLocalMap(int tempVariable) {
//		tempLocalVarMapping.put(index, "i"+tempVariable);
//		index++;
//	}
	
	public void generateTempVarAndLocalVarMapping(String tempVar, Integer localVarIndex){
		tempVarAndLocalVarTableMapping.put(tempVar, localVarIndex);

	}
	
	public void generatePCAndTempVarMapping(Integer PC, String tempVar){
		tempVarAndPCMapping.put(PC, tempVar);
	}

	public ArrayList<DependentObject> getListOfDependency() {
		return listOfDependency;
	}

	public HashMap<Integer, String> getTempVarAndPCMapping() {
		return tempVarAndPCMapping;
	}

	public HashMap<String, Integer> getTempVarAndLocalVarTableMapping() {
		return tempVarAndLocalVarTableMapping;
	}
	
	
}

package com.gannon.rule;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InputObject {
	private HashMap<String,Integer> inputDataMap;
	
	public InputObject(HashMap<String,Integer> data){
		this.inputDataMap = data;
	}

	public HashMap<String, Integer> getInputDataTable() {
		return inputDataMap;
	}

	public void setInputDatTable(HashMap<String, Integer> inputDataTable) {
		this.inputDataMap = inputDataTable;
	}
	
	public void setInputDataByKey(String key, int value){
		this.inputDataMap.put(key, value);
	}
	
	public int getInputDataByKey(String key){
		return this.inputDataMap.get(key);
	}
	
	public boolean isContainsKey(String key){
		boolean flag = false;
		Iterator it = inputDataMap.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry pairs = (Map.Entry)it.next();
	    	String k = pairs.getKey().toString();
	    	if(k.equals(key)){
	    	   return true;
	       }
	    }
	    
	    return flag;
	}
	
	public void printData(){
		Iterator it = inputDataMap.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry pairs = (Map.Entry)it.next();
	        System.out.print(pairs.getKey() + "=" + pairs.getValue()+" ");
	    }
	    System.out.println();
	}
}

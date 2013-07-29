package com.gannon.jvm.progam.path;

import com.gannon.jvm.utilities.TrianglePathBuilderUtility;

public class TestPathGenerator {

	private String className;
	private String methodName;
	
	public TestPathGenerator(String className, String methodName) {
		super();
		this.className = className;
		this.methodName = methodName;
	}
	
	//for testing purpose, need to fix it later
	public  TestPaths deriveTestPaths(){ 
		TestPaths paths=new TestPaths(className,methodName);
		paths.add(TrianglePathBuilderUtility.createPathID1());
		paths.add(TrianglePathBuilderUtility.createPathID2());
		paths.add(TrianglePathBuilderUtility.createPathID3());
		paths.add(TrianglePathBuilderUtility.createPathID4());
		paths.add(TrianglePathBuilderUtility.createPathID5());
		paths.add(TrianglePathBuilderUtility.createPathID6());
		paths.add(TrianglePathBuilderUtility.createPathID7());
		paths.add(TrianglePathBuilderUtility.createPathID8());
		paths.add(TrianglePathBuilderUtility.createPathID9());
		paths.add(TrianglePathBuilderUtility.createPathID10());
		paths.add(TrianglePathBuilderUtility.createPathID11());
		paths.add(TrianglePathBuilderUtility.createPathID12());
		return paths; 
	}
}

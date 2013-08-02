package com.gannon.jvm.progam.path;

import java.util.ArrayList;

public class TestPaths {
	private String className;
	private String methodName;
	private ArrayList<TestPath> paths = new ArrayList<TestPath>();

	public TestPaths() {
		super();
	}

	public TestPaths(String className, String methodName) {
		super();
		this.className = className;
		this.methodName = methodName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public ArrayList<TestPath> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<TestPath> paths) {
		this.paths = paths;
	}

	public void add(TestPath path) {
		paths.add(path);
	}

}

package com.gannon.jvm.data.input;

import java.util.ArrayList;
import java.util.List;

public class InputCollection {
	private final int id;
	private String className;
	private String methodName;
	private List<Input> inputs = new ArrayList<Input>();

	public InputCollection(int id) {
		super();
		this.id = id;
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

	public List<Input> getInputs() {
		return inputs;
	}

	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}

	public int getId() {
		return id;
	}
	
	

	public boolean add(Input e) {
		return inputs.add(e);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + id;
		result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputCollection other = (InputCollection) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (id != other.id)
			return false;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inputs [id=" + id + ", className=" + className + ", methodName=" + methodName + ", inputs=\n" + inputs
				+ "]";
	}

}

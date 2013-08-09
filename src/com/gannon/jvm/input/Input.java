package com.gannon.jvm.input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* One input for a method under the testing, it may contains multiple parameters, 
 * e.g., for method triangleType(a,b,c), a, b, and c are the parameters
 * (a, b, c) is one input
 * */
public class Input {
	private final int id;
	private String className;
	private String methodName;

	private List<Parameter> parameters = new ArrayList<Parameter>();

	public Input(int id) {
		super();
		this.id = id;
	}

	public static Input generateRandom(int id, int numberOfParameters) {
		Input input = new Input(id);
		List<Parameter> ps = input.getParamters();
		for (int i = 0; i < numberOfParameters; i++) {
			ps.add(Parameter.generateRandom(i));
		}
		return input;
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

	public List<Parameter> getParamters() {
		return parameters;
	}

	public void updateParameter(int parameterIndex, Parameter aParameter) {
		for (Parameter p : parameters) {
			if (p.isIndexEqual(aParameter)) {
				p = aParameter;
				break;
			}
		}
	}

	public void setParamters(List<Parameter> paramters) {
		this.parameters = paramters;
	}

	public int getId() {
		return id;
	}

	public boolean add(Parameter e) {
		return parameters.add(e);
	}

	public Parameter get(int index) {
		return parameters.get(index);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public Iterator<Parameter> iterator() {
		return parameters.iterator();
	}

	public int size() {
		return parameters.size();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Input other = (Input) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Input [id=" + id + ", className=" + className + ", methodName=" + methodName + ", paramters="
				+ parameters + "]";
	}

}

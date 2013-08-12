package com.gannon.jvm.data.input;

import java.util.ArrayList;
import java.util.Collection;
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

	public Input(int id, List<Object> oldInput) {
		this(id);
		for (int i = 0; i < oldInput.size(); i++) {
			if (i == 0)
				continue;
			Parameter p = new Parameter(i);
			p.setType(ParameterType.INTEGER);
			p.setValue(oldInput.get(i));
			parameters.add(p);
		}
	}

	public ArrayList<Object> getOldInput() {
		ArrayList<Object> results = new ArrayList<Object>();
		results.add(-1);
		for (Parameter p : parameters) {
			results.add(p.getValue());
		}
		return results;
	}

	public static Input generateRandom(int id, int numberOfParameters) {
		Input input = new Input(id);
		List<Parameter> ps = input.getParamters();
		for (int i = 0; i < numberOfParameters + 1; i++) {
			ps.add(Parameter.generateRandom(i));
		}
		return input;
	}

	public boolean addAll(Collection<? extends Parameter> arg0) {
		return parameters.addAll(arg0);
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

	public Input updateParameterValue(int parameterIndex, Object value) {
		Input result = new Input(1);
		for (Parameter p : parameters) {
			int index = p.getIndex();
			Object pvalue = p.getValue();
			if (index == parameterIndex) {
				pvalue = value;
			}
			Parameter aNewParameter = new Parameter(index);
			aNewParameter.setValue(pvalue);
			result.add(aNewParameter);
		}
		return result;
	}

	public void setParamters(List<Parameter> paramters) {
		this.parameters = paramters;
	}

	public Parameter getParameterByIndex(int parameterIndex) {
		for (Parameter p : parameters) {
			if (p.getIndex() == parameterIndex) {
				return p;
			}
		}
		return null;
	}

	public Parameter getHardCopyParameterByIndex(int parameterIndex) {
		Parameter aNewParameter = new Parameter(parameterIndex);
		for (Parameter p : parameters) {
			if (p.getIndex() == parameterIndex) {
				aNewParameter.setName(p.getName());
				aNewParameter.setType(p.getType());
				aNewParameter.setValue(p.getValue());
				return aNewParameter;
			}
		}
		return null;
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

	public Iterator<Parameter> iterator() {
		return parameters.iterator();
	}

	public int size() {
		return parameters.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
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
		Input other = (Input) obj;
		if (id != other.id)
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else {
			for (int i = 0; i < parameters.size(); i++) {
				if (!parameters.get(i).equals(((Input) obj).getParamters().get(i))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Input [id=" + id + ", className=" + className + ", methodName=" + methodName + ", paramters="
				+ parameters + "]\n";
	}

}

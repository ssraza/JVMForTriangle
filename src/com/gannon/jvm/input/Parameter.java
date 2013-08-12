package com.gannon.jvm.input;

public class Parameter {
	// 1 is the first parameter
	private int index;
	private ParameterType type;
	private String name;//a,b, c
	private Object value;

	public Parameter(int index) {
		super();
		this.index = index;
	}
	
	

	// random generate

	public Parameter(int index, Object value) {
		super();
		this.index = index;
		this.value = value;
	}



	public static Parameter generateRandom(int index) {
		Parameter p = new Parameter(index);

		p.setType(ParameterType.INTEGER);
		p.setName("i" + index);
		p.setValue(Integer.toString(Integer.MIN_VALUE + (int) (Math.random() * Integer.MAX_VALUE)));
		return p;

	}

	public ParameterType getType() {
		return type;
	}

	public void setType(ParameterType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex( int index) {
		this.index = index;
	}
	public boolean isIndexEqual(Parameter aParameter) {
		return index == aParameter.getIndex();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Parameter other = (Parameter) obj;
		if (index != other.index)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parameter [index=" + index + ", type=" + type + ", name=" + name + ", value=" + value + "]";
	}

}

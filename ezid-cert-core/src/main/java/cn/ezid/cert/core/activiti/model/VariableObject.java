package cn.ezid.cert.core.activiti.model;

public class VariableObject {
	public static final String SCOPE_GLOBAL = "global";
	public static final String SCOPE_LOCAL = "local";
	
	private String name;
	private String type;
	private Object value;
	private String scope = SCOPE_GLOBAL;

	public VariableObject() {
		super();
	}

	public VariableObject(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public VariableObject(String name, Object value, String scope) {
		super();
		this.name = name;
		this.value = value;
		this.scope = scope;
	}

	public VariableObject(String name, String type, Object value, String scope) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
		this.scope = scope;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}

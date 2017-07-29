package cn.ezid.cert.manual;

public class ProcessDefinition {

	private String key;
	private String name;

	public ProcessDefinition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProcessDefinition(String key, String name) {
		super();
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

package cn.ezid.cert.core.topo;

public enum ResourceType {
	task_executor(0), engine(1), app(2), manual(3);
	private int code;

	private ResourceType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

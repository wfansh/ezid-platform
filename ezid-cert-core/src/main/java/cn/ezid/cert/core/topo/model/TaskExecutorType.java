package cn.ezid.cert.core.topo.model;

public enum TaskExecutorType {
	preprocess(0), adapter_nciic(1), adapter_loong(2), machine(3), manual(4);
	private int code;

	private TaskExecutorType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

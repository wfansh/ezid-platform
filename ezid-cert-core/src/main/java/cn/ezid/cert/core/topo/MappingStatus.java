package cn.ezid.cert.core.topo;

public enum MappingStatus {
	ON(0), OFF(1), ERROR(2);
	private int code;

	private MappingStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

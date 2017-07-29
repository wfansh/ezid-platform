package cn.ezid.cert.api.model;

import java.util.HashMap;
import java.util.Map;

public class CertResult {
	public enum CertStatus {
		SUCCESS(0), FAIL(1), UNCERTAIN(2), PROCESSING(3), ERROR(4);

		private int code;

		private CertStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	private CertStatus status;
	private String desc;
	private Map<String, Object> props;

	public CertResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CertResult(CertStatus status, String desc) {
		super();
		this.status = status;
		this.desc = desc;
		this.props = new HashMap<>();
	}
	
	public CertResult(CertStatus status, String desc, Map<String, Object> props) {
		super();
		this.status = status;
		this.desc = desc;
		this.props = props;
	}

	public CertStatus getStatus() {
		return status;
	}

	public void setStatus(CertStatus status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Map<String, Object> getProps() {
		return props;
	}

	public void setProps(Map<String, Object> props) {
		this.props = props;
	}

}

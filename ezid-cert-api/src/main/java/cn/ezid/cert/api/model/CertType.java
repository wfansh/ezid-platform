package cn.ezid.cert.api.model;

public enum CertType {
	SocialSec(0), XinhuaCert(1), XinhuaIdcard(2);
	
	private int code;

	private CertType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

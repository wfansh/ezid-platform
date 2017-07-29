package cn.ezid.cert.api;

public class CertException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CertException() {
		
	}
	
	public CertException(Throwable e) {
		super(e);
	}
	
	public CertException(String msg) {
		super(msg);
	}
	
}

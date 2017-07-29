package cn.ezid.cert.core;

/**
 * @author Fan
 *
 */
public class EzidException extends RuntimeException {

	private static final long serialVersionUID = -8542720604494222868L;

	public EzidException() {
		super();
	}

	public EzidException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public EzidException(String message) {
		super(message);
	}

	public EzidException(Throwable throwable) {
		super(throwable);
	}
	
	
	
}

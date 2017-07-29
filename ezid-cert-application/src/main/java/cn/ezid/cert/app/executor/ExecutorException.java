package cn.ezid.cert.app.executor;

import cn.ezid.cert.core.EzidException;

public class ExecutorException extends EzidException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6705498664560182721L;

	public ExecutorException() {
		super();
	}

	public ExecutorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExecutorException(String message) {
		super(message);
	}

	public ExecutorException(Throwable cause) {
		super(cause);
	}

}

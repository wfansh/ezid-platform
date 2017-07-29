package cn.ezid.cert.app.executor.adapter;

import cn.ezid.cert.app.executor.ExecutorException;

public class AdaptException extends ExecutorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8434176181647001387L;
	
	public enum ErrorType {
		NOT_FOUND(0), INCONSISTENT(1), PHOTO_UNFORMAT(2); 
		private int value;
		private ErrorType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	private String title;
	private ErrorType errorType;
	private String message;

	public AdaptException(String title, ErrorType errorType, String message) {
		super();
		this.title = title;
		this.errorType = errorType;
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

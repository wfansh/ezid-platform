package cn.ezid.cert.deploy.landscape;

public class JsonResult {
	private String status;
	private String message;
	private Object data;
	
	public void setSuccess(boolean isSuccess) {
		if (isSuccess) {
			status = "success";
		} else {
			status = "error";
		}
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}

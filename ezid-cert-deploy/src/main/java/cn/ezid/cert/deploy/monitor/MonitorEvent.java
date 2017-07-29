package cn.ezid.cert.deploy.monitor;

import java.util.Date;

import cn.ezid.cert.core.topo.ResourceType;

public class MonitorEvent {
	public static enum AlertLevel {
		Info(0), Warning(1), Error(2);

		private int code;

		private AlertLevel(int code) {
			this.code = code;
		}

		public int getCode() {
			return this.code;
		}
	};


	private ResourceType resourceType;
	private AlertLevel level;
	private String message;
	private Date date = new Date();

	public MonitorEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MonitorEvent(ResourceType resourceType, AlertLevel level, String message) {
		super();
		this.resourceType = resourceType;
		this.level = level;
		this.message = message;
	}

	
	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public AlertLevel getLevel() {
		return level;
	}

	public void setLevel(AlertLevel level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

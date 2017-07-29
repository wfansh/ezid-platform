package cn.ezid.cert.core.topo.model;

import cn.ezid.cert.core.topo.ResourceType;

public class ManualResource extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1048723021149261753L;

	private String resourceName;
	private String url;
	private boolean enabled;

	public ManualResource(long id, String resourceName, String url, boolean enabled) {
		// TODO Auto-generated constructor stub
		super(id, ResourceType.manual);
		this.resourceName = resourceName;
		this.url = url;
		this.enabled = enabled;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

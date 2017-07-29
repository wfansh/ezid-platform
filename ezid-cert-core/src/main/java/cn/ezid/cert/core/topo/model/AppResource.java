package cn.ezid.cert.core.topo.model;

import cn.ezid.cert.core.topo.ResourceType;

public class AppResource extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5314166765049623028L;

	private String resourceName;
	private String url;
	private boolean enabled;

	public AppResource(long id, String resourceName, String url, boolean enabled) {
		super(id, ResourceType.app);
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

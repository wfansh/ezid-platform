package cn.ezid.cert.core.topo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import cn.ezid.cert.core.entity.BaseEntity;

@Alias("LandscapeEntity")
public class LandscapeEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3958972264156319844L;

	private long id;
	private ResourceType resourceType;
	private String resourceName;
	private String url;
	private boolean enabled;
	private Date timeUpdated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
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

	public Date getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(Date timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

}

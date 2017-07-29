package cn.ezid.cert.core.topo.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import cn.ezid.cert.core.topo.ResourceType;

public abstract class Resource implements Serializable {

	private long id;
	private ResourceType resourceType;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5743100934663532310L;

	public Resource(long id, ResourceType resourceType) {
		super();
		this.id = id;
		this.resourceType = resourceType;
	}

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

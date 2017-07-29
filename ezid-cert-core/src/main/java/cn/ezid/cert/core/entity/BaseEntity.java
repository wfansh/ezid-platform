/*
 *****************************************************************************
 ** (C) Copyright 2013, BC Technology, All Rights Reserved.
 ** This software is the proprietary information of BC, Ltd.
 ** Use is subject to license terms.
 *****************************************************************************
 */

package cn.ezid.cert.core.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author fanwan
 *
 */
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4244008389890412338L;

	private Object ext;

	public Object getExt() {
		return ext;
	}

	public void setExt(Object ext) {
		this.ext = ext;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

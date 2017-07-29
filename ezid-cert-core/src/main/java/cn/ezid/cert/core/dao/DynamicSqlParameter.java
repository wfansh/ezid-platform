/*
 *****************************************************************************
 ** (C) Copyright 2013, BC Technology, All Rights Reserved.
 ** This software is the proprietary information of BC, Ltd.
 ** Use is subject to license terms.
 *****************************************************************************
 */
package cn.ezid.cert.core.dao;

import java.util.HashMap;

import org.apache.ibatis.type.Alias;

/**
 * @author fanwan
 *
 */
@Alias("DynamicSqlParameter")
public class DynamicSqlParameter extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6541491929115708891L;
	
	private String sqlId;
	
	public DynamicSqlParameter() {
		super();
	}
	
	public DynamicSqlParameter(String sqlId) {
		super();
		this.sqlId = sqlId;
	}

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
}

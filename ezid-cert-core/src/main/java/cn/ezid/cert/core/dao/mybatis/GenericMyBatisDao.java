/*
 *****************************************************************************
 ** (C) Copyright 2013, BC Technology, All Rights Reserved.
 ** This software is the proprietary information of BC, Ltd.
 ** Use is subject to license terms.
 *****************************************************************************
 */

package cn.ezid.cert.core.dao.mybatis;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.ezid.cert.core.dao.DynamicSqlParameter;
import cn.ezid.cert.core.dao.GenericDao;
import cn.ezid.cert.core.util.EZUtils;


/**
 * @author fanwan
 * 
 * @param <E>
 * @param <PK>
 */
public class GenericMyBatisDao<E, PK extends Serializable> extends
		SqlSessionDaoSupport implements GenericDao<E, PK> {

	public static final String SQLID_INSERT = "insert";
	public static final String SQLID_UPDATE = "update";
	public static final String SQLID_UPDATE_PARAM = "updateParam";
	public static final String SQLID_DELETE = "delete";
	public static final String SQLID_DELETE_PARAM = "deleteParam";
	public static final String SQLID_TRUNCATE = "truncate";
	public static final String SQLID_COUNT = "count";
	public static final String SQLID_COUNT_PARAM = "countParam";
	public static final String SQLID_GET_PK = "getPk";
	public static final String SQLID_GET_PARAM = "getParam";
	public static final String SQLID_SELECT = "select";
	public static final String SQLID_SELECT_PARAM = "selectParam";

	private String namespace;

	@SuppressWarnings("unchecked")
	public GenericMyBatisDao() {
		Type genType = this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		Class<E> clazz = (Class<E>) params[0];
	
		this.setNamespace(clazz.getName());
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void insert(E entity) {
		getSqlSession().insert(namespace + "." + SQLID_INSERT, entity);
	}

	public int update(E entity) {
		return getSqlSession().update(namespace + "." + SQLID_UPDATE, entity);
	}

	public int update(DynamicSqlParameter param) {
		String sqlId = param.getSqlId();
		if (EZUtils.isValidString(sqlId))
			return getSqlSession().update(namespace + "." + sqlId, param);

		return getSqlSession().update(namespace + "." + SQLID_UPDATE_PARAM, param);
	}

	public int delete(PK primaryKey) {
		return getSqlSession().delete(namespace + "." + SQLID_DELETE, primaryKey);
	}

	public int delete(DynamicSqlParameter param) {
		String sqlId = param.getSqlId();
		if (EZUtils.isValidString(sqlId))
			return getSqlSession().delete(namespace + "." + sqlId, param);

		return getSqlSession().delete(namespace + "." + SQLID_DELETE_PARAM, param);
	}

	public int truncate() {
		return getSqlSession().delete(namespace + "." + SQLID_TRUNCATE);
	}

	public int count() {
		Integer count = (Integer) getSqlSession().selectOne(namespace + "." + SQLID_COUNT);
		return count.intValue();
	}

	public int count(DynamicSqlParameter param) {
		Integer count;
		String sqlId = param.getSqlId();
		if (EZUtils.isValidString(sqlId))
			count = getSqlSession().selectOne(namespace + "." + sqlId, param);
		else
			count = getSqlSession().selectOne(namespace + "." + SQLID_COUNT_PARAM, param);

		return count.intValue();
	}

	public E get(PK primaryKey) {
		return getSqlSession().selectOne(namespace + "." + SQLID_GET_PK, primaryKey);
	}

	public E get(DynamicSqlParameter param) {
		String sqlId = param.getSqlId();
		if (EZUtils.isValidString(sqlId))
			return getSqlSession().selectOne(namespace + "." + sqlId, param);

		return getSqlSession().selectOne(namespace + "." + SQLID_GET_PARAM, param);
	}

	public List<E> select() {
		return getSqlSession().selectList(namespace + "." + SQLID_SELECT);
	}

	public List<E> select(DynamicSqlParameter param) {
		String sqlId = param.getSqlId();
		if (EZUtils.isValidString(sqlId))
			return getSqlSession().selectList(namespace + "." + sqlId, param);

		return getSqlSession().selectList(namespace + "." + SQLID_SELECT_PARAM, param);
	}

	public List<E> select(int offset, int limit) {
		RowBounds bound = new RowBounds(offset, limit);
		return getSqlSession().selectList(namespace + "." + SQLID_SELECT, null, bound);
	}

	public List<E> select(DynamicSqlParameter param, int offset, int limit) {
		RowBounds bound = new RowBounds(offset, limit);
		
		String sqlId = param.getSqlId();
		if (EZUtils.isValidString(sqlId))
			return getSqlSession().selectList(namespace + "." + sqlId, param, bound);

		return getSqlSession().selectList(namespace + "." + SQLID_SELECT_PARAM, param, bound);
	}
}
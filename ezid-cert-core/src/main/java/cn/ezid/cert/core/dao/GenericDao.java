/*
 *****************************************************************************
 ** (C) Copyright 2013, BC Technology, All Rights Reserved.
 ** This software is the proprietary information of BC, Ltd.
 ** Use is subject to license terms.
 *****************************************************************************
 */

package cn.ezid.cert.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author fanwan
 *
 * @param <E>
 * @param <PK>
 */
public interface GenericDao<E, PK extends Serializable> {
	/**
	 * insert entity
	 * @param entity
	 */
	public abstract void insert(E entity);
	
	/**
	 * update entity
	 * @param entity
	 * @return the number of updated records
	 */
	public abstract int update(E entity);
	
	/**
	 * update entity according to parameters
	 * @param param
	 * @return the number of updated records
	 */
	public abstract int update(DynamicSqlParameter param);

	/**
	 * delete entity by primary key
	 * @param primaryKey
	 * @return the number of deleted records
	 */
	public abstract int delete(PK primaryKey);
	
	/**
	 * delete entity according to parameters
	 * @param param
	 * @return the number of deleted records
	 */
	public abstract int delete(DynamicSqlParameter param);

	/**
	 * clear table
	 * @return
	 */
	public abstract int truncate();

	/**
	 * query count of records
	 * @return
	 */
	public abstract int count();

	/**
	 * query count of records according to parameters
	 * @param param
	 * @return
	 */
	public abstract int count(DynamicSqlParameter param);

	/**
	 * get record by primary key 
	 * @param primaryKey
	 * @return null if no record
	 */
	public abstract E get(PK primaryKey);
	
	/**
	 * get record according to parameters
	 * @param param
	 * @return null if no record
	 */
	public abstract E get(DynamicSqlParameter param);

	/**
	 * query all records
	 * @return list of records
	 */
	public abstract List<E> select();

	/**
	 * query records according to parameters
	 * @param param
	 * @return list of records
	 */
	public abstract List<E> select(DynamicSqlParameter param);
	
	/**
	 * query a page of records
	 * @param offset
	 * @param limit
	 * @return
	 */
	public abstract List<E> select(int offset, int limit);
	
	/**
	 * query a page of records according to parameters
	 * @param param
	 * @param offset
	 * @param limit
	 * @return
	 */
	public abstract List<E> select(DynamicSqlParameter param, int offset, int limit);
}

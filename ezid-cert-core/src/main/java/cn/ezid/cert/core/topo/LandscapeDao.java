package cn.ezid.cert.core.topo;

import cn.ezid.cert.core.dao.GenericDao;

public interface LandscapeDao extends GenericDao<LandscapeEntity, Long> {
	public static final String SQLID_GET_BY_URL = "getByUrl";
	public static final String SQLID_GET_BY_TYPE = "getByType";
	public static final String SQLID_GET_BY_NAME = "getByName";
	public static final String SQLID_UPDATE_ENABLED = "updateEnabled";
	public static final String SQLID_UPDATE_TIME_UPDATED = "updateTimeUpdated";
}

package cn.ezid.cert.core.topo;

import cn.ezid.cert.core.dao.GenericDao;

public interface TopologyDao extends GenericDao<TopologyEntity, Long> {
	public static final String SQLID_SELECT_BY_MODULEID = "selectByModuleId";
	public static final String SQLID_SELECT_BY_ENGINEID = "selectByEngineId";
	public static final String SQLID_UPDATE_STATUS = "updateStatus";
	public static final String SQLID_UPDATE_TIME_UPDATED = "updateTimeUpdated";
	public static final String SQLID_UPDATE_TIME_UPDATED_BY_MODULEID = "updateTimeUpdatedByModuleId";
	public static final String SQLID_UPDATE_TIME_UPDATED_BY_ENGINEID = "updateTimeUpdatedByEngineId";
	public static final String SQLID_UPDATE_TIME_EXECUTED = "updateTimeExecuted";
	public static final String SQLID_COUNT_MODIFIED_BY_MODULEID = "countModifiedByModuleId";
	public static final String SQLID_COUNT_MODIFIED_BY_ENGINEID = "countModifiedByEngineId";
	
}

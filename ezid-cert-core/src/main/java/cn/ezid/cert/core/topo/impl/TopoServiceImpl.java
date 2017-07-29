package cn.ezid.cert.core.topo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.core.dao.DynamicSqlParameter;
import cn.ezid.cert.core.topo.LandscapeDao;
import cn.ezid.cert.core.topo.LandscapeEntity;
import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.ResourceType;
import cn.ezid.cert.core.topo.TopoService;
import cn.ezid.cert.core.topo.TopologyDao;
import cn.ezid.cert.core.topo.TopologyEntity;

@Service
public class TopoServiceImpl implements TopoService {

	@Autowired
	private LandscapeDao landscapeDao;

	@Autowired
	private TopologyDao topologyDao;

	
	@Override
	public LandscapeEntity getResource(long id) {
		// TODO Auto-generated method stub
		return landscapeDao.get(id);
	}
	
	@Override
	public LandscapeEntity getResourceByName(String resourceName) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter(LandscapeDao.SQLID_GET_BY_NAME);
		param.put("resourceName", resourceName);
		return landscapeDao.get(param);
	}

	@Override
	public List<LandscapeEntity> getAllResources() {
		// TODO Auto-generated method stub
		return landscapeDao.select();
	}

	@Override
	public List<LandscapeEntity> getResourcesByType(ResourceType resourceType) {
		DynamicSqlParameter param = new DynamicSqlParameter(LandscapeDao.SQLID_GET_BY_TYPE);
		param.put("resourceType", resourceType);
		return landscapeDao.select(param);
	}
	
	@Override
	public List<LandscapeEntity> getResourcesByUrl(String url) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter(LandscapeDao.SQLID_GET_BY_URL);
		param.put("url", url);
		return landscapeDao.select(param);
	}
	
	@Override
	public long insertResource(ResourceType resourceType, String resourceName, String url, boolean enabled) {
		// TODO Auto-generated method stub
		LandscapeEntity entity = new LandscapeEntity();
		entity.setResourceType(resourceType);
		entity.setResourceName(resourceName);
		entity.setUrl(url);
		entity.setEnabled(enabled);
		
		landscapeDao.insert(entity);
		
		return entity.getId();
	}
	
	@Override
	public void deleteResource(long id) {
		// TODO Auto-generated method stub
		LandscapeEntity entity = this.getResource(id);
		landscapeDao.delete(id);
		
		/**
		 *  Force reload of Landscape resources
		 */
		if (entity != null) {
			switch (entity.getResourceType()) {
			case app:
			case manual:
				this.updateMappingUpdTimeByModuleId(entity.getId());
				break;
			case engine:
				this.updateMappingUpdTimeByEngineId(entity.getId());
				break;
			default:
			}
		}
	}

	@Override
	public void enableResource(long id, boolean enabled) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter(LandscapeDao.SQLID_UPDATE_ENABLED);
		param.put("id", id);
		param.put("enabled", enabled);
		landscapeDao.update(param);
		
		this.updateResourceUpdTime(id);
	}

	@Override
	public void updateResourceUpdTime(long id) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter(LandscapeDao.SQLID_UPDATE_TIME_UPDATED);
		param.put("id", id);
		landscapeDao.update(param);
	}
	
	@Override
	public boolean isResourceModified(long id, Date lastUpdate) {
		LandscapeEntity entity = this.getResource(id);
		if (entity == null)
			return true;
		
		if (lastUpdate == null)
			return true;
		
		if (entity.getTimeUpdated() == null)
			return true;
		
		return entity.getTimeUpdated().after(lastUpdate);
	}


	
	/*
	 * ***********************************************************************************************
	 * ***********************************************************************************************
	 * ***********************************************************************************************
	 */
	
	
	@Override
	public TopologyEntity getMapping(long id) {
		// TODO Auto-generated method stub
		return topologyDao.get(id);
	}

	@Override
	public List<TopologyEntity> getAllMappings() {
		// TODO Auto-generated method stub
		return topologyDao.select();
	}
	
	@Override
	public List<TopologyEntity> getMappingsByModuleId(long moduleId) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter(TopologyDao.SQLID_SELECT_BY_MODULEID);
		param.put("moduleId", moduleId);
		return topologyDao.select(param);
	}
	
	public List<TopologyEntity> getMappingsByEngineId(long engineId) {
		DynamicSqlParameter param = new DynamicSqlParameter(TopologyDao.SQLID_SELECT_BY_ENGINEID);
		param.put("engineId", engineId);
		return topologyDao.select(param);
	}

	
	@Override
	public long insertMapping(long moduleId, long engineId, long taskExecutorId, String userName, String password,
			MappingStatus status) {
		// TODO Auto-generated method stub
		TopologyEntity entity = new TopologyEntity();
		entity.setModuleId(moduleId);
		entity.setEngineId(engineId);
		entity.setTaskExecutorId(taskExecutorId);
		entity.setUserName(userName);
		entity.setPassword(password);
		entity.setStatus(status);
		
		topologyDao.insert(entity);
		
		/**
		 *  Force reload of Landscape resources
		 */
		this.updateResourceUpdTime(moduleId);
		this.updateResourceUpdTime(engineId);
		
		return entity.getId();
	}

	@Override
	public void deleteMapping(long id) {
		// TODO Auto-generated method stub
		TopologyEntity entity = this.getMapping(id);
		topologyDao.delete(id);
		
		/**
		 *  Force reload of Landscape resources
		 */
		if (entity != null) {
			LandscapeEntity resource = this.getResource(entity.getModuleId());
			if (resource != null)
				this.updateResourceUpdTime(resource.getId());
			
			resource = this.getResource(entity.getEngineId());
			if (resource != null)
				this.updateResourceUpdTime(resource.getId());
		}
	}

	@Override
	public void updateMappingStatus(long id, MappingStatus status, String description) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter(TopologyDao.SQLID_UPDATE_STATUS);
		param.put("id", id);
		param.put("status", status);
		param.put("description", description);
		topologyDao.update(param);
		
		this.updateMappingUpdTime(id);
	}
	

	@Override
	public void updateMappingUpdTime(long id) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter(TopologyDao.SQLID_UPDATE_TIME_UPDATED);
		param.put("id", id);
		topologyDao.update(param);
	}

	@Override
	public void updateMappingUpdTimeByModuleId(long moduleId) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter(TopologyDao.SQLID_UPDATE_TIME_UPDATED_BY_MODULEID);
		param.put("moduleId", moduleId);
		topologyDao.update(param);

	}
	
	@Override
	public void updateMappingUpdTimeByEngineId(long engineId) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter(TopologyDao.SQLID_UPDATE_TIME_UPDATED_BY_ENGINEID);
		param.put("engineId", engineId);
		topologyDao.update(param);

	}

	@Override
	public void updateMappingExecutedTime(long id) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter(TopologyDao.SQLID_UPDATE_TIME_EXECUTED);
		param.put("id", id);
		topologyDao.update(param);
	}

	@Override
	public int countModifiedMappingByModuleId(long moduleId, Date lastUpdate) {
		// TODO Auto-generated method stub
		if (lastUpdate == null)
			return this.getMappingsByModuleId(moduleId).size();
		
		DynamicSqlParameter param = new DynamicSqlParameter(TopologyDao.SQLID_COUNT_MODIFIED_BY_MODULEID);
		param.put("moduleId", moduleId);
		param.put("lastUpdate", lastUpdate);
		return topologyDao.count(param);
	}

	@Override
	public int countModifiedMappingByEngineId(long engineId, Date lastUpdate) {
		// TODO Auto-generated method stub
		if (lastUpdate == null)
			return this.getMappingsByEngineId(engineId).size();
		
		DynamicSqlParameter param = new DynamicSqlParameter(TopologyDao.SQLID_COUNT_MODIFIED_BY_ENGINEID);
		param.put("engineId", engineId);
		param.put("lastUpdate", lastUpdate);
		return topologyDao.count(param);
	}

}

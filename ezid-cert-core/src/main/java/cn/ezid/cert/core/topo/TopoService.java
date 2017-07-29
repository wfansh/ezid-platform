package cn.ezid.cert.core.topo;

import java.util.Date;
import java.util.List;

public interface TopoService {

	public LandscapeEntity getResource(long id);
	
	public LandscapeEntity getResourceByName(String resourceName);

	public List<LandscapeEntity> getAllResources();

	public List<LandscapeEntity> getResourcesByType(ResourceType resourceType);
	
	public List<LandscapeEntity> getResourcesByUrl(String url);

	public long insertResource(ResourceType resourceType, String resourceName, String url, boolean enabled);

	public void deleteResource(long id);

	public void enableResource(long id, boolean enabled);

	public void updateResourceUpdTime(long id);
	
	public boolean isResourceModified(long id, Date lastUpdate);

	/*
	 * ***********************************************************************************************
	 * ***********************************************************************************************
	 * ***********************************************************************************************
	 */
	public TopologyEntity getMapping(long id);

	public List<TopologyEntity> getAllMappings();

	public List<TopologyEntity> getMappingsByModuleId(long moduleId);

	public List<TopologyEntity> getMappingsByEngineId(long engineId);

	public long insertMapping(long moduleId, long engineId, long taskExecutorId, String userName, String password,
			MappingStatus status);

	public void deleteMapping(long id);

	public void updateMappingStatus(long id, MappingStatus status, String description);

	public void updateMappingUpdTime(long id);

	public void updateMappingUpdTimeByModuleId(long moduleId);

	public void updateMappingUpdTimeByEngineId(long engineId);

	public void updateMappingExecutedTime(long id);
	
	public int countModifiedMappingByModuleId(long moduleId, Date lastUpdate);
	
	public int countModifiedMappingByEngineId(long engineId, Date lastUpdate);
	
}

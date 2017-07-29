package cn.ezid.cert.core.topo;

import java.util.List;

public interface DeployTopoFacade {

	public LandscapeEntity getResource(long id);
	
	public LandscapeEntity getResourceByName(String resourceName);
	
	public List<LandscapeEntity> getAllResources();

	public List<LandscapeEntity> getResourcesByType(ResourceType resourceType);
	
	public List<LandscapeEntity> getSubResourcesByType(long engineId, ResourceType resourceType);
	
	public long insertResource(ResourceType resourceType, String resourceName, String url, boolean enabled);	
	
	public void deleteResource(long id);

	public void enableResource(long id, boolean enabled);

	public void updateResourceUpdTime(long id);
	
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

}

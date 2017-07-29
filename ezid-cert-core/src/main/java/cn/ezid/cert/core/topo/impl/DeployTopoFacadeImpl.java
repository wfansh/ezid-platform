package cn.ezid.cert.core.topo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.core.topo.DeployTopoFacade;
import cn.ezid.cert.core.topo.LandscapeEntity;
import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.ResourceType;
import cn.ezid.cert.core.topo.TopoService;
import cn.ezid.cert.core.topo.TopologyEntity;

@Service
public class DeployTopoFacadeImpl implements DeployTopoFacade {

	@Autowired
	private TopoService topoService;

	@Override
	public LandscapeEntity getResource(long id) {
		// TODO Auto-generated method stub
		return topoService.getResource(id);
	}
	
	@Override
	public LandscapeEntity getResourceByName(String resourceName) {
		// TODO Auto-generated method stub
		return topoService.getResourceByName(resourceName);
	}

	@Override
	public List<LandscapeEntity> getAllResources() {
		// TODO Auto-generated method stub
		return topoService.getAllResources();
	}

	@Override
	public List<LandscapeEntity> getResourcesByType(ResourceType resourceType) {
		return topoService.getResourcesByType(resourceType);
	}

	@Override
	public List<LandscapeEntity> getSubResourcesByType(long engineId, ResourceType resourceType) {
		Map<Long, LandscapeEntity> resources = new HashMap<>();
		
		List<TopologyEntity> mappings = this.getMappingsByEngineId(engineId);
		for (TopologyEntity mapping : mappings) {
			LandscapeEntity resource = this.getResource(mapping.getModuleId());
			if (resource.getResourceType() == resourceType) {
				resources.put(mapping.getModuleId(), resource);
			}
		}
		return new ArrayList<>(resources.values());
	}

	@Override
	public long insertResource(ResourceType resourceType, String resourceName, String url, boolean enabled) {
		// TODO Auto-generated method stub
		return topoService.insertResource(resourceType, resourceName, url, enabled);
	}

	@Override
	public void deleteResource(long id) {
		// TODO Auto-generated method stub
		topoService.deleteResource(id);
	}

	@Override
	public void enableResource(long id, boolean enabled) {
		// TODO Auto-generated method stub
		topoService.enableResource(id, enabled);
	}

	@Override
	public void updateResourceUpdTime(long id) {
		// TODO Auto-generated method stub
		topoService.updateResourceUpdTime(id);
	}

	@Override
	public TopologyEntity getMapping(long id) {
		// TODO Auto-generated method stub
		return topoService.getMapping(id);
	}

	@Override
	public List<TopologyEntity> getAllMappings() {
		// TODO Auto-generated method stub
		return topoService.getAllMappings();
	}

	@Override
	public List<TopologyEntity> getMappingsByModuleId(long moduleId) {
		// TODO Auto-generated method stub
		return topoService.getMappingsByModuleId(moduleId);
	}

	@Override
	public List<TopologyEntity> getMappingsByEngineId(long engineId) {
		// TODO Auto-generated method stub
		return topoService.getMappingsByEngineId(engineId);
	}

	@Override
	public long insertMapping(long moduleId, long engineId, long taskExecutorId, String userName, String password,
			MappingStatus status) {
		// TODO Auto-generated method stub
		return topoService.insertMapping(moduleId, engineId, taskExecutorId, userName, password, status);
	}

	@Override
	public void deleteMapping(long id) {
		// TODO Auto-generated method stub
		topoService.deleteMapping(id);
	}

	@Override
	public void updateMappingStatus(long id, MappingStatus status, String description) {
		// TODO Auto-generated method stub
		topoService.updateMappingStatus(id, status, description);
	}

	@Override
	public void updateMappingUpdTime(long id) {
		// TODO Auto-generated method stub
		topoService.updateMappingUpdTime(id);
	}
}

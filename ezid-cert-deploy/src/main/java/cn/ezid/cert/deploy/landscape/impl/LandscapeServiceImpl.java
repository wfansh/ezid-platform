package cn.ezid.cert.deploy.landscape.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.core.NLSSupport;
import cn.ezid.cert.core.topo.DeployTopoFacade;
import cn.ezid.cert.core.topo.LandscapeEntity;
import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.ResourceType;
import cn.ezid.cert.core.topo.TopologyEntity;
import cn.ezid.cert.deploy.landscape.LandscapeService;
import cn.ezid.cert.deploy.landscape.LandscapeViewBean;

@Service
public class LandscapeServiceImpl implements LandscapeService {

	@Autowired
	private DeployTopoFacade topoFacade;

	private Map<Long, Long> resourceIdMap = new HashMap<Long, Long>();

	@Override
	public List<LandscapeViewBean> getAllResources() {
		List<LandscapeViewBean> landscapeList = new ArrayList<LandscapeViewBean>();
		List<LandscapeEntity> moduleList = new ArrayList<LandscapeEntity>();
		List<LandscapeEntity> manualList;
		List<LandscapeEntity> appList;
		List<LandscapeEntity> engineList = topoFacade.getResourcesByType(ResourceType.engine);
		for (LandscapeEntity engine : engineList) {
			List<LandscapeViewBean> subLandscapeList = new ArrayList<LandscapeViewBean>();
			manualList = topoFacade.getSubResourcesByType(engine.getId(), ResourceType.manual);
			appList = topoFacade.getSubResourcesByType(engine.getId(), ResourceType.app);
			moduleList.addAll(manualList);
			moduleList.addAll(appList);
			for (LandscapeEntity module : moduleList) {
				List<LandscapeViewBean> taskExecutorLandscape = new ArrayList<LandscapeViewBean>();
				List<TopologyEntity> moduleMappings = topoFacade.getMappingsByModuleId(module.getId());
				for (TopologyEntity moduleMapping : moduleMappings) {
					LandscapeEntity taskExecutor = topoFacade.getResource(moduleMapping.getTaskExecutorId());
					taskExecutorLandscape.add(getViewBean(moduleMapping, taskExecutor, null));
				}
				subLandscapeList.add(getViewBean(null, module, taskExecutorLandscape));
			}
			landscapeList.add(getViewBean(null, engine, subLandscapeList));
			moduleList.clear();
		}
		return landscapeList;
	}

	private LandscapeViewBean getViewBean(TopologyEntity topologyEntity, LandscapeEntity landscapeEntity,
			List<LandscapeViewBean> subLandscapeList) {
		LandscapeViewBean viewBean = new LandscapeViewBean();
		ResourceType resourceType = landscapeEntity.getResourceType();
		if (topologyEntity != null) {
			viewBean.setId(topologyEntity.getId());
			viewBean.setModuleId(topologyEntity.getModuleId());
			viewBean.setEngineId(topologyEntity.getEngineId());
			viewBean.setTaskExecutorId(topologyEntity.getTaskExecutorId());
			viewBean.setUserName(topologyEntity.getUserName());
			viewBean.setPassword(topologyEntity.getPassword());
			viewBean.setStatus(topologyEntity.getStatus());
			viewBean.setDescription(topologyEntity.getDescription());
			viewBean.setTimeExecuted(topologyEntity.getTimeExecuted());
			viewBean.setTimeUpdated(topologyEntity.getTimeUpdated());
		}
		viewBean.setResourceType(resourceType);
		viewBean.setResourceName(landscapeEntity.getResourceName());
		viewBean.setUrl(landscapeEntity.getUrl());
		viewBean.setEnabled(landscapeEntity.isEnabled());
		if (subLandscapeList != null) {
			if (subLandscapeList.size() > 0) {
				viewBean.setSubLandscapeViews(subLandscapeList);
			}
		}
		if (resourceType == ResourceType.engine) {
			viewBean.setEngineId(landscapeEntity.getId());
		} else if (resourceType == ResourceType.manual || resourceType == ResourceType.app) {
			viewBean.setModuleId(landscapeEntity.getId());
		}
		return viewBean;
	}

	@Override
	public List<LandscapeEntity> getTaskExecutorsByModuleType(ResourceType resourceType) {
		List<LandscapeEntity> landscapeList = topoFacade.getResourcesByType(ResourceType.task_executor);
		List<LandscapeEntity> taskExecutorAppList = new ArrayList<LandscapeEntity>();
		List<LandscapeEntity> taskExecutorManualList = new ArrayList<LandscapeEntity>();
		for (LandscapeEntity landscapeEntity : landscapeList) {
			if ("manual".equals(landscapeEntity.getResourceName())) {
				taskExecutorManualList.add(landscapeEntity);
			} else {
				taskExecutorAppList.add(landscapeEntity);
			}
		}
		return resourceType == ResourceType.app ? taskExecutorAppList : taskExecutorManualList;
	}

	@Override
	public String deleteResource(ResourceType resourceType, long id) {
		List<TopologyEntity> resourceMappingList = new ArrayList<TopologyEntity>();
		List<TopologyEntity> moduleMappingList = new ArrayList<TopologyEntity>();
		if (resourceType == ResourceType.task_executor) {
			TopologyEntity resourceMapping = topoFacade.getMapping(id);
			if (resourceMapping == null) {
				return NLSSupport.getMessage("Landscape.Resource.MappingUnexisted");
			}
			resourceMappingList.add(resourceMapping);
			if (!isStatusOff(resourceMappingList)) {
				return NLSSupport.getMessage("Landscape.TaskExecutor.OFF");
			}
			moduleMappingList = topoFacade.getMappingsByModuleId(resourceMapping.getModuleId());
			if (moduleMappingList.size() > 1) {
				topoFacade.deleteMapping(id);
			} else {
				topoFacade.deleteMapping(id);
				topoFacade.deleteResource(moduleMappingList.get(0).getModuleId());
			}

		}

		if (resourceType == ResourceType.manual || resourceType == ResourceType.app) {
			resourceMappingList = topoFacade.getMappingsByModuleId(id);
			if (resourceMappingList.size() == 0) {
				return NLSSupport.getMessage("Landscape.Resource.MappingUnexisted");
			}
			if (!isStatusOff(resourceMappingList)) {
				return NLSSupport.getMessage("Landscape.TaskExecutor.OFF");
			}
			for (TopologyEntity resourceMapping : resourceMappingList) {
				topoFacade.deleteMapping(resourceMapping.getId());
			}
			topoFacade.deleteResource(id);
		}

		if (resourceType == ResourceType.engine) {
			if (topoFacade.getResource(id) == null) {
				return NLSSupport.getMessage("Landscape.Resource.MappingUnexisted");
			}
			resourceMappingList = topoFacade.getMappingsByEngineId(id);
			if (!isStatusOff(resourceMappingList)) {
				return NLSSupport.getMessage("Landscape.TaskExecutor.OFF");
			}
			for (TopologyEntity resourceMapping : resourceMappingList) {
				topoFacade.deleteMapping(resourceMapping.getId());
			}
			for (long moduleId : resourceIdMap.keySet()) {
				topoFacade.deleteResource(moduleId);
			}
			topoFacade.deleteResource(id);
		}
		return "";

	}

	private boolean isStatusOff(List<TopologyEntity> resourceMappingList) {
		resourceIdMap.clear();
		for (TopologyEntity resourceMapping : resourceMappingList) {
			if (resourceMapping.getStatus() == MappingStatus.ON || resourceMapping.getStatus() == MappingStatus.ERROR) {
				return false;
			}
			resourceIdMap.put(resourceMapping.getModuleId(), resourceMapping.getEngineId());
		}
		return true;
	}

	@Override
	public void updateMappingStatus(long id, MappingStatus status) {
		String description = topoFacade.getMapping(id).getDescription();
		topoFacade.updateMappingStatus(id, status, description);
	}

	@Override
	public String createResource(LandscapeViewBean viewBean) {
		List<Long> executorIdList = new ArrayList<Long>();
		List<Long> appExecutorIdList = new ArrayList<Long>();
		List<Long> manualExecutorIdList = new ArrayList<Long>();
		ResourceType resourceType = viewBean.getResourceType();
		String resourceName = viewBean.getResourceName() == null ? null : viewBean.getResourceName().trim();
		String url = viewBean.getUrl() == null ? null : viewBean.getUrl().trim();
		long moduleId = viewBean.getModuleId();
		long engineId = viewBean.getEngineId();
		long taskExecutorId = viewBean.getTaskExecutorId();
		String userName = viewBean.getUserName() == null ? null : viewBean.getUserName().trim();
		String password = viewBean.getPassword() == null ? null : viewBean.getPassword().trim();

		if (resourceType == ResourceType.engine) {
			if (topoFacade.getResourceByName(resourceName) != null) {
				return NLSSupport.getMessage("Landscape.Resource.NameExisted");
			}
			topoFacade.insertResource(resourceType, resourceName, url, true);
		}

		if (resourceType == ResourceType.app || resourceType == ResourceType.manual) {
			if (topoFacade.getResourceByName(resourceName) != null) {
				return NLSSupport.getMessage("Landscape.Resource.NameExisted");
			}
			List<LandscapeEntity> taskExecutorList = topoFacade.getResourcesByType(ResourceType.task_executor);
			for (LandscapeEntity taskExecutor : taskExecutorList) {
				if ("manual".equals(taskExecutor.getResourceName())) {
					manualExecutorIdList.add(taskExecutor.getId());
				} else {
					appExecutorIdList.add(taskExecutor.getId());
				}
			}
			if (resourceType == ResourceType.manual) {
				executorIdList = manualExecutorIdList;
				userName = null;
				password = null;
			} else {
				executorIdList = appExecutorIdList;
			}
			moduleId = topoFacade.insertResource(resourceType, resourceName, url, true);
			for (long executorId : executorIdList) {
				topoFacade.insertMapping(moduleId, engineId, executorId, userName, password, MappingStatus.OFF);
			}
		}

		if (resourceType == ResourceType.task_executor) {
			List<TopologyEntity> resourceMappingList = topoFacade.getMappingsByModuleId(moduleId);
			for (TopologyEntity resourceMapping : resourceMappingList) {
				if (resourceMapping.getTaskExecutorId() == taskExecutorId) {
					return NLSSupport.getMessage("Landscape.Module.TaskExecutorExisted");
				}
			}
			topoFacade.insertMapping(moduleId, engineId, taskExecutorId, userName, password, MappingStatus.OFF);
		}
		return "";
	}

}

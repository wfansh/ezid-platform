package cn.ezid.cert.deploy.landscape;

import java.util.List;

import cn.ezid.cert.core.topo.LandscapeEntity;
import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.ResourceType;

public interface LandscapeService {
	public List<LandscapeViewBean> getAllResources();

	public List<LandscapeEntity> getTaskExecutorsByModuleType(ResourceType resourceType);

	public String deleteResource(ResourceType resourceType, long id);

	public void updateMappingStatus(long id, MappingStatus status);

	public String createResource(LandscapeViewBean viewBean);
}

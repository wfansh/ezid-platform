package cn.ezid.cert.core.topo;

import java.util.Map;

import cn.ezid.cert.core.topo.model.AppResource;
import cn.ezid.cert.core.topo.model.TaskExecutorType;
import cn.ezid.cert.core.topo.model.TopoMapping;

public interface AppTopoFacade {

	public boolean isSync();
	
	public void loadTopo();
	
	public AppResource getCurrentApp();
	
	public Map<TaskExecutorType, TopoMapping> getServedMappings();
	
	public void updateStatus(TaskExecutorType taskExecutorType, MappingStatus status, String description);
	
	public void updateExecutedTime(TaskExecutorType taskExecutorType);
}
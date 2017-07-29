package cn.ezid.cert.core.topo;

import cn.ezid.cert.core.topo.model.ManualResource;
import cn.ezid.cert.core.topo.model.TopoMapping;

public interface ManualTopoFacade {
	public boolean isSync();
	
	public void loadTopo();
	
	public ManualResource getCurrentManual();
	
	public TopoMapping getServedMapping();
	
	public void updateStatus(MappingStatus status, String description);
}

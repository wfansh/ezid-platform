package cn.ezid.cert.app.executor.adapter.loong;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ezid.cert.app.executor.adapter.AbstractAdapterExecutor;
import cn.ezid.cert.app.executor.adapter.AdapterService;
import cn.ezid.cert.core.activiti.TaskConstants;
import cn.ezid.cert.core.topo.model.TaskExecutorType;

public class AdapterLoongExecutor extends AbstractAdapterExecutor {
	
	@Autowired
	private AdapterServiceLoongImpl adapterService;
	
	public AdapterLoongExecutor() {
		super(TaskExecutorType.adapter_loong, TaskConstants.GROUP_PHOTO_ADAPTER);
	}

	@Override
	protected AdapterService getAdapterService() {
		// TODO Auto-generated method stub
		return adapterService;
	}
}

package cn.ezid.cert.app.executor.adapter.nciic;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ezid.cert.app.executor.adapter.AbstractAdapterExecutor;
import cn.ezid.cert.app.executor.adapter.AdapterService;
import cn.ezid.cert.core.activiti.TaskConstants;
import cn.ezid.cert.core.topo.model.TaskExecutorType;

public class AdapterNciicExecutor extends AbstractAdapterExecutor {

	@Autowired
	private AdapterServiceNciicImpl adapterService;

	public AdapterNciicExecutor() {
		super(TaskExecutorType.adapter_nciic, TaskConstants.GROUP_PHOTO_ADAPTER);
	}

	@Override
	protected AdapterService getAdapterService() {
		// TODO Auto-generated method stub
		return adapterService;
	}

}

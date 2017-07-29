package cn.ezid.cert.app.executor;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.core.topo.AppTopoFacade;
import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.model.AppResource;
import cn.ezid.cert.core.topo.model.TaskExecutorType;
import cn.ezid.cert.core.topo.model.TopoMapping;

@Service
public class ExecutorContextImpl implements ExecutorContext {

	private static final Logger log = LoggerFactory.getLogger(ExecutorContextImpl.class);

	@Autowired
	private AppTopoFacade topoFacade;

	@Autowired
	private List<AbstractTaskExecutor> executors;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.ezid.cert.app.executor.ExecutorContext#sync()
	 */
	@Override
	public void sync() {
		if (!topoFacade.isSync()) {
			topoFacade.loadTopo();
			for (AbstractTaskExecutor executor : executors)
				executor.reinitialize();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.ezid.cert.app.executor.ExecutorContext#stopExecutorWithError(cn.ezid.cert.core.topo.model.TaskExecutorType,
	 * java.lang.String)
	 */
	@Override
	public void stopExecutorWithError(TaskExecutorType taskExecutorType, String error) {
		log.info("Executor {} is stopped with error {}", taskExecutorType, error);
		topoFacade.updateStatus(taskExecutorType, MappingStatus.ERROR, error);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.ezid.cert.app.executor.ExecutorContext#updateLastExecuteTime(cn.ezid.cert.core.topo.model.TaskExecutorType)
	 */
	@Override
	public void updateLastExecuteTime(TaskExecutorType taskExecutorType) {
		topoFacade.updateExecutedTime(taskExecutorType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.ezid.cert.app.executor.ExecutorContext#isExecutorEnabled(cn.ezid.cert.core.topo.model.TaskExecutorType)
	 */
	@Override
	public boolean isExecutorEnabled(TaskExecutorType taskExecutorType) {
		AppResource current = topoFacade.getCurrentApp();
		if (current == null || !current.isEnabled())
			return false;

		Map<TaskExecutorType, TopoMapping> servedMappings = topoFacade.getServedMappings();
		if (servedMappings == null || !servedMappings.containsKey(taskExecutorType))
			return false;

		TopoMapping servedMapping = servedMappings.get(taskExecutorType);
		if (servedMapping.getStatus() != MappingStatus.ON || servedMapping.getEngine() == null
				|| !servedMapping.getEngine().isEnabled())
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.ezid.cert.app.executor.ExecutorContext#getExecutorConfig(cn.ezid.cert.core.topo.model.TaskExecutorType)
	 */
	@Override
	public TopoMapping getExecutorConfig(TaskExecutorType taskExecutorType) {
		Map<TaskExecutorType, TopoMapping> serverMapping = topoFacade.getServedMappings();
		if (serverMapping == null) {
			return null;
		}
		return serverMapping.get(taskExecutorType);
	}
}

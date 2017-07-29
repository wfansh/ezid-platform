package cn.ezid.cert.app.executor;

import cn.ezid.cert.core.topo.model.TaskExecutorType;
import cn.ezid.cert.core.topo.model.TopoMapping;

public interface ExecutorContext {

	public abstract void sync();

	public abstract void stopExecutorWithError(TaskExecutorType taskExecutorType, String error);

	public abstract void updateLastExecuteTime(TaskExecutorType taskExecutorType);

	public abstract boolean isExecutorEnabled(TaskExecutorType taskExecutorType);
	
	public abstract TopoMapping getExecutorConfig(TaskExecutorType taskExecutorType);
}
package cn.ezid.cert.core.topo.model;

import cn.ezid.cert.core.topo.ResourceType;

public class TaskExecutorResource extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5607188137333503921L;

	private TaskExecutorType taskExecutorType;


	public TaskExecutorResource(long id, String resourceName) {
		super(id, ResourceType.task_executor);
		taskExecutorType = TaskExecutorType.valueOf(resourceName);
	}

	public TaskExecutorType getTaskExecutorType() {
		return taskExecutorType;
	}

	public void setTaskExecutorType(TaskExecutorType taskExecutorType) {
		this.taskExecutorType = taskExecutorType;
	}

}

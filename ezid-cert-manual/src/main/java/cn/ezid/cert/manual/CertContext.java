package cn.ezid.cert.manual;

import java.util.Map;

import cn.ezid.cert.core.activiti.TaskClient;
import cn.ezid.cert.core.topo.model.TaskExecutorType;
import cn.ezid.cert.core.topo.model.TopoMapping;

public interface CertContext {
	public static final String SESSION_ATTR_TASK_CLIENT = "taskClient";
	public static final String SESSION_ATTR_PROCESS_DEFINITION = "processDefinition";

	/**
	 * *******************************************************
	 * Topology and landscape related methods
	 * 
	 * *******************************************************
	 */
	public abstract void sync();

	public abstract TopoMapping getConfig();

	public abstract void stopWithError(String error);

	public abstract boolean isEnabled();
	
	public TaskExecutorType getExecutorType();

	
	/**
	 * *******************************************************
	 * Cert client methods
	 * 
	 * *******************************************************
	 */
	public abstract TaskClient getTaskClient(String username, String password);
	
	public abstract TaskClient getTaskClient();
	
	
	/**
	 * *******************************************************
	 * Process definition methods
	 * 
	 * *******************************************************
	 */
	public Map<String, ProcessDefinition> getProcessDefinitions();
	
	public ProcessDefinition getProcessDefinition(String key);
	
	public void setProcessDefinition(String key);

	public ProcessDefinition getProcessDefinition();

}

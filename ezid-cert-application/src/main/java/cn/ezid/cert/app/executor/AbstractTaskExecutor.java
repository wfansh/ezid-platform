package cn.ezid.cert.app.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ezid.cert.core.activiti.TaskClient;
import cn.ezid.cert.core.activiti.TaskConstants;
import cn.ezid.cert.core.activiti.model.TaskListObject;
import cn.ezid.cert.core.activiti.model.TaskObject;
import cn.ezid.cert.core.activiti.model.VariableObject;
import cn.ezid.cert.core.topo.model.TaskExecutorType;
import cn.ezid.cert.core.topo.model.TopoMapping;


public abstract class AbstractTaskExecutor {
	private static final Logger log = LoggerFactory.getLogger(AbstractTaskExecutor.class);
	
	// Constant information
	private final TaskExecutorType executorType;
	private final String candidateGroup;
	
	
	// Configurable information
	private String engineUrl;
	private String userName;
	private String password;
	
	protected TaskClient taskClient;
	
	@Autowired
	private ExecutorContext executorContext;	
	
	private boolean initialized = false;

	public AbstractTaskExecutor(TaskExecutorType executorType, String candidateGroup) {
		this.executorType = executorType;
		this.candidateGroup = candidateGroup;
	}

	public abstract void executeTask(TaskObject task) throws ExecutorException;
	
	public void execute() {
		if (!isExecutorEnabled())
			return;
		
		log.debug("Executor {} run.", this.getExecutorType());
		
		if (!initialized) {
			initialize();
			if (taskClient == null) {
				log.error("Can't load correct configure.");
				this.stopExecutor("Read executor configure failed.");
				this.updateLastExecuteTime();
				return;
			}
		}
		
		List<TaskObject> tasks = null;
		try {
			tasks = this.getAssignedTasks();
		} catch (Exception e) {
			this.stopExecutor("Can't get assigned tasks from engine " + engineUrl + " error:" + e.getMessage());
			this.updateLastExecuteTime();
			return;
		}
		
		if (tasks == null) {
			this.stopExecutor("Can't get assigned tasks from engine " + engineUrl + ". Result is null.");
			this.updateLastExecuteTime();
			return;
		}
		
		for (TaskObject task: tasks) {
			log.info("Start executing task {}.", task.getId());
			try {
				claimTask(task);
			} catch(Exception e) {
				if (e.getMessage().contains("409 Conflict") || e.getMessage().contains("404 Not Found")) {
					continue;
				} else {
					log.warn(e.getMessage());
					continue;
				}
			}
			
			try {
				executeTask(task);
			} catch (Throwable e) {
				log.warn(e.getMessage());
				this.unClaimTask(task);
				this.stopExecutor(e.getMessage());
				break;
			}
		}
		
		this.updateLastExecuteTime();
	}

	private void initialize() {
		TopoMapping config = executorContext.getExecutorConfig(this.getExecutorType());
		if (config == null)
			return;
		
		engineUrl = config.getEngine().getUrl();
		userName = config.getUserName();
		password = config.getPassword();
		taskClient = new TaskClient(engineUrl, userName, password);
		
		this.initialized = true;
	}
	
	void reinitialize() {
		this.initialized = false;
	}

	
	/*
	 * ************************************************
	 *  Delegate methods for ExecutorContext    
	 * ************************************************
	 */
	/**
	 * @param error
	 */
	private void stopExecutor(String error) {
		executorContext.stopExecutorWithError(getExecutorType(), error);
	}
	
	/**
	 * 
	 */
	protected void updateLastExecuteTime() {
		executorContext.updateLastExecuteTime(getExecutorType());
	}
	
	/**
	 * @return
	 */
	protected boolean isExecutorEnabled() {
		return executorContext.isExecutorEnabled(getExecutorType());
	}
	
	
	/*
	 * ************************************************
	 *  Delegate methods for TaskClient    
	 * ************************************************
	 */
	protected List<TaskObject> getAssignedTasks() {

        //有问题
		TaskListObject result = taskClient.getTasksByOwner(userName);
		if (result != null && result.getData() != null && !result.getData().isEmpty()) {
			return result.getData();
		}
		
		result = taskClient.getTasksByCandidateGroup(candidateGroup);
		if (result != null) {
			return result.getData();
		}
		
		return null;
	}
	
	
	protected void claimTask(TaskObject task) {
		taskClient.claimTask(task.getId());
	}
	
	protected void unClaimTask(TaskObject task) {
		taskClient.unclaimTask(task.getId());	
	}

	protected void completeTask(TaskObject task, boolean success, String rsltTitle, String rsltContent) {
		this.completeTask(task, success, 100, rsltTitle, rsltContent, null);
	}
	
	protected void completeTask(TaskObject task, boolean success, int certainty, String rsltTitle, String rsltContent) {
		this.completeTask(task, success, certainty, rsltTitle, rsltContent, null);
	}
	
	protected void completeTask(TaskObject task, boolean success, String rsltTitle, String rsltContent, Map<String, Object> variablesToSet) {
		this.completeTask(task, success, 100, rsltTitle, rsltContent, variablesToSet);
	}
	
	protected void completeTask(TaskObject task, boolean success, int certainty, String rsltTitle, String rsltContent, Map<String, Object> variablesToSet) {
		taskClient.createTaskJournal(task.getId(), rsltTitle, rsltContent);
		
		List<VariableObject> vars = new ArrayList<VariableObject>();
		
		vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT, success, VariableObject.SCOPE_GLOBAL));
		vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT_CERTAINTY, certainty, VariableObject.SCOPE_GLOBAL));
		vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT_DESC, rsltContent, VariableObject.SCOPE_GLOBAL));
		
		if (variablesToSet != null) {
			for (String key : variablesToSet.keySet()) {
				VariableObject tmpVar = new VariableObject(key, variablesToSet.get(key));
				vars.add(tmpVar);
			}
		}
		taskClient.completeTask(task.getId(), vars);
	}
	
	protected void createTaskVariable(TaskObject task, VariableObject variable) {
		taskClient.createTaskVariable(task.getId(), variable);
	}
	
	protected void createTaskVariables(TaskObject task, List<VariableObject> variables) {
		taskClient.createTaskVariables(task.getId(), variables);
	}
	
	protected Object getTaskVariable(TaskObject task, String variableName) {
		VariableObject variable = taskClient.getTaskVariable(task.getId(), variableName);
		if (variable != null) {
			return variable.getValue();
		}
		return null;
	}
	
	protected Map<String, Object> getTaskVariables(TaskObject task) {
		List<VariableObject> variables = taskClient.getTaskVariables(task.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		for (VariableObject vo : variables) {
			map.put(vo.getName(), vo.getValue());
		}
		return map;
	}
	
	protected void createTaskJournal(TaskObject task, String rsltTitle, String rsltContent) {
		taskClient.createTaskJournal(task.getId(), rsltTitle, rsltContent);
	}
	
	protected String getStringVariable(Map<String, Object> taskVariables, String propertyName) {
		Object obj = taskVariables.get(propertyName);
		if (obj == null) {
			return null;
		} else {
			return (String)obj;
		}
	}
	
	protected boolean getBooleanVariable(Map<String, Object> taskVariables, String propertyName) {
		Object obj = taskVariables.get(propertyName);
		if (obj == null) {
			return false;
		}
		
		return (Boolean)obj;
	}
	
	

	public TaskExecutorType getExecutorType() {
		return executorType;
	}

	public String getCandidateGroup() {
		return candidateGroup;
	}

	public String getEngineUrl() {
		return engineUrl;
	}

	public void setEngineUrl(String engineUrl) {
		this.engineUrl = engineUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

package cn.ezid.cert.manual.task.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.collections.map.LRUMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import cn.ezid.cert.core.activiti.TaskClient;
import cn.ezid.cert.core.activiti.TaskConstants;
import cn.ezid.cert.core.activiti.model.TaskObject;
import cn.ezid.cert.core.activiti.model.VariableObject;
import cn.ezid.cert.core.oss.OSSService;
import cn.ezid.cert.manual.CertContext;
import cn.ezid.cert.manual.task.AsyncReviewService;
import cn.ezid.cert.manual.task.TaskViewBean;

@Service
public class AsyncReviewServiceImpl implements AsyncReviewService {
	private static final Logger log = LoggerFactory.getLogger(AsyncReviewServiceImpl.class);

	private Map<String, ConcurrentLinkedQueue<TaskViewBean>> taskCache = new ConcurrentHashMap<>();
	private Map<String, Map<String, TaskViewBean>> currentTasks = new ConcurrentHashMap<>();
	private Map<String, LRUMap> completeTasks = new ConcurrentHashMap<>();

	@Autowired
	private ThreadPoolTaskExecutor executor;

    @Autowired
    private CertContext certContext;
    
    @Autowired
    private OSSService ossService;

	@Override
	public List<TaskViewBean> getReviewTasks(int size) {
		// TODO Auto-generated method stub
		String user = certContext.getTaskClient().getUser();
		
		if (currentTasks.containsKey(user) && !currentTasks.get(user).isEmpty()) {
			log.debug("Current tasks not empty.");
			return new ArrayList<TaskViewBean>(currentTasks.get(user).values()); 
		} 
		
		if (!taskCache.containsKey(user)) {
			log.info("Init task cache for user {}.", user);
			taskCache.put(user, new ConcurrentLinkedQueue<TaskViewBean>());
			currentTasks.put(user, new LinkedHashMap<String, TaskViewBean>());
			completeTasks.put(user, new LRUMap(TASK_CACHE_CAPACITY * 2));   // Not a accurate size, just a big value
			
			// Try to get all the assigned tasks at start time
			int claimed = this.putAssignedTasks(certContext.getTaskClient(), certContext.getProcessDefinition().getKey(), TASK_CACHE_CAPACITY * 2);
			
			if (claimed < size) {
				claimed += this.putUnassignedTasks(certContext.getTaskClient(), certContext.getProcessDefinition().getKey(), size - claimed);
			} 
			
			// Fill the task cache queue in async mode
			this.asyncPutUnassignedTasks(certContext.getTaskClient(), certContext.getProcessDefinition().getKey(), 
					Math.min(TASK_CACHE_CAPACITY, TASK_CACHE_CAPACITY - (claimed - size)));
		} else {
			log.debug("Get assigned/unassigned tasks async for user {}.", user);
			this.asyncPutTasks(certContext.getTaskClient(), certContext.getProcessDefinition().getKey(), 
					Math.min(TASK_CACHE_CAPACITY, TASK_CACHE_CAPACITY - (taskCache.get(user).size() - size)));
		}
		
		for (int i = 0; i < size; i++) {
			TaskViewBean task = taskCache.get(user).poll();
			if (task == null) {
				break;
			}
			currentTasks.get(user).put(task.getTaskId(), task);
		}
		
		return new ArrayList<TaskViewBean>(currentTasks.get(user).values()); 
	}

	@Override
    public String completeTasks(String[] taskIds, String[] results, String[] comments) {
		for (String taskId : taskIds) {
			currentTasks.get(certContext.getTaskClient().getUser()).remove(taskId);
			completeTasks.get(certContext.getTaskClient().getUser()).put(taskId, taskId);
		}
		
		this.asyncCompleteTasks(certContext.getTaskClient(), taskIds, results, comments);
		return new StringBuilder().append(taskIds.length).append("个任务已提交后台完成").toString();
	}
	
	
	@Override
    public void purgeUserCache(String user) {
    	taskCache.remove(user);
    	currentTasks.remove(user);
    	completeTasks.remove(user);
    }

	
	/**
	 * @param assignee
	 * @param size
	 */
    private int putAssignedTasks(TaskClient taskClient, String processDefinitionKey, int size) {
    	if (size <= 0) {
    		return 0;
    	}
    	    	
        Map<String, String> vars = new HashMap<>();
        vars.put("assignee", taskClient.getUser());
        vars.put("processDefinitionKey", processDefinitionKey);
		vars.put("taskDefinitionKeyLike", TaskConstants.TASK_DEFINITION_KEY_MANUAL_LIKE);
        vars.put("sort", "dueDate");
        vars.put("order", "asc");
        vars.put("active", "true");
        // Double the size here, since some async complete tasks might be filtered out
        vars.put("size", Integer.toString(size * 2));

		List<TaskObject> taskList = taskClient
				.getTasksByQueryString(
						"assignee={assignee}&sort={sort}&order={order}&active={active}&start=0&size={size}&includeProcessVariables=false&processDefinitionKey={processDefinitionKey}&taskDefinitionKeyLike={taskDefinitionKeyLike}",
						vars).getData();
		
		List<TaskViewBean> viewBeans = new ArrayList<TaskViewBean>();
		
		for (TaskObject task : taskList) {
			// Filter out if in async complete process
			if (completeTasks.containsKey(taskClient.getUser()) &&
					completeTasks.get(taskClient.getUser()).containsKey(task.getId())) {
				log.debug("Task {} is in async complete process.", task.getId());
				continue;
			}
			
			TaskViewBean viewBean = new TaskViewBean(task);
			
			// Add view beans into cache before variables are included, to improve performance
			// Because this method might be run in async mode, have to verify if user's task cache queue exists
			if (taskCache.containsKey(taskClient.getUser())) {
				taskCache.get(taskClient.getUser()).offer(viewBean);
			}
			viewBeans.add(viewBean);
			
			// Break when view beans size equals the required size
			if (viewBeans.size() >= size) {
				break;
			}
		}
		
		for (TaskViewBean viewBean : viewBeans) {
			List<VariableObject> variables = taskClient.getTaskVariables(viewBean.getTaskId());
			viewBean.setVariables(variables);
			viewBean.beforeRender();
		}
		
		return viewBeans.size();
    }
    
    
    /**
     * @param taskClient
     * @param processDefinition
     * @param size
     */
    private int putUnassignedTasks(TaskClient taskClient, String processDefinitionKey, int size) {
    	if (size <= 0) {
    		return 0;
    	}
    	
        Map<String, String> vars = new HashMap<>();
        vars.put("processDefinitionKey", processDefinitionKey);
		vars.put("taskDefinitionKeyLike", TaskConstants.TASK_DEFINITION_KEY_MANUAL_LIKE);
		vars.put("sort", "dueDate");
		vars.put("order", "asc");
		vars.put("active", "true");
		vars.put("size", Integer.toString(size));
		
		List<TaskObject> taskList = null;
		synchronized(this) {
			taskList = taskClient
					.getTasksByQueryString(
							"unassigned=true&sort={sort}&order={order}&active={active}&start=0&size={size}&processDefinitionKey={processDefinitionKey}&taskDefinitionKeyLike={taskDefinitionKeyLike}",
							vars).getData();
			for (TaskObject task : taskList) {
				log.debug("User {} claiming task {}...", taskClient.getUser(), task.getId());
				taskClient.claimTask(task.getId());
			}
		}
		
		List<TaskViewBean> viewBeans = new ArrayList<TaskViewBean>();
		
		for (TaskObject task : taskList) {
			TaskViewBean viewBean = new TaskViewBean(task);
			
			// Because this method might be run in async mode, have to verify if user's task cache queue exists
			if (taskCache.containsKey(taskClient.getUser())) {
				taskCache.get(taskClient.getUser()).offer(viewBean);
			}
			viewBeans.add(viewBean);
		}
		
		for (TaskViewBean viewBean : viewBeans) {
			List<VariableObject> variables = taskClient.getTaskVariables(viewBean.getTaskId());
			viewBean.setVariables(variables);
			viewBean.beforeRender();
		}
		
		return taskList.size();
    }

	/**
	 * @param taskClient
	 * @param processDefinitionKey
	 * @param size
	 */
    private void asyncPutUnassignedTasks(final TaskClient taskClient, final String processDefinitionKey, final int size) {
    	executor.submit(new Runnable() {
    		@Override
    		public void run() {
    			AsyncReviewServiceImpl.this.putUnassignedTasks(taskClient, processDefinitionKey, size);
    		}
    	});
    }
    
    /**
	 * @param taskClient
	 * @param processDefinitionKey
	 * @param size
	 */
    private void asyncPutTasks(final TaskClient taskClient, final String processDefinitionKey, final int size) {
    	executor.submit(new Runnable() {
    		@Override
    		public void run() {
    			int claimed = AsyncReviewServiceImpl.this.putAssignedTasks(taskClient, processDefinitionKey, size);
    			AsyncReviewServiceImpl.this.putUnassignedTasks(taskClient, processDefinitionKey, size - claimed);
    		}
    	});
    }

    /**
     * @param taskIds
     * @param results
     * @param comments
     */
    private void completeTasks(TaskClient taskClient, String[] taskIds, String[] results, String[] comments) {
        for (int i = 0; i < taskIds.length; i++) {    
        	// If cert process is reInited or reCerted
        	// If task is assigned to others
            if (!this.isTaskValid(taskClient, taskIds[i])) {
                log.info("Task {} does not exist or assigned to others!", taskIds[i]);
                continue;
            }
            
            if (results[i].equals("pass")) {
                taskClient.createTaskJournal(taskIds[i], "人工审核完成-认证通过", comments[i]);

                List<VariableObject> vars = new ArrayList<VariableObject>();
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT, true, VariableObject.SCOPE_GLOBAL));
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT_CERTAINTY, 100l, VariableObject.SCOPE_GLOBAL));
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT_DESC, comments[i], VariableObject.SCOPE_GLOBAL));

                taskClient.completeTask(taskIds[i], vars);
                taskClient.updateHistoryTaskDescription(taskIds[i], "通过" + "," + comments[i]);
            } else if (results[i].equals("reject")) {
                taskClient.createTaskJournal(taskIds[i], "人工审核完成-认证未通过", comments[i]);

                List<VariableObject> vars = new ArrayList<VariableObject>();
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT, false, VariableObject.SCOPE_GLOBAL));
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT_DESC, comments[i], VariableObject.SCOPE_GLOBAL));

                taskClient.completeTask(taskIds[i], vars);
                taskClient.updateHistoryTaskDescription(taskIds[i], "未通过" + "," + comments[i]);
            } else if ("unclaim".equals(results[i])) {
                taskClient.unclaimTask(taskIds[i]);
            }
            log.info("====== User {} has {} the task {}.", taskClient.getUser(), results[i], taskIds[i]);
        }
    }

    /**
     * @param taskClient
     * @param taskIds
     * @param results
     * @param comments
     */
    private void asyncCompleteTasks(final TaskClient taskClient, final String[] taskIds, final String[] results, final String[] comments) { 
    	executor.submit(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				AsyncReviewServiceImpl.this.completeTasks(taskClient, taskIds, results, comments);
			}
		});
    }

    /**
     * @param taskClient
     * @param taskId
     * @return
     */
    private boolean isTaskValid(final TaskClient taskClient, final String taskId) {
        try {
            TaskObject task = taskClient.getTask(taskId);
            if (task.getAssignee().equals(taskClient.getUser())) {
            	return true;
            }
            log.info("The task {} has been assigned to {}", taskId, task.getAssignee());
            return false;
        } catch (Exception e){
            log.info("The task {} not exist!", taskId);
            return false;
        }
    }
}

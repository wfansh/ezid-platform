package cn.ezid.cert.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ezid.cert.app.executor.AbstractTaskExecutor;
import cn.ezid.cert.app.executor.ExecutorContext;
import cn.ezid.cert.core.topo.model.TaskExecutorType;

public class AppCoordinator {
	private static final Logger log = LoggerFactory.getLogger(AppCoordinator.class);

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private ExecutorContext taskContext;
	
	@Autowired
	private List<AbstractTaskExecutor> executors;
	
	private boolean initialized = false;
	
	
	private Map<TaskExecutorType, TriggerKey> triggerKeys = new HashMap<>();
	private Map<TaskExecutorType, TriggerKey> executingKeys = new HashMap<>();

	public AppCoordinator() {
	}

	private void initialize() {
		try {
			Set<TriggerKey> keys = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals("DEFAULT"));
			for (AbstractTaskExecutor executor : executors) {
				String prefix = executor.getClass().getSimpleName().toLowerCase();
				prefix = prefix.substring(0, prefix.indexOf("executor"));
				
				for (TriggerKey key : keys) {
					if (key.getName().toLowerCase().startsWith(prefix)) {
						triggerKeys.put(executor.getExecutorType(), key);
						break;
					}
				}
			}
			
			executingKeys.putAll(triggerKeys);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			log.error("Failed to initialize AppCoordinator.", e);
		}
		
		
		initialized = true;
	}
	public void execute() {
		if (!initialized)
			this.initialize();
		
		taskContext.sync();
		for (TaskExecutorType taskExecutorType : triggerKeys.keySet()) {
			coordinate(taskExecutorType);
		}
	}

	private synchronized void coordinate(TaskExecutorType taskExecutorType) {
		if (executingKeys.containsKey(taskExecutorType)) {
			if (!taskContext.isExecutorEnabled(taskExecutorType)) {	
				pauseExecutor(taskExecutorType);
			}
		} else {
			if (taskContext.isExecutorEnabled(taskExecutorType)) {
				resumeExecutor(taskExecutorType);
			}
		}
	}

	private synchronized void resumeExecutor(TaskExecutorType executorType) {
		TriggerKey triggerKey = triggerKeys.get(executorType);

		try {			
			TriggerState status = scheduler.getTriggerState(triggerKey);
			log.info("Resume quartz job {}. Previous status is {}.", triggerKey, status);
			
			scheduler.resumeTrigger(triggerKey);
			executingKeys.put(executorType, triggerKey);
		} catch (SchedulerException e) {
			log.error("Can't resumt quartz job {}. Caused by : {}.", triggerKey.getName(), e);
		}
	}

	private synchronized void pauseExecutor(TaskExecutorType executorType) {
		TriggerKey triggerKey = triggerKeys.get(executorType);
		try {
			TriggerState status = scheduler.getTriggerState(triggerKey);
			log.info("Pause quartz job {}. Previous status is {}.", triggerKey, status);

			scheduler.pauseTrigger(triggerKey);
			executingKeys.remove(executorType);
		} catch (SchedulerException e) {
			log.error("Can't pause quartz job {}. Caused by : {}.", triggerKey.getName(), e);
		}
	}
}
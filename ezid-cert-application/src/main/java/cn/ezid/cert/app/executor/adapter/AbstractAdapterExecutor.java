package cn.ezid.cert.app.executor.adapter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ezid.cert.app.executor.AbstractTaskExecutor;
import cn.ezid.cert.app.executor.ExecutorException;
import cn.ezid.cert.app.executor.adapter.AdaptException.ErrorType;
import cn.ezid.cert.core.NLSSupport;
import cn.ezid.cert.core.PropConfig;
import cn.ezid.cert.core.activiti.TaskConstants;
import cn.ezid.cert.core.activiti.model.TaskObject;
import cn.ezid.cert.core.history.AdapterInvalidEntity;
import cn.ezid.cert.core.history.HistoryService;
import cn.ezid.cert.core.topo.model.TaskExecutorType;
import cn.ezid.cert.core.util.EZUtils;

public abstract class AbstractAdapterExecutor extends AbstractTaskExecutor {

	private static final Logger log = LoggerFactory.getLogger(AbstractAdapterExecutor.class);
	
	@Autowired
	private HistoryService historySerivce;

	public AbstractAdapterExecutor(TaskExecutorType executorType, String candidateGroup) {
		super(executorType, candidateGroup);
	}
	
	protected abstract AdapterService getAdapterService();
	
	/* (non-Javadoc)
	 * @see cn.ezid.cert.app.executor.AbstractTaskExecutor#executeTask(cn.ezid.cert.core.activiti.model.TaskObject)
	 */
	@Override
	public void executeTask(TaskObject task) throws ExecutorException {
		// TODO Auto-generated method stub
		log.info("Start adapter for task {}.", task.getId());
		
		Map<String, Object> taskVariables = getTaskVariables(task);
		Map<String, Object> variablesToSet = new HashMap<String, Object>();
		
		String idcardNum = getStringVariable(taskVariables, TaskConstants.IN_VAR_PERSON_IDCARD_NUM);
		String name = getStringVariable(taskVariables, TaskConstants.IN_VAR_PERSON_NAME);
		boolean photoIgnored = getBooleanVariable(taskVariables, TaskConstants.IN_VAR_ADAPTER_PHOTO_IGNORED);
		boolean debugMode = getBooleanVariable(taskVariables, TaskConstants.IN_VAR_DEBUG_MODE)
				|| !PropConfig.getParameterAsBoolean("Adapter.Switch", false);

		try {			
			if (!EZUtils.isValidString(idcardNum) || !EZUtils.isValidString(name)) {
				completeTask(task, false, NLSSupport.getMessage("Title.Adapter.Fail"),
						NLSSupport.getMessage("Comment.Adapter.InfoIncomplete"));				
				log.warn("Execute get photo task failed, idcardNum : {}, name : {}.", idcardNum, name);
				return;
			}
			
			AdapterInvalidEntity invalidEntity = null;
			if (!debugMode) {
				invalidEntity = historySerivce.getInvalid(idcardNum, name);
			}
				
			if (invalidEntity != null) {
				completeTask(task, false, NLSSupport.getMessage("Title.Adapter.Fail"), invalidEntity.getMessage());
				log.warn("Execute get photo task failed, {} {} inconsistent in history.", idcardNum, name);
				return;
			}

			String photoUri = getAdapterService().getPhoto(idcardNum, name, photoIgnored, debugMode);
			
			variablesToSet.put(TaskConstants.COR_VAR_PERSON_IDCARD_PHOTO, photoUri);
			completeTask(task, true, NLSSupport.getMessage("Title.Adapter.Success"),
					NLSSupport.getMessage("Comment.Adapter.Success"), variablesToSet);
			log.info("Execute get photo task successfully for idcardNum={} name={}.", idcardNum, name);

		} catch (AdaptException e) {
			log.warn("AdapterException for {} {}, {} {}", idcardNum, name, e.getErrorType(), e.getMessage());
			if (ErrorType.PHOTO_UNFORMAT.equals(e.getErrorType())) {
				// Uncertain result when unformatted data returned
				completeTask(task, false, 0, NLSSupport.getMessage("Title.Adapter.Fail"), e.getMessage());
			} else {
				completeTask(task, false, NLSSupport.getMessage("Title.Adapter.Fail"), e.getMessage());
				if (!debugMode) {
					historySerivce.insertInvalid(idcardNum, name, e.getTitle(), e.getMessage());
				}
			}
			
			log.warn("Execute get photo task failed for idcardNum {} name {}. Caused by {}.", idcardNum, name,
					e.getMessage());
			return;
		} catch (Exception e) {
			log.error("Execute photo adapter task for idcardNum{} name {} failed with exception {}", idcardNum, name, e);
			throw new ExecutorException(e);
		}
	}
}

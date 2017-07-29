package cn.ezid.cert.app.executor.preprocess;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ezid.cert.app.executor.AbstractTaskExecutor;
import cn.ezid.cert.app.executor.ExecutorException;
import cn.ezid.cert.core.NLSSupport;
import cn.ezid.cert.core.activiti.TaskConstants;
import cn.ezid.cert.core.activiti.model.TaskObject;
import cn.ezid.cert.core.topo.model.TaskExecutorType;
import cn.ezid.cert.core.util.EZUtils;
import cn.ezid.cert.core.util.EZUtils.OSPlatform;


/**
 * @author Fan
 */
public class PreprocessExecutor extends AbstractTaskExecutor{
	private static final Logger log = LoggerFactory.getLogger(PreprocessExecutor.class);
	
	@Autowired
	private PreprocessService preprocessService;
	
	
	public PreprocessExecutor() {
		super(TaskExecutorType.preprocess, TaskConstants.GROUP_PHOTO_PREPROCESS);
	}
		
	@Override
	public void executeTask(TaskObject task) throws ExecutorException {
		
		if (EZUtils.getOSPlatform() != OSPlatform.Linux) {
			throw new ExecutorException("Platform not supported - " + EZUtils.getOSPlatform());
		}
		
		try {
			log.info("Start preproess for task {}.", task.getId());
			Map<String, Object> taskVariables = getTaskVariables(task);
			
			/**
			 * 1. Photo
			 */
			String personPhoto = getStringVariable(taskVariables, TaskConstants.IN_VAR_PERSON_PHOTO);
			if (EZUtils.isValidString(personPhoto)) {
				for (String photo : personPhoto.split(";")) {
					preprocessService.preprocessPhoto(photo);
				}
			} else {
				log.warn("Execute preprocess task failed, personPhoto is {}.", personPhoto);
				completeTask(task, false, NLSSupport.getMessage("Title.Preprocess.Fail"), 
						NLSSupport.getMessage("Comment.Preprocess.PersonPhotoNotExist"));
				return;
			}
			
			/**
			 * 2. Video
			 */
			String personVideo = getStringVariable(taskVariables, TaskConstants.IN_VAR_PERSON_VIDEO);
			if (EZUtils.isValidString(personVideo)) {
				for (String video : personVideo.split(";")) {
					preprocessService.preprocessVideo(video);
				}
			} else {
				log.info("Skip person video preproess because it doesn't exist.");
			}
			
			completeTask(task, true, NLSSupport.getMessage("Title.Preprocess.Success"), 
					NLSSupport.getMessage("Comment.Preprocess.Success"));
			log.info("Execute the preprocess task {} successfully", task.getId());
		} catch (Exception e) {
			log.error("Execute preprocess task failed with exception.", e);
			throw new ExecutorException(e);
		}
	}
}

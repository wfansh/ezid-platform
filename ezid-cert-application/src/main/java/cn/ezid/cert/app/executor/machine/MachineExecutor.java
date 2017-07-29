package cn.ezid.cert.app.executor.machine;

import java.util.HashMap;
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

public class MachineExecutor extends AbstractTaskExecutor {
	private static final Logger log = LoggerFactory.getLogger(MachineExecutor.class);

	@Autowired
	private MachineService machineService;

	public MachineExecutor() {
		super(TaskExecutorType.machine, TaskConstants.GROUP_MACHINE_CERTIFICATION);
	}
	
	@Override
	public void executeTask(TaskObject task) throws ExecutorException {
		
		if (EZUtils.getOSPlatform() != OSPlatform.Windows) {
			throw new ExecutorException("Platform not supported - " + EZUtils.getOSPlatform());
		}
		
		try {
			log.info("Start machine identifiation for task {}.", task.getId());

			Map<String, Object> taskVariables = getTaskVariables(task);
			Map<String, Object> variablesToSet = new HashMap<String, Object>();
			
			String personPhoto = getStringVariable(taskVariables, TaskConstants.IN_VAR_PERSON_PHOTO);
			if (!EZUtils.isValidString(personPhoto)) {
				variablesToSet.put(TaskConstants.OUT_VAR_MACHINE_CERTIFICATION_RESULT, -1);
				completeTask(task, false, NLSSupport.getMessage("Title.Machine.Fail"),
						NLSSupport.getMessage("Comment.Machine.PersonPhotoNotExist"), variablesToSet);
				log.warn("Execute machine identification task failed. PersonPhoto is {}。", personPhoto);
				return;
			}

			String photo = personPhoto.split(";")[0];
			if (!machineService.detectFace(photo)) {
				variablesToSet.put(TaskConstants.OUT_VAR_MACHINE_CERTIFICATION_RESULT, -1);
				completeTask(task, false, NLSSupport.getMessage("Title.Machine.Fail"),
						NLSSupport.getMessage("Comment.Machine.FaceNotDetected"), variablesToSet);
				log.warn("Execute machine identification task failed。 Undetectable face.");
				return;
			}

			String personIdcardPhoto = getStringVariable(taskVariables, TaskConstants.COR_VAR_PERSON_IDCARD_PHOTO);
			log.info("Start identify.");
			if (!EZUtils.isValidString(personIdcardPhoto)) {
				variablesToSet.put(TaskConstants.OUT_VAR_MACHINE_CERTIFICATION_RESULT, -1);
				completeTask(task, false, NLSSupport.getMessage("Title.Machine.Fail"),
						NLSSupport.getMessage("Comment.Machine.IdcardPhotoNotExist"), variablesToSet);
				log.warn("Execute machine identification task failed. PersonIdcardPhoto is {}.", personIdcardPhoto);
				return;
			}

			long result = machineService.compareFace(photo, personIdcardPhoto);

			variablesToSet.put(TaskConstants.OUT_VAR_MACHINE_CERTIFICATION_RESULT, result);
			completeTask(task, true, NLSSupport.getMessage("Title.Machine.Success"),
					NLSSupport.getMessage("Comment.Machine.Success", result), variablesToSet);
			log.info("Execute machine identification task successfully");
		} catch (Exception e) {
			log.error("Execute machine identification task failed with exception", e);
			throw new ExecutorException(e);
		}
	}
}

package com.ezid.cert.test;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ezid.cert.task.AbstractTaskExecuter;
import com.ezid.cert.task.TaskConstants;
import com.ezid.cert.task.activiti.model.TaskObject;
import com.ezid.cert.task.activiti.model.VariableObject;


public class MockPhotoAdapter extends AbstractTaskExecuter {
	private static final Logger log = LoggerFactory.getLogger(MockPhotoAdapter.class);

	public MockPhotoAdapter(String taskName, String userName, String password) {
		super(taskName, CertEngineTester.URL, TaskConstants.GROUP_PHOTO_ADAPTER, userName, password);
		
	}

	@Override
	public void executeTask(TaskObject task) throws Exception {
		log.info("MockPhotoAdapter execute task" + task.getId());
		VariableObject taskResult = new VariableObject();
		taskResult.setName("taskResult");
		taskResult.setType("boolean");
		taskResult.setValue(true);
		
		VariableObject taskResultTitle = new VariableObject();
		taskResultTitle.setName("taskResultTitle");
		taskResultTitle.setType("string");
		taskResultTitle.setValue("pass");
		
		VariableObject taskResultDesc = new VariableObject();
		taskResultDesc.setName("taskResultDesc");
		taskResultDesc.setType("string");
		taskResultDesc.setValue("pass desc");
		
		List<VariableObject> vars = new ArrayList<VariableObject>();
		vars.add(taskResult);
		vars.add(taskResultTitle);
		vars.add(taskResultDesc);
		
		createTaskComment(task, "pass now");
//		createTaskVariables(task, vars);
		completeTask(task, vars);
		
	}

	@Override
	public String getTaskName() {
		return "MockPhotoAdapter";
	}
	
}
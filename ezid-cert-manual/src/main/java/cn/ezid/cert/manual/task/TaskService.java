package cn.ezid.cert.manual.task;

import java.util.List;
import java.util.Map;

import cn.ezid.cert.core.activiti.model.HistoryTaskListObject;
import cn.ezid.cert.core.activiti.model.HistoryTaskObject;
import cn.ezid.cert.core.activiti.model.TaskListObject;
import cn.ezid.cert.core.activiti.model.UserObject;
import cn.ezid.cert.core.reject.TaskRejectReasonEntity;

public interface TaskService {

	public TaskListObject getTasksByCondition(TaskQueryBean taskQueryBean);

	public HistoryTaskListObject getHistoryTasksByCondition(TaskQueryBean taskQueryBean);

	public HistoryTaskObject getManualHistoryTaskByProcessId(String processId);

	public TaskViewBean getTaskViewByProcessInstanceId(String processInstanceId);

	public List<UserObject> getUsersByGroup(String group);

	public boolean isTaskOwner(String id, String userName);

	
	
	public List<TaskRejectReasonEntity> selectAllTaskRejectReasons();

	public TaskRejectReasonEntity getTaskRejectReasonById(long id);

	public void createTaskRejectReason(String name, long parentId);

	public void deleteTaskRejectReason(long id);

	
	
	public List<TaskViewBean> claimTask(int count);

	public void unclaim(String taskId);

	public String completeTask(int taskCount, String[] taskIds, String[] results, String[] comments);

	public void retrialTask(String taskId, String result, String comment);

	public Map<String, String> getTaskSummary();

}

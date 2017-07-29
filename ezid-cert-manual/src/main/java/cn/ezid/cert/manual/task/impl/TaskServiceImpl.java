package cn.ezid.cert.manual.task.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ezid.cert.core.activiti.TaskClient;
import cn.ezid.cert.core.activiti.TaskConstants;
import cn.ezid.cert.core.activiti.model.HistoryTaskListObject;
import cn.ezid.cert.core.activiti.model.HistoryTaskObject;
import cn.ezid.cert.core.activiti.model.TaskListObject;
import cn.ezid.cert.core.activiti.model.TaskObject;
import cn.ezid.cert.core.activiti.model.UserObject;
import cn.ezid.cert.core.activiti.model.VariableObject;
import cn.ezid.cert.core.reject.TaskRejectReasonDao;
import cn.ezid.cert.core.reject.TaskRejectReasonEntity;
import cn.ezid.cert.core.util.EZUtils;
import cn.ezid.cert.manual.CertContext;
import cn.ezid.cert.manual.task.TaskController;
import cn.ezid.cert.manual.task.TaskQueryBean;
import cn.ezid.cert.manual.task.TaskService;
import cn.ezid.cert.manual.task.TaskViewBean;

import com.fasterxml.jackson.databind.util.ISO8601Utils;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    
    @Autowired
    private CertContext certContext;

    @Autowired
    private TaskRejectReasonDao taskRejectReasonDao;


    // 根据搜索条件取得当前Task列表
    @Override
    public TaskListObject getTasksByCondition(TaskQueryBean taskQueryBean) {
    	 /**
         * process definition key
         */
        String queryString = "active=true&processDefinitionKey=" + certContext.getProcessDefinition().getKey();  
        Map<String, String> queryVars = new HashMap<>();

        if (EZUtils.isValidString(taskQueryBean.getId())) {
            queryString = queryString + "&processInstanceId={id}";
            queryVars.put("id", taskQueryBean.getId());
        }
        if (EZUtils.isValidString(taskQueryBean.getAssignee())) {
            queryString = queryString + "&assignee={assignee}";
            queryVars.put("assignee", taskQueryBean.getAssignee());
        }
        if (EZUtils.isValidString(taskQueryBean.getTimeCreateStart())) {
            queryString = queryString + "&createdAfter={createdAfter}";
            queryVars.put("createdAfter", getDateStr(taskQueryBean.getTimeCreateStart()));
        }
        if (EZUtils.isValidString(taskQueryBean.getTimeCreateEnd())) {
            queryString = queryString + "&createdBefore={createdBefore}";
            queryVars.put("createdBefore", getPlusOneDay(taskQueryBean.getTimeCreateEnd()));
        }
        if (EZUtils.isValidString(taskQueryBean.getDueDateStart())) {
            queryString = queryString + "&dueAfter={dueAfter}";
            queryVars.put("dueAfter", getDateStr(taskQueryBean.getDueDateStart()));
        }
        if (EZUtils.isValidString(taskQueryBean.getDueDateEnd())) {
            queryString = queryString + "&dueBefore={dueBefore}";
            queryVars.put("dueBefore", getPlusOneDay(taskQueryBean.getDueDateEnd()));
        }

        // 排序条件
        if (EZUtils.isValidString(taskQueryBean.getSort())) {		// 排序依据
            queryString = queryString + "&sort={sort}";
            queryVars.put("sort", taskQueryBean.getSort());
        }
        if (EZUtils.isValidString(taskQueryBean.getOrder())) {		// 正倒序
            queryString = queryString + "&order={order}";
            queryVars.put("order", taskQueryBean.getOrder());
        }

        // 分页
        if (taskQueryBean.getPageStart() > 0) {						// 开始页
            queryString = queryString + "&start={start}";
            queryVars.put("start", String.valueOf((taskQueryBean.getPageStart() - 1) * taskQueryBean.getPageSize()));
        }
        if (taskQueryBean.getPageStart() > 0) {						// 每页显示数量
            queryString = queryString + "&size={size}";
            queryVars.put("size", String.valueOf(taskQueryBean.getPageSize()));
        }
        
        if (EZUtils.isValidString(taskQueryBean.getTaskDefinitionKey())) {
            queryString = queryString + "&taskDefinitionKeyLike={taskDefinitionKey}";
            queryVars.put("taskDefinitionKey", taskQueryBean.getTaskDefinitionKey());
        }
                
        log.info(queryString);
        return certContext.getTaskClient().getTasksByQueryString(queryString, queryVars);
    }

    // 根据搜索条件取得历史Task列表
    @Override
    public HistoryTaskListObject getHistoryTasksByCondition(TaskQueryBean taskQueryBean) {
		String queryString = "finished=true&includeProcessVariables=false&processDefinitionKey="
				+ certContext.getProcessDefinition().getKey();
        Map<String, String> queryVars = new HashMap<String, String>();

        // 筛选条件
        if (EZUtils.isValidString(taskQueryBean.getId())) {
            queryString = queryString + "&processInstanceId={id}";
            queryVars.put("id", taskQueryBean.getId());
        }
        if (EZUtils.isValidString(taskQueryBean.getAssignee())) {
            queryString = queryString + "&taskAssignee={assignee}";
            queryVars.put("assignee", taskQueryBean.getAssignee());
        }
        if (EZUtils.isValidString(taskQueryBean.getResult())) {
            queryString = queryString + "&taskDescriptionLike=" + taskQueryBean.getResult() + "%";
        }
        if (EZUtils.isValidString(taskQueryBean.getTimeCreateStart())) {
            queryString = queryString + "&taskCreatedAfter={taskCreatedAfter}";
            queryVars.put("taskCreatedAfter", getDateStr(taskQueryBean.getTimeCreateStart()));
        }
        if (EZUtils.isValidString(taskQueryBean.getTimeCreateEnd())) {
            queryString = queryString + "&taskCreatedBefore={taskCreatedBefore}";
            queryVars.put("taskCreatedBefore", getPlusOneDay(taskQueryBean.getTimeCreateEnd()));
        }
        if (EZUtils.isValidString(taskQueryBean.getTimeFinishStart())) {
            queryString = queryString + "&taskCompletedAfter={taskCompletedAfter}";
            queryVars.put("taskCompletedAfter", getDateStr(taskQueryBean.getTimeFinishStart()));
        }
        if (EZUtils.isValidString(taskQueryBean.getTimeFinishEnd())) {
            queryString = queryString + "&taskCompletedBefore={taskCompletedBefore}";
            queryVars.put("taskCompletedBefore", getPlusOneDay(taskQueryBean.getTimeFinishEnd()));
        }

        // 排序条件
        if (EZUtils.isValidString(taskQueryBean.getSort())) {			// 排序依据
            queryString = queryString + "&sort={sort}";
            queryVars.put("sort", taskQueryBean.getSort());
        }
        if (EZUtils.isValidString(taskQueryBean.getOrder())) {			// 正倒序
            queryString = queryString + "&order={order}";
            queryVars.put("order", taskQueryBean.getOrder());
        }

        // 分页
        if (taskQueryBean.getPageStart() > 0) {							// 开始页
            queryString = queryString + "&start={start}";
            queryVars.put("start", String.valueOf((taskQueryBean.getPageStart() - 1) * taskQueryBean.getPageSize()));
        }
        if (taskQueryBean.getPageStart() > 0) {							// 每页显示数量
            queryString = queryString + "&size={size}";
            queryVars.put("size", String.valueOf(taskQueryBean.getPageSize()));
        }
        if (EZUtils.isValidString(taskQueryBean.getTaskDefinitionKey())) {
            queryString = queryString + "&taskDefinitionKey={taskDefinitionKey}";
            queryVars.put("taskDefinitionKey", taskQueryBean.getTaskDefinitionKey());
        }
        if (EZUtils.isValidString(taskQueryBean.getTaskNameLike())) {
            queryString = queryString + "&taskNameLike={taskNameLike}";
            queryVars.put("taskNameLike", taskQueryBean.getTaskNameLike());
        }
        
        log.info(queryString);
        log.info(queryVars.toString());
        return certContext.getTaskClient().getHistoryTasksByQueryString(queryString, queryVars);
    }
    
    @Override
    public HistoryTaskObject getManualHistoryTaskByProcessId(String processId) {
        Map<String, String> vars = new HashMap<>();
        vars.put("processInstanceId", processId);
        vars.put("taskNameLike", TaskConstants.TASK_NAME_MANUAL_LIKE);
        List<HistoryTaskObject> tasks = certContext.getTaskClient().getHistoryTasksByQueryString(
                        "finished=true&&includeProcessVariables=true&sort=endTime&order=desc&processInstanceId={processInstanceId}&taskNameLike={taskNameLike}",
                        vars).
                        getData();
        if (tasks.size() > 0) {
            return tasks.get(0);
        }
        return null;
    }

    @Override
    public TaskViewBean getTaskViewByProcessInstanceId(String processInstanceId) {
		Map<String, String> vars = new HashMap<>();
		TaskViewBean result = new TaskViewBean();
		vars.put("processInstanceId", processInstanceId);
		vars.put("taskDefinitionKeyLike", TaskConstants.TASK_DEFINITION_KEY_MANUAL_LIKE);
		vars.put("taskNameLike", TaskConstants.TASK_NAME_MANUAL_LIKE);

		List<TaskObject> taskList = certContext
				.getTaskClient()
				.getTasksByQueryString(
						"processInstanceId={processInstanceId}&taskDefinitionKeyLike={taskDefinitionKeyLike}&includeProcessVariables=true",
						vars).getData();

		if (taskList.size() > 0) {
			result = new TaskViewBean(taskList.get(0));
		} else {
			List<HistoryTaskObject> historyTaskList = certContext
					.getTaskClient()
					.getHistoryTasksByQueryString(
							"processInstanceId={processInstanceId}&taskNameLike={taskNameLike}&includeProcessVariables=true",
							vars).getData();
			result = new TaskViewBean(historyTaskList.get(0));
		}

		result.beforeRender();
        return result;
    }

    
    @Override
    public List<UserObject> getUsersByGroup(String group) {
        return certContext.getTaskClient().getUsersByGroup(group).getData();
    }

    @Override
    public boolean isTaskOwner(String id, String userName) {
        try {
            TaskObject task = certContext.getTaskClient().getTask(id);
            if (task.getAssignee().equals(userName))
                return true;
        } catch (Exception e){
            log.info("the task {} not exist",id);
            return false;
        }
        return false;
    }

    
    @Override
    public List<TaskRejectReasonEntity> selectAllTaskRejectReasons() {
        List<TaskRejectReasonEntity> resultList = new ArrayList<TaskRejectReasonEntity>();
        List<TaskRejectReasonEntity> reasonList = taskRejectReasonDao.select();

        // 得到完整的reason树
        for (TaskRejectReasonEntity reason : reasonList) {
            long id = reason.getId();
            for (TaskRejectReasonEntity subReason : reasonList) {
                if (subReason.getParentId() == id) {
                    if (reason.getSubReasonList() == null) {
                        reason.setSubReasonList(new ArrayList<TaskRejectReasonEntity>());
                    }
                    reason.getSubReasonList().add(subReason);
                }
            }
        }

        // 只取最父层的元素
        for (TaskRejectReasonEntity reason : reasonList) {
            if (reason.getParentId() == new Long(0)) {
                resultList.add(reason);
            }
        }
        return resultList;
    }

    @Override
    public TaskRejectReasonEntity getTaskRejectReasonById(long id) {
        TaskRejectReasonEntity entity = taskRejectReasonDao.get(id);
        return entity;
    }

    @Override
    @Transactional    
    public void createTaskRejectReason(String name, long parentId) {
        TaskRejectReasonEntity entity = new TaskRejectReasonEntity();

        entity.setName(name);
        entity.setParentId(parentId);

        String fullName = name;
        TaskRejectReasonEntity parentReason = getTaskRejectReasonById(parentId);
        if (parentReason != null) {
            fullName = parentReason.getName() + "-" + fullName;
        }
        entity.setFullName(fullName);

        taskRejectReasonDao.insert(entity);
    }

    @Override
    @Transactional
    public void deleteTaskRejectReason(long id) {
        taskRejectReasonDao.delete(id);
    }

    
    @Override
    public List<TaskViewBean> claimTask(int count) {
        TaskClient taskClient = certContext.getTaskClient();
        List<TaskObject> taskList = new ArrayList<>();
        List<TaskViewBean> resultList = new ArrayList<>();

        // 1. 先抓取当前人已领取的任务
        Map<String, String> vars = new HashMap<>();
        vars.put("assignee", taskClient.getUser());
        // process definition key
        vars.put("processDefinitionKey", certContext.getProcessDefinition().getKey());
		vars.put("taskDefinitionKeyLike", TaskConstants.TASK_DEFINITION_KEY_MANUAL_LIKE);
        vars.put("sort", "dueDate");
        vars.put("order", "asc");
        vars.put("active", "true");
        vars.put("size", Integer.toString(count));

		List<TaskObject> todoList = taskClient
				.getTasksByQueryString(
						"assignee={assignee}&sort={sort}&order={order}&active={active}&start=0&size={size}&includeProcessVariables=false&processDefinitionKey={processDefinitionKey}&taskDefinitionKeyLike={taskDefinitionKeyLike}",
						vars).getData();
		taskList.addAll(todoList);
        
		// 2. 抓取当前人还需领的任务并领取
		if (count > todoList.size()) {
			vars.clear();
	        vars.put("processDefinitionKey", certContext.getProcessDefinition().getKey());
			vars.put("taskDefinitionKeyLike", TaskConstants.TASK_DEFINITION_KEY_MANUAL_LIKE);
			vars.put("sort", "dueDate");
			vars.put("order", "asc");
			vars.put("active", "true");
			vars.put("size", Integer.toString(count - todoList.size()));
			
			List<TaskObject> unclaimList = new ArrayList<>();
			synchronized(this) {
				unclaimList = taskClient
						.getTasksByQueryString(
								"unassigned=true&sort={sort}&order={order}&active={active}&start=0&size={size}&processDefinitionKey={processDefinitionKey}&taskDefinitionKeyLike={taskDefinitionKeyLike}",
								vars).getData();
				for (TaskObject task : unclaimList) {
					taskClient.claimTask(task.getId());
				}
			}
			taskList.addAll(unclaimList);
		}
               
        for (TaskObject task : taskList) {
        	List<VariableObject> variables = taskClient.getTaskVariables(task.getId());
        	TaskViewBean viewBean = new TaskViewBean(task, variables);
        	viewBean.beforeRender();
            resultList.add(viewBean);
        }
        
        return resultList;
    }

    @Override
    public void unclaim(String taskId) {
        certContext.getTaskClient().unclaimTask(taskId);
    }

    @Override
    public void retrialTask(String taskId, String result, String common) {
        TaskClient taskClient = certContext.getTaskClient();
        taskClient.updateHistoryTaskJournal(taskId, result.equals("pass") ? "重审认证通过" : "重审认证不通过", common);
		taskClient.updateHistoryTaskDescription(taskId, (result.equals("pass") ? "通过" : "未通过") + "," + common);
        VariableObject resultVariable = new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT, result.equals("pass") ? true : false,
                VariableObject.SCOPE_GLOBAL);
        taskClient.updateHistoryTaskVariable(taskId, resultVariable);
        VariableObject resultDescVariable = new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT_DESC, common,
                VariableObject.SCOPE_GLOBAL);
        taskClient.updateHistoryTaskVariable(taskId, resultDescVariable);
        
        // Complete history task to update end time
        taskClient.completeHistoryTask(taskId);
    }

    @Override
    public String completeTask(int taskCount, String[] taskIds, String[] results, String[] comments) {
        TaskClient taskClient = certContext.getTaskClient();
        int passCount = 0;		// 通过数量
        int rejectCount = 0;	// 未通过数量
        int unclaimCount = 0;	// 放弃数量
        int anotherCount = 0;	// 已指派他人数量
        int reviewCount = 0; 	// 任务被重认证或者重采集
        String userName = certContext.getTaskClient().getUser();
        for (int i = 0; i < taskCount; i++) {
            //若任务被重采集或者重认证
            if (!this.isTaskExist(taskIds[i])) {
                reviewCount++;
                continue;
            }
            // 若任务不是在当前人手里
            if (!this.isTaskOwner(taskIds[i], userName)) {
                anotherCount++;
                continue;
            }
            if ("pass".equals(results[i])) {
                taskClient.createTaskJournal(taskIds[i], "人工审核完成-认证通过", comments[i]);

                List<VariableObject> vars = new ArrayList<VariableObject>();
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT, true, VariableObject.SCOPE_GLOBAL));
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT_CERTAINTY, 100l, VariableObject.SCOPE_GLOBAL));
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT_DESC, comments[i],
                        VariableObject.SCOPE_GLOBAL));

                taskClient.completeTask(taskIds[i], vars);
                taskClient.updateHistoryTaskDescription(taskIds[i], "通过" + "," + comments[i]);

                log.info("====== {} has {} the task({}).", userName, results[i], taskIds[i]);

                passCount++;
            } else if ("reject".equals(results[i])) {
                taskClient.createTaskJournal(taskIds[i], "人工审核完成-认证未通过", comments[i]);

                List<VariableObject> vars = new ArrayList<VariableObject>();
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT, false, VariableObject.SCOPE_GLOBAL));
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT_CERTAINTY, 100l, VariableObject.SCOPE_GLOBAL));
                vars.add(new VariableObject(TaskConstants.OUT_VAR_TASK_RESULT_DESC, comments[i],
                        VariableObject.SCOPE_GLOBAL));

                taskClient.completeTask(taskIds[i], vars);
                taskClient.updateHistoryTaskDescription(taskIds[i], "未通过" + "," + comments[i]);

                rejectCount++;
            } else if ("unclaim".equals(results[i])) {
                this.unclaim(taskIds[i]);
                unclaimCount++;
            }
            log.info("====== {} has {} the task({}).", userName, results[i], taskIds[i]);
        }

        String msg = "";
        if (reviewCount > 0){
            msg = msg + reviewCount + "个任务已被重采集或者重认证  ";
        }

        if (passCount > 0) {
            msg = msg + passCount + "个任务通过  ";
        }
        
        if (rejectCount > 0) {
            msg = msg + rejectCount + "个任务未通过  ";
        }
        
        if (unclaimCount > 0) {
            msg = msg + unclaimCount + "个任务被放弃  ";
        }
        
        if (anotherCount > 0) {
            msg = msg + anotherCount + "个任务已另指派   ";
        }
        
        return msg;
    }


    @Override
    public Map<String, String> getTaskSummary() {
        Map<String, String> resultMap = new HashMap<String, String>();
        TaskClient taskClient = certContext.getTaskClient();

        Date currntDate = new Date() ;
        
        Map<String, String> vars = new HashMap<>();
        
        // 1. overDue
        vars.put("dueBefore", ISO8601Utils.format(currntDate));
        vars.put("processDefinitionKey", certContext.getProcessDefinition().getKey());
        vars.put("taskDefinitionKeyLike", TaskConstants.TASK_DEFINITION_KEY_MANUAL_LIKE);
		int overDue = taskClient
				.getTasksByQueryString(
						"active=true&size=0&dueBefore={dueBefore}&processDefinitionKey={processDefinitionKey}&taskDefinitionKeyLike={taskDefinitionKeyLike}",
						vars).getTotal();
        resultMap.put("overDue", String.valueOf(overDue));

        
        // 2. due in 3 days
        vars.clear();
        vars.put("dueBefore", ISO8601Utils.format(new Date(currntDate.getTime() + 1000 * 60 * 60 * 24 * 3)));
        vars.put("processDefinitionKey", certContext.getProcessDefinition().getKey());
        vars.put("taskDefinitionKeyLike", TaskConstants.TASK_DEFINITION_KEY_MANUAL_LIKE);
		int halfHourDue = taskClient
				.getTasksByQueryString(
						"active=true&size=0&dueBefore={dueBefore}&processDefinitionKey={processDefinitionKey}&taskDefinitionKeyLike={taskDefinitionKeyLike}",
						vars).getTotal();
        resultMap.put("threeDaysDue", String.valueOf(halfHourDue - overDue));

        
        // 3. todo
        vars.clear();
        vars.put("processDefinitionKey", certContext.getProcessDefinition().getKey());
        vars.put("taskDefinitionKeyLike", TaskConstants.TASK_DEFINITION_KEY_MANUAL_LIKE);
        int todo = taskClient.getTasksByQueryString(
                "active=true&size=0&processDefinitionKey={processDefinitionKey}&taskDefinitionKeyLike={taskDefinitionKeyLike}", vars).getTotal();
        resultMap.put("todo", String.valueOf(todo));

        
        // 4. extra
        vars.clear();
        vars.put("taskCompletedAfter", ISO8601Utils.format(getTodayTime()));
        vars.put("taskCompletedBefore", getPlusOneDay(getTodayTime()));
        vars.put("processDefinitionKey", certContext.getProcessDefinition().getKey());
        vars.put("taskNameLike", TaskConstants.TASK_NAME_MANUAL_LIKE);
        int finishToday = taskClient.getHistoryTasksByQueryString(
                "finished=true&size=0&taskCompletedAfter={taskCompletedAfter}&taskCompletedBefore={taskCompletedBefore}&processDefinitionKey={processDefinitionKey}&taskNameLike={taskNameLike}", vars)
                .getTotal();
        resultMap.put("finishToday", String.valueOf(finishToday));
        
        List<UserObject> userList = getUsersByGroup(TaskConstants.GROUP_MANUAL_CERTIFICATION);
        for (UserObject user : userList) {
        	vars.clear();
            vars.put("processDefinitionKey", certContext.getProcessDefinition().getKey());
            vars.put("taskDefinitionKeyLike", TaskConstants.TASK_DEFINITION_KEY_MANUAL_LIKE);
            vars.put("assignee", user.getFirstName());
            int userTodo = taskClient.getTasksByQueryString(
                    "active=true&size=0&processDefinitionKey={processDefinitionKey}&taskDefinitionKeyLike={taskDefinitionKeyLike}&assignee={assignee}", vars).getTotal();
            resultMap.put("userTodo_" + user.getFirstName(), String.valueOf(userTodo));
            
            vars.clear();
            vars.put("taskCompletedAfter", ISO8601Utils.format(getTodayTime()));
            vars.put("taskCompletedBefore", getPlusOneDay(getTodayTime()));
            vars.put("processDefinitionKey", certContext.getProcessDefinition().getKey());
            vars.put("taskNameLike", TaskConstants.TASK_NAME_MANUAL_LIKE);
            vars.put("taskAssignee", user.getFirstName());
            int userFinishToday = taskClient.getHistoryTasksByQueryString(
                    "finished=true&size=0&taskCompletedAfter={taskCompletedAfter}&taskCompletedBefore={taskCompletedBefore}&processDefinitionKey={processDefinitionKey}&taskNameLike={taskNameLike}&taskAssignee={taskAssignee}", vars)
                    .getTotal();
            resultMap.put("userFinishToday_" + user.getFirstName(), String.valueOf(userFinishToday));
        }

        return resultMap;
    }

    
    /******************************************************
     * 
     */
    
    private boolean isTaskExist(String taskId) {
        try {
            certContext.getTaskClient().getTask(taskId);
        } catch (Exception e){
            log.info("the task {} not exist", taskId);
            return false;
        }
        return true;
    }
    
    private String getPlusOneDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return ISO8601Utils.format(calendar.getTime());
    }

    private String getPlusOneDay(String date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendar.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return ISO8601Utils.format(calendar.getTime());
    }

    private Date getTodayTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    private String getDateStr(String date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendar.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return ISO8601Utils.format(calendar.getTime());
    }
}

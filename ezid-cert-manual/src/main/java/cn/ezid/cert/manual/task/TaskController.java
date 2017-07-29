package cn.ezid.cert.manual.task;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.ezid.cert.core.activiti.TaskConstants;
import cn.ezid.cert.core.activiti.model.HistoryTaskListObject;
import cn.ezid.cert.core.activiti.model.HistoryTaskObject;
import cn.ezid.cert.core.activiti.model.TaskListObject;
import cn.ezid.cert.core.activiti.model.TaskObject;
import cn.ezid.cert.core.activiti.model.UserObject;
import cn.ezid.cert.core.reject.TaskRejectReasonEntity;
import cn.ezid.cert.core.util.EZUtils;
import cn.ezid.cert.manual.CertContext;
import cn.ezid.cert.manual.common.Pagination;

@Controller
@RequestMapping("/task")
public class TaskController {
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private CertContext certContext;

	@Autowired
	private TaskService taskService;

	@Autowired
	private AsyncReviewService asyncReviewService;
		
	
	// 菜单->任务管理
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String index(Model model, Principal principal) {
        if (!((UsernamePasswordAuthenticationToken) principal).getAuthorities().contains(new SimpleGrantedAuthority(TaskConstants.GROUP_ADMIN))) {
            return "redirect:/task/historyTask";
        }
		Map<String, String> summaryMap = taskService.getTaskSummary();
		
		Map<String, UserObject> userMap = new HashMap<>();
		for (String key : summaryMap.keySet()) {
			if (key.startsWith("userTodo") || key.startsWith("userFinishToday")) {
				String firstName = key.substring(key.indexOf("_") + 1);
				String value = summaryMap.get(key);
				UserObject user = userMap.containsKey(firstName) ? userMap.get(firstName) : new UserObject();
				user.setFirstName(firstName);
				if (key.startsWith("userTodo")) {
					user.setUrl(value);
				} else {
					user.setEmail(value);
				}
				userMap.put(firstName, user);
			}
		}
		
		model.addAttribute("userList", userMap.values());
		model.addAttribute("summaryMap", summaryMap);
		return "task.index";
	}

	// 菜单->任务管理->当前任务管理
	@RequestMapping(value = "listTask")
	public String listTask(@ModelAttribute("TaskQueryBean") TaskQueryBean taskQueryBean, Model model) {
		Pagination<TaskObject> manualTaskPage = new Pagination<TaskObject>();
		List<UserObject> userList = new ArrayList<UserObject>();

		taskQueryBean.setTaskDefinitionKey(TaskConstants.TASK_DEFINITION_KEY_MANUAL_LIKE);
//		taskQueryBean.setSort("dueDate");
//		taskQueryBean.setOrder("asc");
		TaskListObject taskList = taskService.getTasksByCondition(taskQueryBean);
		manualTaskPage = new Pagination<TaskObject>(taskQueryBean.getPageStart(), taskQueryBean.getPageSize(),
				taskList.getTotal(), taskList.getData());
		
		userList = taskService.getUsersByGroup(TaskConstants.GROUP_MANUAL_CERTIFICATION);

		model.addAttribute("taskQueryBean", taskQueryBean);
		model.addAttribute("manualTaskPage", manualTaskPage);
		model.addAttribute("taskSize", taskList.getTotal());
		model.addAttribute("userList", userList);
		return "task.list";
	}

	// 菜单->任务管理->历史任务管理
	@RequestMapping(value = "historyTask")
	public String historyTask(Model model, @ModelAttribute("TaskQueryBean") TaskQueryBean taskQueryBean, Principal principal) {
		Pagination<HistoryTaskObject> manualTaskPage = new Pagination<HistoryTaskObject>();
		List<UserObject> userList = new ArrayList<UserObject>();
		
        taskQueryBean.setSort("endTime");
        taskQueryBean.setOrder("desc");
        taskQueryBean.setTaskNameLike(TaskConstants.TASK_NAME_MANUAL_LIKE);
        
        if (!((UsernamePasswordAuthenticationToken) principal).getAuthorities().contains(new SimpleGrantedAuthority(TaskConstants.GROUP_ADMIN))){
            taskQueryBean.setAssignee(principal.getName());
        };
        
        HistoryTaskListObject historyTaskList = taskService.getHistoryTasksByCondition(taskQueryBean);
        manualTaskPage = new Pagination<HistoryTaskObject>(taskQueryBean.getPageStart(), taskQueryBean.getPageSize(),
        		historyTaskList.getTotal(), historyTaskList.getData());
		userList = taskService.getUsersByGroup(TaskConstants.GROUP_MANUAL_CERTIFICATION);
		
		model.addAttribute("taskQueryBean", taskQueryBean);
		model.addAttribute("manualTaskPage", manualTaskPage);
		model.addAttribute("taskSize", historyTaskList.getTotal());
		model.addAttribute("userList", userList);
		
		return "task.history." + certContext.getProcessDefinition().getKey();
	}

	// 查看单个人信息
	@RequestMapping(method = RequestMethod.GET, value = "view/{id}")
	public String view(@PathVariable String id, Model model) {
		// List<UserObject> userList = new ArrayList<UserObject>();		List<TaskRejectReasonEntity> taskRejectReasonList = new ArrayList<TaskRejectReasonEntity>();
		TaskViewBean task = taskService.getTaskViewByProcessInstanceId(id);
		List<TaskRejectReasonEntity> taskRejectReasonList = taskService.selectAllTaskRejectReasons();

		model.addAttribute("task", task);
		model.addAttribute("taskRejectReasonList", taskRejectReasonList);
		return "task.view." + certContext.getProcessDefinition().getKey();
	}

	// 菜单->任务管理->开始审核任务
	@RequestMapping(value = "reviewTask")
	public String reviewTask(Model model) {
		List<TaskViewBean> taskList = asyncReviewService.getReviewTasks(5);
		List<TaskRejectReasonEntity> taskRejectReasonList = taskService.selectAllTaskRejectReasons();
		model.addAttribute("taskList", taskList);
		model.addAttribute("taskRejectReasonList", taskRejectReasonList);
		return "task.review." + certContext.getProcessDefinition().getKey();
	}

	@RequestMapping(value = "completeTask")
	public String completeTask(@RequestParam(value = "nextAction", required = true) String nextAction,
			@RequestParam(value = "taskCount", required = true) int taskCount,
			@RequestParam(value = "taskId", required = true) String[] taskIds,
			@RequestParam(value = "result", required = true) String[] results,
			@RequestParam(value = "comment", required = true) String[] comments, Model model,
			RedirectAttributes redirectAttributes) {

		String resultMsg = asyncReviewService.completeTasks(taskIds, results, comments);
		redirectAttributes.addFlashAttribute("alertMsgType", "success");
		redirectAttributes.addFlashAttribute("alertMsgTitle", "任务提交成功！");
		redirectAttributes.addFlashAttribute("alertMsgContent", resultMsg);
		if (nextAction.equals("end")) {
			// Purge current user's review task cache
			asyncReviewService.purgeUserCache(certContext.getTaskClient().getUser());
			return "redirect:/task";
		} else {
			return "redirect:/task/reviewTask";
		}
	}

	// 指派任务
	@RequestMapping(method = RequestMethod.POST, value = "assignTask")
	public String assignTask(@RequestParam(value = "taskIds", required = true) String[] taskIds, 
			@RequestParam(value = "originAssignees", required = false) String[] originAssignees,
			@RequestParam(value = "assignee", required = true) String assignee, Model model, Principal principal,
			RedirectAttributes redirectAttributes) {

		if (originAssignees != null) {
			for (String originAssignee : new HashSet<String>(Arrays.asList(originAssignees))) {
				asyncReviewService.purgeUserCache(originAssignee);
			}
		}
		asyncReviewService.purgeUserCache(assignee);
		
		for (String taskId : taskIds) {
			taskService.unclaim(taskId);
			if (EZUtils.isValidString(assignee)) {
				certContext.getTaskClient().claimTask(taskId, assignee);
			}
		}
		redirectAttributes.addFlashAttribute("alertMsgType", "success");
		redirectAttributes.addFlashAttribute("alertMsgTitle", "指派任务成功！");
		redirectAttributes.addFlashAttribute("alertMsgContent", "此批认证人员的任务已成功指派给" + assignee);
		return "redirect:/task/listTask";
	}

	// 重审任务
	@RequestMapping(method = RequestMethod.POST, value = "retrialTask")
	public String retrialTask(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "result", required = true) String result,
			@RequestParam(value = "common", required = true) String common, Model model,
			RedirectAttributes redirectAttributes) {
		log.debug("Retrial task for process instance {}.", id);
		
		HistoryTaskObject historyTask = taskService.getManualHistoryTaskByProcessId(id);
		taskService.retrialTask(historyTask.getId(), result, common);
				
		redirectAttributes.addFlashAttribute("alertMsgType", "success");
		redirectAttributes.addFlashAttribute("alertMsgTitle", "重审任务成功！");
		redirectAttributes.addFlashAttribute("alertMsgContent", "该人员的任务重审操作已被成功执行。");
		return "redirect:/task/view/" + id;
	}

	@RequestMapping(value = "rejectReason")
	public String listRejectReason(Model model) {
		List<TaskRejectReasonEntity> rejectReasonList = taskService.selectAllTaskRejectReasons();
		model.addAttribute("rejectReasonList", rejectReasonList);
		return "task.rejectReason";
	}

	@RequestMapping(method = RequestMethod.POST, value = "rejectReason/add")
	public String addRejectReason(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "parentId", required = false) Long parentId, Model model,
			RedirectAttributes redirectAttributes) {
		taskService.createTaskRejectReason(name, parentId);
		redirectAttributes.addFlashAttribute("alertMsgType", "success");
		redirectAttributes.addFlashAttribute("alertMsgTitle", "添加成功！");
		redirectAttributes.addFlashAttribute("alertMsgContent", "新的理由已经被成功创建了");
		return "redirect:/task/rejectReason";
	}

	@RequestMapping(method = RequestMethod.GET, value = "rejectReason/delete/{id}")
	public String deleteRejectReason(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		taskService.deleteTaskRejectReason(id);
		redirectAttributes.addFlashAttribute("alertMsgType", "success");
		redirectAttributes.addFlashAttribute("alertMsgTitle", "删除成功！");
		redirectAttributes.addFlashAttribute("alertMsgContent", "您选择的理由已经被成功删除了");
		return "redirect:/task/rejectReason";
	}

	// 获取个人视频子页
	@RequestMapping(method = RequestMethod.GET, value = "get_detail_video/{id}")
	public String getDetailVideo(@PathVariable String id, Model model, HttpServletRequest request) {
		TaskViewBean task = taskService.getTaskViewByProcessInstanceId(id);
		model.addAttribute("task", task);
		return "/task/include_detail_video";
	}

}

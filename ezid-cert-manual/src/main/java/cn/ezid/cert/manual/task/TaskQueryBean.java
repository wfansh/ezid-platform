package cn.ezid.cert.manual.task;

public class TaskQueryBean {

	private String id;
	private String result;
	private String common;
	private String assignee;
	private String timeCreateStart;
	private String timeCreateEnd;
	private String timeFinishStart;
	private String timeFinishEnd;
	private String dueDateStart;
	private String dueDateEnd;
	private String taskDefinitionKey;
	private String taskNameLike;

	private int pageStart = 1;
	private int pageSize = 10;
	private String sort;
	private String order;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageSize() {
		if (pageSize == 0) {
			return 10;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public String getTimeCreateStart() {
		return timeCreateStart;
	}

	public void setTimeCreateStart(String timeCreateStart) {
		this.timeCreateStart = timeCreateStart;
	}

	public String getTimeCreateEnd() {
		return timeCreateEnd;
	}

	public void setTimeCreateEnd(String timeCreateEnd) {
		this.timeCreateEnd = timeCreateEnd;
	}

	public String getTimeFinishStart() {
		return timeFinishStart;
	}

	public void setTimeFinishStart(String timeFinishStart) {
		this.timeFinishStart = timeFinishStart;
	}

	public String getTimeFinishEnd() {
		return timeFinishEnd;
	}

	public void setTimeFinishEnd(String timeFinishEnd) {
		this.timeFinishEnd = timeFinishEnd;
	}

	public String getDueDateStart() {
		return dueDateStart;
	}

	public void setDueDateStart(String dueDateStart) {
		this.dueDateStart = dueDateStart;
	}

	public String getDueDateEnd() {
		return dueDateEnd;
	}

	public void setDueDateEnd(String dueDateEnd) {
		this.dueDateEnd = dueDateEnd;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getTaskNameLike() {
		return taskNameLike;
	}

	public void setTaskNameLike(String taskNameLike) {
		this.taskNameLike = taskNameLike;
	}
}

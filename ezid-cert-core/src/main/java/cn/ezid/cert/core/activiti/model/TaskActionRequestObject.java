package cn.ezid.cert.core.activiti.model;

import java.util.List;

public class TaskActionRequestObject {
	private String action;
	private String assignee;
	private List<VariableObject> variables;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public List<VariableObject> getVariables() {
		return variables;
	}

	public void setVariables(List<VariableObject> variables) {
		this.variables = variables;
	}

}

package cn.ezid.cert.api.model.rest;

import java.util.List;

public class ProcessRequestObject {
	private String processDefinitionKey;
	private String businessKey;
	private List<VariableObject> variables;

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public List<VariableObject> getVariables() {
		return variables;
	}

	public void setVariables(List<VariableObject> variables) {
		this.variables = variables;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

}

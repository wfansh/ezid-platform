package cn.ezid.cert.core.activiti;

import cn.ezid.cert.core.activiti.model.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class TaskClient {

	private String engineUrl;
	private String user;
	private String password;

	private RestTemplate template;

	public TaskClient(String engineUrl, String user, String password) {
		this.engineUrl = engineUrl;
		this.user = user;
		this.password = password;

		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.user, this.password));

		HttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();

		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

		template = new RestTemplate(requestFactory);

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		template.setMessageConverters(messageConverters);

	}

	public TaskObject getTask(String taskId) {
		return template.getForObject(engineUrl + "runtime/tasks/{taskId}", TaskObject.class, taskId);
	}

	public TaskListObject getTasksByQueryString(String queryString, Map<String, String> vars) {
		return template.getForObject(engineUrl + "runtime/tasks?" + queryString, TaskListObject.class, vars);
	}
	
	public TaskListObject getTasksByQueryString(String queryString) {
		return template.getForObject(engineUrl + "runtime/tasks?" + queryString, TaskListObject.class);
	}
	
	public TaskListObject getTasksByCandidateGroup(String candidateGroup) {
		Map<String, String> vars = new HashMap<>();
		vars.put("candidateGroup", candidateGroup);
		vars.put("sort", "dueDate");
		vars.put("order", "desc");
		vars.put("active", "true");
		return template
				.getForObject(
						engineUrl
								+ "runtime/tasks?candidateGroup={candidateGroup}&sort={sort}&order={order}&active={active}&start=0&size=50",
						TaskListObject.class, vars);
	}

	public TaskListObject getTasksByOwner(String owner) {
		Map<String, String> vars = new HashMap<>();
		vars.put("owner", owner);
		vars.put("sort", "dueDate");
		vars.put("order", "desc");
		vars.put("active", "true");
		return template.getForObject(engineUrl
				+ "runtime/tasks?owner={owner}&sort={sort}&order={order}&active={active}&start=0&size=10",
				TaskListObject.class, vars);
	}


	public void claimTask(String taskId) {
		TaskActionRequestObject request = new TaskActionRequestObject();
		request.setAction("claim");
		request.setAssignee(user);
		template.postForObject(engineUrl + "runtime/tasks/{taskId}", request, TaskObject.class, taskId);
	}
	
	public void claimTask(String taskId, String userId) {
		TaskActionRequestObject request = new TaskActionRequestObject();
		request.setAction("claim");
		request.setAssignee(userId);
		template.postForObject(engineUrl + "runtime/tasks/{taskId}", request, TaskObject.class, taskId);
	}

	public void unclaimTask(String taskId) {
		TaskActionRequestObject request = new TaskActionRequestObject();
		request.setAction("claim");
		request.setAssignee(null);
		template.postForObject(engineUrl + "runtime/tasks/{taskId}", request, TaskObject.class, taskId);
	}

	public void completeTask(String taskId, List<VariableObject> vars) {
		TaskActionRequestObject request = new TaskActionRequestObject();
		request.setAction("complete");
		request.setVariables(vars);
		template.postForObject(engineUrl + "runtime/tasks/{taskId}", request, TaskObject.class, taskId);
	}

	public VariableObject getTaskVariable(String taskId, String variableName) {
		try {
			return template.getForObject(engineUrl + "runtime/tasks/{taskId}/variables/{variableName}",
					VariableObject.class, taskId, variableName);
		} catch (RestClientException e) {
			return null;
		}
	}

	public List<VariableObject> getTaskVariables(String taskId) {
		VariableObject[] variables = template.getForObject(engineUrl + "runtime/tasks/{taskId}/variables",
				VariableObject[].class, taskId);
		return Arrays.asList(variables);
	}

	public void createTaskVariable(String taskId, VariableObject variable) {
		this.createTaskVariables(taskId, Collections.singletonList(variable));
	}

	public void createTaskVariables(String taskId, List<VariableObject> variables) {
		template.postForObject(engineUrl + "runtime/tasks/{taskId}/variables", variables, String.class, taskId);
	}

	public void updateTaskVariable(String taskId, VariableObject variable) {
		template.put(engineUrl + "runtime/tasks/{taskId}/variables/{variableName}", variable, taskId,
				variable.getName());
	}
	
	public void createTaskDescription(String taskId, final String description) {
		TaskDescriptionRequestObject request = new TaskDescriptionRequestObject(description);
		template.put(engineUrl + "runtime/tasks/{taskId}", request, taskId);
	}

	public void createTaskJournal(String taskId, String title, String content) {
		// Mark time stamp
		String journal = title + "--" + content + "@" + System.currentTimeMillis();
		
		VariableObject variable = this.getTaskVariable(taskId, TaskConstants.OUT_VAR_TASK_JOURNAL);
		
		if (variable == null) {
			this.createTaskVariable(taskId, new VariableObject(TaskConstants.OUT_VAR_TASK_JOURNAL, journal));
		} else {
			String str = variable.getValue().toString();
			this.updateTaskVariable(taskId, new VariableObject(TaskConstants.OUT_VAR_TASK_JOURNAL, str + ";" + journal));
		}
	}
	
	public HistoryTaskObject getHistoryTask(String taskId) {
		return template.getForObject(engineUrl + "history/historic-task-instances/{taskId}", HistoryTaskObject.class, taskId);
	}
	
	public HistoryTaskListObject getHistoryTasksByQueryString(String queryString, Map<String, String> vars) {
		try {
			return template.getForObject(engineUrl + "history/historic-task-instances?" + queryString, HistoryTaskListObject.class, vars);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public HistoryTaskListObject getHistoryTasksByQueryString(String queryString) {
		try {
			return template.getForObject(engineUrl + "history/historic-task-instances?" + queryString, HistoryTaskListObject.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public VariableObject getHistoryTaskVariable(String taskId, String variableName) {
		try {
			return template.getForObject(engineUrl + "history/historic-task-instances/{taskId}/variables/{variableName}",
					VariableObject.class, taskId, variableName);
		} catch (RestClientException e) {
			return null;
		}
	}

	public List<VariableObject> getHistoryTaskVariables(String taskId) {
		VariableObject[] variables = template.getForObject(engineUrl + "history/historic-task-instances/{taskId}/variables",
				VariableObject[].class, taskId);
		return Arrays.asList(variables);
	}
	
	public void updateHistoryTaskDescription(String taskId, final String description) {
		TaskDescriptionRequestObject request = new TaskDescriptionRequestObject(description);
		template.put(engineUrl + "history/historic-task-instances/{taskId}", request, taskId);
	}


	public void updateHistoryTaskVariable(String taskId, VariableObject variable) {
		template.put(engineUrl + "history/historic-task-instances/{taskId}/variables/{variableName}", variable, taskId,
				variable.getName());
	}

	public void updateHistoryTaskJournal(String taskId, String title, String content) {
		// Mark time stamp
		String journal = title + "--" + content + "@" + System.currentTimeMillis();

		VariableObject variable = this.getHistoryTaskVariable(taskId, TaskConstants.OUT_VAR_TASK_JOURNAL);
		if (variable == null) {
			return;
		} else {
			String str = variable.getValue().toString();
			this.updateHistoryTaskVariable(taskId, new VariableObject(TaskConstants.OUT_VAR_TASK_JOURNAL, str + ";" + journal));
		}
	}
	
	public void completeHistoryTask(String taskId) {
		HistoryTaskActionRequestObject request = new HistoryTaskActionRequestObject();
		request.setAction("complete");
		template.postForObject(engineUrl + "history/historic-task-instances/{taskId}", request, HistoryTaskObject.class, taskId);
	}

	
	public GroupListObject getGroupsByMember(String member) {
		return template.getForObject(engineUrl + "identity/groups?member={member}", GroupListObject.class, member);
	}

	public UserListObject getUsersByGroup(String group) {
		return template.getForObject(engineUrl + "identity/users?memberOfGroup={group}", UserListObject.class, group);
	}

	public String getEngineUrl() {
		return engineUrl;
	}

	public void setEngineUrl(String engineUrl) {
		this.engineUrl = engineUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RestTemplate getTemplate() {
		return template;
	}

	public void setTemplate(RestTemplate template) {
		this.template = template;
	}
}

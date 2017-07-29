package cn.ezid.cert.core.topo.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import cn.ezid.cert.core.topo.MappingStatus;

public class TopoMapping {

	private long id;

	private Resource module;
	private EngineResource engine;
	private TaskExecutorResource taskExecutor;

	private String userName;
	private String password;

	private MappingStatus status;
	private String description;

	public TopoMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TopoMapping(long id, Resource module, EngineResource engine, TaskExecutorResource taskExecutor,
			String userName, String password, MappingStatus status, String description) {
		super();
		this.id = id;
		this.module = module;
		this.engine = engine;
		this.taskExecutor = taskExecutor;
		this.userName = userName;
		this.password = password;
		this.status = status;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Resource getModule() {
		return module;
	}

	public void setModule(Resource module) {
		this.module = module;
	}

	public EngineResource getEngine() {
		return engine;
	}

	public void setEngine(EngineResource engine) {
		this.engine = engine;
	}

	public TaskExecutorResource getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutorResource taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MappingStatus getStatus() {
		return status;
	}

	public void setStatus(MappingStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

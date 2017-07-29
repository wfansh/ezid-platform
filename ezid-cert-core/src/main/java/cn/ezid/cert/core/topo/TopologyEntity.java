package cn.ezid.cert.core.topo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import cn.ezid.cert.core.entity.BaseEntity;

@Alias("TopologyEntity")
public class TopologyEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2633217360970055303L;

	private long id;
	private long moduleId;
	private long engineId;
	private long taskExecutorId;
	private String userName;
	private String password;
	private MappingStatus status;
	private String description;
	private Date timeExecuted;
	private Date timeUpdated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public long getEngineId() {
		return engineId;
	}

	public void setEngineId(long engineId) {
		this.engineId = engineId;
	}

	public long getTaskExecutorId() {
		return taskExecutorId;
	}

	public void setTaskExecutorId(long taskExecutorId) {
		this.taskExecutorId = taskExecutorId;
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

	public Date getTimeExecuted() {
		return timeExecuted;
	}

	public void setTimeExecuted(Date timeExecuted) {
		this.timeExecuted = timeExecuted;
	}

	public Date getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(Date timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

}

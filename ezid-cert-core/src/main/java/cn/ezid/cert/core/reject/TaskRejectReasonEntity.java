package cn.ezid.cert.core.reject;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import cn.ezid.cert.core.entity.BaseEntity;

@Alias("TaskRejectReasonEntity")
public class TaskRejectReasonEntity extends BaseEntity {

	private static final long serialVersionUID = 7111573161219845725L;

	private long id;
	private long parentId;
	private String name;
	private String fullName;
	private Date timeCreated;
	private Date timeUpdated;
	private List<TaskRejectReasonEntity> subReasonList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Date getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(Date timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

	public List<TaskRejectReasonEntity> getSubReasonList() {
		return subReasonList;
	}

	public void setSubReasonList(List<TaskRejectReasonEntity> subReasonList) {
		this.subReasonList = subReasonList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

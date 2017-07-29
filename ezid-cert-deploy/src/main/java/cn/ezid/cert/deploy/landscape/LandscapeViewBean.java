package cn.ezid.cert.deploy.landscape;

import java.util.Date;
import java.util.List;

import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.ResourceType;


/**
 * Merge Landscape Entity & Topology Entity into a single view
 * 
 * @author Fan
 *
 */
public class LandscapeViewBean {
	
	private long id;
	
	private long moduleId;
	private long engineId;
	private long taskExecutorId;

	private ResourceType resourceType;
	private String resourceName;
	private String url;
	private boolean enabled;

	private String userName;
	private String password;
	private MappingStatus status;
	private String description;
	private Date timeExecuted;
	private Date timeUpdated;
	
	private List<LandscapeViewBean> subLandscapeViews;

	private int subSize = 0;
	
	public String getLastTime() {
		if (timeExecuted!=null) {
			Date currentTime = new Date();
			long interval = (currentTime.getTime() - timeExecuted.getTime()) / 1000;// 秒
			long day = interval / (24 * 3600);		// 小时
			long hour = interval % (24 * 3600) / 3600;		// 小时
			long minute = interval % 3600 / 60;				// 分钟
			long second = interval % 60;					// 秒
			String msg = "";
			if (day > 0)
				msg = msg + day + "天";
			if (hour > 0)
				msg = msg + hour + "小时";
			if (minute > 0)
				msg = msg + minute + "分";
			if (second > 0)
				msg = msg + second + "秒";
			return msg+ " 前";
		}
		return "";
	}

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

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public List<LandscapeViewBean> getSubLandscapeViews() {
		return subLandscapeViews;
	}

	public void setSubLandscapeViews(List<LandscapeViewBean> subLandscapeViews) {
		this.subLandscapeViews = subLandscapeViews;
	}

	public int getSubSize() {
		subSize = getSubSize(subLandscapeViews);
		if (subSize == 0) {
			return 1;
		}
		
		return subSize;
	}

	private int getSubSize(List<LandscapeViewBean> subViews) {
		int size = 0;
		if (subViews != null) {
			for (LandscapeViewBean viewBean : subViews) {
				if (viewBean.getResourceType() == ResourceType.task_executor) {
					size ++;
				} else {
					size += getSubSize(viewBean.getSubLandscapeViews());
				}
			}
		}
		return size;
	}
}

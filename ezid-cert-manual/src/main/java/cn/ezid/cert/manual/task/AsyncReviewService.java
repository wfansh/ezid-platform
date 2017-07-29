package cn.ezid.cert.manual.task;

import java.util.List;

public interface AsyncReviewService {
	
	public static final int TASK_CACHE_CAPACITY = 10;

    public List<TaskViewBean> getReviewTasks(int size);
    
    public String completeTasks(String[] taskIds, String[] results, String[] comments);
    
    public void purgeUserCache(String user);
    
}

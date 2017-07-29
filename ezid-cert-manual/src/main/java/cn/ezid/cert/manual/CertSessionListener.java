package cn.ezid.cert.manual;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.ezid.cert.core.ApplicationContextHelper;
import cn.ezid.cert.core.activiti.TaskClient;
import cn.ezid.cert.manual.task.AsyncReviewService;

public class CertSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		TaskClient taskClient = (TaskClient)se.getSession().getAttribute(CertContext.SESSION_ATTR_TASK_CLIENT);
		if (taskClient != null) {
			ApplicationContextHelper.getBeanFactory().getBean(AsyncReviewService.class).purgeUserCache(taskClient.getUser());
		}
	}

}

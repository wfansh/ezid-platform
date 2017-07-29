package cn.ezid.cert.manual;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.ezid.cert.core.PropConfig;
import cn.ezid.cert.core.activiti.TaskClient;
import cn.ezid.cert.core.topo.ManualTopoFacade;
import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.model.ManualResource;
import cn.ezid.cert.core.topo.model.TaskExecutorType;
import cn.ezid.cert.core.topo.model.TopoMapping;
import cn.ezid.cert.core.util.EZUtils;
import cn.ezid.cert.manual.task.AsyncReviewService;

@Service
public class CertContextImpl implements CertContext {
	private static final Logger log = LoggerFactory.getLogger(CertContextImpl.class);

	@Autowired
	private ManualTopoFacade topoFacade;
	
	@Autowired
	private AsyncReviewService asyncReviewService;

	private static Map<String, ProcessDefinition> processDefinitions = new LinkedHashMap<>();
	
	private ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);

	public CertContextImpl() {			
		String list = PropConfig.getParameter("ProcessDefinition.List");
		if (EZUtils.isValidString(list)) {
			for (String str : list.split(";")) {
				String[] split = str.split(":");
				if (split.length == 2) {
					processDefinitions.put(split[0], new ProcessDefinition(split[0], split[1]));
				}
			}
		}

		threadPool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				CertContextImpl.this.sync();
			}

		}, 5, 5 * 60, TimeUnit.SECONDS);
	}

	
	@Override
	public void sync() {
		// TODO Auto-generated method stub
		if (!topoFacade.isSync()) {
			topoFacade.loadTopo();
		}
	}
	
	@Override
	public TopoMapping getConfig() {
		// TODO Auto-generated method stub
		return topoFacade.getServedMapping();
	}

	@Override
	public void stopWithError(String error) {
		// TODO Auto-generated method stub
		log.info("Manual cert is stopped with error {}", error);
		topoFacade.updateStatus(MappingStatus.ERROR, error);
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		ManualResource current = topoFacade.getCurrentManual();
		if (current == null || !current.isEnabled())
			return false;

		TopoMapping servedMapping = topoFacade.getServedMapping();
		if (servedMapping == null)
			return false;

		if (servedMapping.getStatus() != MappingStatus.ON || servedMapping.getEngine() == null
				|| !servedMapping.getEngine().isEnabled())
			return false;

		return true;
	}

	public TaskExecutorType getExecutorType() {
		return TaskExecutorType.manual;
	}


	/**
	 * Create a new TaskClient and put into Session scope
	 */
	@Override
	public TaskClient getTaskClient(String username, String password) {
		// TODO Auto-generated method stub
		if (!this.isEnabled())
			return null;
		
		TopoMapping config = this.getConfig();
		TaskClient taskClient = new TaskClient(config.getEngine().getUrl(), username, password);

		getSession().setAttribute(SESSION_ATTR_TASK_CLIENT, taskClient);
		
		return taskClient;
	}
	
	@Override
	public TaskClient getTaskClient() {
		return (TaskClient)getSession().getAttribute(SESSION_ATTR_TASK_CLIENT);
	}

	
	@Override
	public Map<String, ProcessDefinition> getProcessDefinitions() {
		return processDefinitions;
	}
	
	@Override
	public ProcessDefinition getProcessDefinition(String key) {
		return processDefinitions.get(key);
	}
	
	public void setProcessDefinition(String key) {
		// Clear async task cache when changing process definition
		asyncReviewService.purgeUserCache(this.getTaskClient().getUser());
		
		getSession().setAttribute(SESSION_ATTR_PROCESS_DEFINITION, processDefinitions.get(key));
	}

	public ProcessDefinition getProcessDefinition() {
		HttpSession session = getSession();
		ProcessDefinition processDefinition = (ProcessDefinition)session.getAttribute(SESSION_ATTR_PROCESS_DEFINITION);
		if (processDefinition != null) {
			return processDefinition;
		}
		
		String defaultKey = PropConfig.getParameter("ProcessDefinition.Default");
		processDefinition = processDefinitions.get(defaultKey);
		
		session.setAttribute(SESSION_ATTR_PROCESS_DEFINITION, processDefinition);
		
		return processDefinition;

	}
	
	private HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession();
	}
}

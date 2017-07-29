package cn.ezid.cert.deploy.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ezid.cert.core.NLSSupport;
import cn.ezid.cert.core.activiti.TaskClient;
import cn.ezid.cert.core.activiti.TaskConstants;
import cn.ezid.cert.core.topo.AppTopoFacade;
import cn.ezid.cert.core.topo.DeployTopoFacade;
import cn.ezid.cert.core.topo.LandscapeEntity;
import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.ResourceType;
import cn.ezid.cert.core.topo.TopologyEntity;
import cn.ezid.cert.core.util.EZUtils;
import cn.ezid.cert.deploy.landscape.LandscapeController;
import cn.ezid.cert.deploy.monitor.MonitorEvent.AlertLevel;

import com.fasterxml.jackson.databind.util.ISO8601Utils;

@Controller
@RequestMapping("/monitor")

public class MonitorController {
	private static final Logger log = LoggerFactory.getLogger(LandscapeController.class);

	@Autowired
	private DeployTopoFacade topoFacade;

    @Autowired
	private AppTopoFacade appTopoFacade;

	@RequestMapping(method = RequestMethod.GET, value = "listEngineIds")
	@ResponseBody
	public String listEngineIds() {
		List<LandscapeEntity> engines = topoFacade.getResourcesByType(ResourceType.engine);
		for (Iterator<LandscapeEntity> itr = engines.iterator(); itr.hasNext();) {
			LandscapeEntity engine = itr.next();
			if (!engine.isEnabled()) {
				itr.remove();
			}
		}
		String landscapes = "";
		for(int i = 0; i < engines.size(); i++) {
            if (i==engines.size()-1){
                landscapes += engines.get(i).getId()+":"+engines.get(i).getResourceName();
            }else {
                landscapes += engines.get(i).getId()+":"+engines.get(i).getResourceName()+",";
            }
		}
		return landscapes;
	}

	
	@RequestMapping(method=RequestMethod.GET, value = "getMonitorEvents", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MonitorEvent> getMonitorEvents(@RequestParam long engineId, @RequestParam String resourceType) {
		List<MonitorEvent> events = new ArrayList<>();
		
		LandscapeEntity engine = topoFacade.getResource(engineId);
		if (!engine.isEnabled()) {
			log.warn("Engine {} is disabled.", engineId);
			return events;
		}
		
		/**
		 * Tricky : Get user and password from either of the engine mappings
		 * Another solution : String userName= "admin"; String password = "admin";
		 */
		String userName = null;
		String password = null;
		for (TopologyEntity topoEntity : topoFacade.getMappingsByEngineId(engine.getId())) {
			if (EZUtils.isValidString(topoEntity.getUserName()) && EZUtils.isValidString(topoEntity.getPassword())) {
				userName = topoEntity.getUserName();
				password = topoEntity.getPassword();
				break;
			}
		}
		
		if (!EZUtils.isValidString(userName) || !EZUtils.isValidString(password)) {
			log.warn("Failed to monitor engine {}. User is {}, password is {}.", engineId, userName, password);
			return events;
		}
		
		TaskClient taskClient = new TaskClient(engine.getUrl(), userName, password);
		
		Date date = new Date(new Date().getTime());
		if (resourceType.equals(ResourceType.manual.name())) {
			String[] candidateGroups = new String[] { TaskConstants.GROUP_MANUAL_REVIEW,
					TaskConstants.GROUP_MANUAL_CERTIFICATION };
			// Error : Due date < now
			int errorCount = this.getTasksByCandidateGroupsAndDueBefore(taskClient, candidateGroups, date);
			if(errorCount > 0) {
				events.add(new MonitorEvent(ResourceType.manual, AlertLevel.Error, NLSSupport.getMessage("Monitor.Manual.Error", errorCount)));
			}
		} else if (resourceType.equals(ResourceType.app.name())) {
			String[] candidateGroups = new String[] { TaskConstants.GROUP_PHOTO_PREPROCESS,
					TaskConstants.GROUP_PHOTO_ADAPTER, TaskConstants.GROUP_MACHINE_CERTIFICATION };
			
			// Error : Due date < now
			int errorCount = this.getTasksByCandidateGroupsAndDueBefore(taskClient, candidateGroups, date);
			if(errorCount > 0) {
				events.add(new MonitorEvent(ResourceType.app, AlertLevel.Error, NLSSupport.getMessage("Monitor.Application.Error", errorCount)));
				
//				// Warning : Due date < now + 15 min
//				int warningCount = taskClient.getTasksByCandidateGroupsAndDueBefore(candidateGroups, new Date(date.getTime() + 15 * 60 * 1000)).getSize() - errorCount;
//		        if (warningCount > 0) {
//		        	events.add(new MonitorEvent(ResourceType.app, AlertLevel.Warning, NLSSupport.getMessage("Monitor.Application.Warning", warningCount)));
//		        	
//		        	// Info : Due date < now + 30 min
//					int infoCount = taskClient.getTasksByCandidateGroupsAndDueBefore(candidateGroups, new Date(date.getTime() + 30 * 60 * 1000)).getSize() - warningCount - errorCount;
//					if (infoCount > 0) {
//						events.add(new MonitorEvent(ResourceType.app, AlertLevel.Info, NLSSupport.getMessage("Monitor.Application.Info", infoCount)));
//					}
//		        }
			}
		}
		
		return events;
	}

    @RequestMapping(method = RequestMethod.GET, value = "getAppEvents")
    @ResponseBody
    public List<MonitorEvent> getAppEvents(@RequestParam long engineId) {
        List<MonitorEvent> events = new ArrayList<>();
        List<TopologyEntity> topologyEntities = topoFacade.getMappingsByEngineId(engineId);
        for (TopologyEntity topologyEntity : topologyEntities)
            if (topologyEntity.getStatus() == MappingStatus.ERROR) {
                MonitorEvent event = new MonitorEvent(ResourceType.app, AlertLevel.Error, topologyEntity.getDescription());
                event.setDate(topologyEntity.getTimeUpdated());
                events.add(event);
            }
        return events;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAppEventsTime", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getAppEventsTime(@RequestParam long engineId) {
        StringBuffer stringBuffer = new StringBuffer();
        List<TopologyEntity> topologyEntities = topoFacade.getMappingsByEngineId(engineId);
        for (TopologyEntity topologyEntity : topologyEntities){
            if (topologyEntity.getTimeExecuted() != null&&(new Date().getTime() - topologyEntity.getTimeExecuted().getTime())/1000 > 600) {
                LandscapeEntity resource = topoFacade.getResource(topologyEntity.getModuleId());
                stringBuffer.append(resource.getResourceName() + ",");
            }
        }
        return stringBuffer.toString();
    }
    
	private int getTasksByCandidateGroupsAndDueBefore(TaskClient taskClient, String[] candidateGroups, Date dueBefore) {
        Map<String, String> vars = new HashMap<>();
        vars.put("dueBefore", ISO8601Utils.format(dueBefore));
        vars.put("taskDefinitionKeyLike", "manual_%");
        return  taskClient.getTasksByQueryString("active=true&size=0&dueBefore={dueBefore}&taskDefinitionKeyLike={taskDefinitionKeyLike}", vars).getTotal();
    }

}
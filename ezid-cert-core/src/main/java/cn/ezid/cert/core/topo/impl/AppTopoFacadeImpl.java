package cn.ezid.cert.core.topo.impl;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.core.topo.AppTopoFacade;
import cn.ezid.cert.core.topo.LandscapeEntity;
import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.ResourceType;
import cn.ezid.cert.core.topo.TopoService;
import cn.ezid.cert.core.topo.TopologyEntity;
import cn.ezid.cert.core.topo.model.AppResource;
import cn.ezid.cert.core.topo.model.EngineResource;
import cn.ezid.cert.core.topo.model.TaskExecutorResource;
import cn.ezid.cert.core.topo.model.TaskExecutorType;
import cn.ezid.cert.core.topo.model.TopoMapping;

@Service
public class AppTopoFacadeImpl implements AppTopoFacade {

	private Logger log = LoggerFactory.getLogger(AppTopoFacadeImpl.class);

	private AppResource app;
	private Map<TaskExecutorType, TopoMapping> servedMappings;
	private Date lastUpdate;

	@Autowired
	private TopoService topoService;

	@Override
	public boolean isSync() {
		// TODO Auto-generated method stub
		if (lastUpdate == null || app == null || servedMappings == null || servedMappings.isEmpty())
			return false;

		if (topoService.isResourceModified(app.getId(), lastUpdate))
			return false;

		for (TopoMapping servedMapping : servedMappings.values()) {
			if (topoService.isResourceModified(servedMapping.getEngine().getId(), lastUpdate))
				return false;
		}
		
		if (topoService.countModifiedMappingByModuleId(app.getId(), lastUpdate) > 0)
			return false;

		return true;
	}

	@Override
	public void loadTopo() {
		// TODO Auto-generated method stub

		// 1. Load app resource
		String host = null;

		/**
		 * Linux sample : /usr/local/apache-tomcat-7.0.47/webapps
		 * Windows sample : C:/Program Files/Apache Software Foundation/Tomcat 7.0
		 */
		String path = new File("").getAbsolutePath().replaceAll("\\\\", "/").replaceAll("/.$", "");

		try {
			host = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			log.error("Caught exception on loading topology.", e);
			return;
		}

		LandscapeEntity entity = null;
		for (LandscapeEntity le : topoService.getResourcesByUrl(host + ":" + path)) {
			if (le.getResourceType() == ResourceType.app) {
				entity = le;
				break;
			}
		}
		
		if (entity == null) {
			for (LandscapeEntity le : topoService.getResourcesByUrl(host)) {
				if (le.getResourceType() == ResourceType.app) {
					entity = le;
					break;
				}
			}
		}
		
		if (entity == null) {
			log.warn("Can not find app resource information for host {} path {}", host, path);
			return;
		}

		this.app = new AppResource(entity.getId(), entity.getResourceName(), entity.getUrl(), entity.isEnabled());

		// 2. Load topo mappings
		this.servedMappings = new HashMap<>();
		for (TopologyEntity te : topoService.getMappingsByModuleId(app.getId())) {
			LandscapeEntity engine = topoService.getResource(te.getEngineId());
			if (engine == null) {
				log.warn("Engine {} can not be found.", te.getEngineId());
				continue;
			}

			LandscapeEntity taskExecutor = topoService.getResource(te.getTaskExecutorId());
			if (taskExecutor == null) {
				log.warn("Task Executor {} can not be found.", te.getTaskExecutorId());
				continue;
			}

			EngineResource engineResource = new EngineResource(engine.getId(), engine.getResourceName(),
					engine.getUrl(), engine.isEnabled());
			TaskExecutorResource taskExecutorResource = new TaskExecutorResource(taskExecutor.getId(),
					taskExecutor.getResourceName());

			servedMappings.put(
					taskExecutorResource.getTaskExecutorType(),
					new TopoMapping(te.getId(), this.app, engineResource, taskExecutorResource, te.getUserName(), te
							.getPassword(), te.getStatus(), te.getDescription()));
		}
		
		// 3. Update lastUpdate
		this.lastUpdate = new Date();
	}

	@Override
	public AppResource getCurrentApp() {
		// TODO Auto-generated method stub
		return app;
	}

	@Override
	public Map<TaskExecutorType, TopoMapping> getServedMappings() {
		// TODO Auto-generated method stub
		return servedMappings;
	}

	@Override
	public void updateStatus(TaskExecutorType taskExecutorType, MappingStatus status, String description) {
		// TODO Auto-generated method stub
		log.info("Update topo mapping status for {}, status is {} {}", taskExecutorType, status, description);

		TopoMapping mapping = servedMappings.get(taskExecutorType);
		mapping.setStatus(status);
		mapping.setDescription(description);

		topoService.updateMappingStatus(mapping.getId(), status, description);
	}

	@Override
	public void updateExecutedTime(TaskExecutorType taskExecutorType) {
		// TODO Auto-generated method stub
		Date executed = new Date();
		log.debug("Update topo mapping executed time to {}", executed);

		topoService.updateMappingExecutedTime(servedMappings.get(taskExecutorType).getId());
	}

}

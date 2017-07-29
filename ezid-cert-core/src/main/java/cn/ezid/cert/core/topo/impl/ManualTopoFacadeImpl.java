package cn.ezid.cert.core.topo.impl;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.core.topo.LandscapeEntity;
import cn.ezid.cert.core.topo.ManualTopoFacade;
import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.ResourceType;
import cn.ezid.cert.core.topo.TopoService;
import cn.ezid.cert.core.topo.TopologyEntity;
import cn.ezid.cert.core.topo.model.EngineResource;
import cn.ezid.cert.core.topo.model.ManualResource;
import cn.ezid.cert.core.topo.model.TopoMapping;

@Service
public class ManualTopoFacadeImpl implements ManualTopoFacade {
	private Logger log = LoggerFactory.getLogger(ManualTopoFacadeImpl.class);

	private ManualResource manual;
	private TopoMapping servedMapping;
	private Date lastUpdate;
	
	@Autowired
	private TopoService topoService;

	@Override
	public boolean isSync() {
		// TODO Auto-generated method stub
		if (lastUpdate == null || manual == null || servedMapping == null)
			return false;

		if (topoService.isResourceModified(manual.getId(), lastUpdate))
			return false;

		if (topoService.isResourceModified(servedMapping.getEngine().getId(), lastUpdate))
			return false;

		if (topoService.countModifiedMappingByModuleId(manual.getId(), lastUpdate) > 0)
			return false;

		return true;
	}

	@Override
	public void loadTopo() {
		// TODO Auto-generated method stub

		// 1. Load manual resource
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
			if (le.getResourceType() == ResourceType.manual) {
				entity = le;
				break;
			}
		}
		
		if (entity == null) {
			for (LandscapeEntity le : topoService.getResourcesByUrl(host)) {
				if (le.getResourceType() == ResourceType.manual) {
					entity = le;
					break;
				}
			}
		}
		
		if (entity == null) {
			log.warn("Can not find manual resource information for host {} path {}", host, path);
			return;
		}

		this.manual = new ManualResource(entity.getId(), entity.getResourceName(), entity.getUrl(), entity.isEnabled());

		// 2. Load topo mapping
		for (TopologyEntity te : topoService.getMappingsByModuleId(manual.getId())) {
			LandscapeEntity engine = topoService.getResource(te.getEngineId());
			if (engine == null) {
				log.warn("Engine {} can not be found.", te.getEngineId());
				continue;
			}

			EngineResource engineResource = new EngineResource(engine.getId(), engine.getResourceName(),
					engine.getUrl(), engine.isEnabled());

			this.servedMapping = new TopoMapping(te.getId(), this.manual, engineResource, null, te.getUserName(),
					te.getPassword(), te.getStatus(), te.getDescription());
		}
		
		// 3. Update lastUpdate
		this.lastUpdate = new Date();
	}

	@Override
	public ManualResource getCurrentManual() {
		// TODO Auto-generated method stub
		return manual;
	}

	@Override
	public TopoMapping getServedMapping() {
		// TODO Auto-generated method stub
		return servedMapping;
	}

	@Override
	public void updateStatus(MappingStatus status, String description) {
		// TODO Auto-generated method stub
		log.info("Update topo mapping status, status is {} {}", status, description);

		servedMapping.setStatus(status);
		servedMapping.setDescription(description);

		topoService.updateMappingStatus(servedMapping.getId(), status, description);
	}
}

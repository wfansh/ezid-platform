package cn.ezid.cert.core.topo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:root-test-context.xml" })
public class TopoServiceTest {

	@Autowired
	private TopoService topoService;
	
	@Test
	public void testGetResource() {
//		System.out.println(topoService.getResource(1));
	}

	@Test
	public void testGetAllResources() {
//		System.out.println(topoService.getAllResources());
//		System.out.println(topoService.getAllResources().size());
	}

	@Test
	public void testGetResourceByType() {
//		System.out.println(topoService.getResourcesByType(ResourceType.task_executor));
	}
	@Test
	public void testGetResourceByUrl() {
//		System.out.println(topoService.getResourceByUrl("http://192.168.1.33:8081/activiti-rest/service/"));
//		System.out.println(topoService.getResourceByUrl("192.168.1.33"));
//		System.out.println(topoService.getResourceByUrl("192.168.1.33:C:/Program Files/Apache Software Foundation/Tomcat 7.0"));
//		System.out.println(topoService.getResourceByUrl(""));
//		System.out.println(topoService.getResourceByUrl("null"));
	}

	@Test
	public void testInsertResource() {
//		topoService.insertResource(ResourceType.app, "app-3", null, true);
//		topoService.insertResource(ResourceType.engine, "engine-1", "url", false);

	}

	@Test
	public void testDeleteResource() {
//		topoService.deleteResource(7);
	}

	@Test
	public void testEnableResource() {
//		topoService.enableResource(1, false);
//		topoService.enableResource(2, true);
//		topoService.enableResource(8, true);
		
	}

	@Test
	public void testUpdateResourceUpdTime() {
//		topoService.updateResourceUpdTime(3);
	}

	@Test
	public void testIsResourceModified() {
//		System.out.println(topoService.isResourceModified(1, new Date()));
//		System.out.println(topoService.isResourceModified(1, null));
//		System.out.println(topoService.isResourceModified(10, new Date()));
//		System.out.println(topoService.isResourceModified(2, new Date()));
	}
	
	@Test
	public void testGetMapping() {
//		System.out.println(topoService.getMapping(1));
//		System.out.println(topoService.getMapping(6));
//		System.out.println(topoService.getMapping(8));
//		
	}

	@Test
	public void testGetAllMappings() {
//		System.out.println(topoService.getAllMappings());
//		System.out.println(topoService.getAllMappings().size());
	}

	@Test
	public void testGetMappingsByModuleId() {
//		System.out.println(topoService.getMappingsByModuleId(7));
//		System.out.println(topoService.getMappingsByModuleId(7).size());
//		System.out.println(topoService.getMappingsByModuleId(8));
//		System.out.println(topoService.getMappingsByModuleId(8).size());
	}

	@Test
	public void testGetMappingsByEngineId() {
//		System.out.println(topoService.getMappingsByEngineId(4));
//		System.out.println(topoService.getMappingsByEngineId(4).size());
//		System.out.println(topoService.getMappingsByEngineId(6));
//		System.out.println(topoService.getMappingsByEngineId(6).size());
	}

	@Test
	public void testInsertMapping() {
//		topoService.insertMapping(4, 5, 6, "u", "pwd", MappingStatus.ERROR);
	}

	@Test
	public void testDeleteMapping() {
//		topoService.deleteMapping(6);
//		topoService.deleteMapping(7);
	}

	@Test
	public void testUpdateMappingStatus() {
//		topoService.updateMappingStatus(1, MappingStatus.ERROR, "ADF");
//		topoService.updateMappingStatus(8, MappingStatus.ERROR, "ADF");
	}

	@Test
	public void testUpdateMappingUpdTime() {
//		topoService.updateMappingUpdTime(1);
//		topoService.updateMappingUpdTime(10);
	}

	@Test
	public void testUpdateMappingUpdTimeByModuleId() {
//		topoService.updateMappingUpdTimeByModuleId(6);
//		topoService.updateMappingUpdTimeByModuleId(10);
	}

	@Test
	public void testUpdateMappingUpdTimeByEngineId() {
//		topoService.updateMappingUpdTimeByEngineId(4);
//		topoService.updateMappingUpdTimeByEngineId(10);
	}

	@Test
	public void testUpdateMappingExecutedTime() {
//		topoService.updateMappingExecutedTime(1);
//		topoService.updateMappingExecutedTime(10);	
	}
	
	@Test
	public void testCountModifiedMappingByModuleId() {
//		System.out.println(topoService.countModifiedMappingByModuleId(6, null));
//		System.out.println(topoService.countModifiedMappingByModuleId(6, new Date()));
//		System.out.println(topoService.countModifiedMappingByModuleId(8, new Date()));
	}

	@Test
	public void testCountModifiedMappingByEngineId() {
//		System.out.println(topoService.countModifiedMappingByEngineId(5, null));
//		System.out.println(topoService.countModifiedMappingByEngineId(5, new Date()));
//		System.out.println(topoService.countModifiedMappingByEngineId(8, new Date()));
	}
}

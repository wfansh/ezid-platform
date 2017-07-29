package cn.ezid.cert.core.topo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:root-test-context.xml" })
public class AppTopoFacadeTest {

	@Autowired
	private AppTopoFacade topoFacade;
	
	@Test
	public void testIsSync() {
//		System.out.println(topoFacade.isSync());
	}

	@Test
	public void testLoadTopo() {
//		System.out.println(topoFacade.isSync());
//		topoFacade.loadTopo();
//		System.out.println(topoFacade.isSync());
	}

	@Test
	public void testGetCurrentApp() {
//		topoFacade.loadTopo();
//		System.out.println(topoFacade.getCurrentApp());
	}

	@Test
	public void testGetServedMappings() {
//		topoFacade.loadTopo();
//		System.out.println(topoFacade.getServedMappings());
//		System.out.println(topoFacade.getServedMappings().size());
	}

	@Test
	public void testUpdateStatus() {
//		System.out.println(topoFacade.isSync());
//		topoFacade.loadTopo();
//		System.out.println(topoFacade.isSync());
//		topoFacade.updateStatus(TaskExecutorType.preprocess, MappingStatus.ERROR, "error");
//		System.out.println(topoFacade.isSync());
//		System.out.println(topoFacade.getServedMappings().get(TaskExecutorType.preprocess).getStatus());
		
	}

	@Test
	public void testUpdateExecutedTime() {
//		System.out.println(topoFacade.isSync());
//		topoFacade.loadTopo();
//		System.out.println(topoFacade.isSync());
//		topoFacade.updateExecutedTime(TaskExecutorType.preprocess);
//		System.out.println(topoFacade.isSync());
//		System.out.println(topoFacade.getServedMappings().get(TaskExecutorType.preprocess).getStatus());
	}

}

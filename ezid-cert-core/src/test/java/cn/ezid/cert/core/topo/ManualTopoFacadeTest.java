package cn.ezid.cert.core.topo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:root-test-context.xml" })
public class ManualTopoFacadeTest {

	@Autowired
	private ManualTopoFacade topoFacade;
	
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
//		System.out.println(topoFacade.getCurrentManual());
	}

	@Test
	public void testGetServedMapping() {
//		topoFacade.loadTopo();
//		System.out.println(topoFacade.getServedMapping());
	}

	@Test
	public void testUpdateStatus() {
//		System.out.println(topoFacade.isSync());
//		topoFacade.loadTopo();
//		System.out.println(topoFacade.isSync());
//		topoFacade.updateStatus(MappingStatus.ERROR, "error");
	}
}

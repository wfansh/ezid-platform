package cn.ezid.cert.app.nls;

import org.junit.Ignore;
import org.junit.Test;

import cn.ezid.cert.core.NLSSupport;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:root-test-context.xml" })
@Ignore
public class NLSTest {

	@Test
	public void testNLS() {
		System.out.println(NLSSupport.getMessage("Comment.Preprocess.Success"));
		System.out.println(NLSSupport.getMessage("Comment.Preprocess.PersonPhotoNotExist"));
		System.out.println(NLSSupport.getMessage("Comment.Preprocess.Success"));

		System.out.println(NLSSupport.getMessage("Comment.Machine.PersonPhotoNotExist"));
		System.out.println(NLSSupport.getMessage("Comment.Machine.FaceNotDetected"));
		System.out.println(NLSSupport.getMessage("Comment.Machine.IdcardPhotoNotExist"));
		System.out.println(NLSSupport.getMessage("Comment.Machine.Success", 99l));

		System.out.println(NLSSupport.getMessage("Comment.Adapter.InfoIncomplete"));
		System.out.println(NLSSupport.getMessage("Comment.Adapter.Success"));
	}

}

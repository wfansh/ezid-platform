package cn.ezid.cert.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CertClientTest {
	private	CertClient client = new CertClient("http://115.28.140.20:8080//activiti-rest/service/", "admin", "admin");

	@Test
	public void testCreateCert() {
		Map<String, Object> props = new HashMap<>();
		props.put(CertClientConstants.IN_VAR_PERSON_IDCARD_NUM, "130622198405218013");
		props.put(CertClientConstants.IN_VAR_PERSON_NAME, "王帆");
		props.put(CertClientConstants.IN_VAR_PERSON_PHOTO, "idcardphoto/2014/20140608/89/130622198405218013_1402240498290.jpg");
		props.put(CertClientConstants.IN_VAR_MACHINE_CERTIFICATION_THRESHOLD, 50);
		props.put(CertClientConstants.IN_VAR_BUSINESS_TYPE, "test");
		props.put(CertClientConstants.IN_VAR_PERSON_IDCARD_PRINT, "");
		System.out.println(client.createCert("socialsecProcess", "http://127.0.0.1:8080/ezid-socialsec-engine-connection/callback?serviceUnit=anhui", props));
	}
	
	@Test
	public void testGetCertResult() {
		String instanceId = "621a4009-0062-11e4-868f-201a065bdc8c";
		System.out.println(client.getCertResult(instanceId).getStatus());
		System.out.println(client.getCertResult(instanceId).getDesc());
		System.out.println(client.getCertResult(instanceId).getProps());
	}
	
	@Test
	public void testDeleteCert() {
		System.out.println(client.deleteCert("a7131f80-16fe-11e4-b926-00163e00115d"));
	}
}

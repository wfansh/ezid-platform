package com.ezid.cert.test;

import java.util.HashMap;
import java.util.Map;

import com.ezid.cert.api.CertClient;

public class MockSocialSecCertCreator extends Thread {
	
	private CertClient certClient = new CertClient(CertEngineTester.URL, CertEngineTester.USER_NAME, CertEngineTester.PASSWORD);
	@Override
	public void run() {
		int MAX = 10;
		int count = 0;
		while (count++ < MAX) {
			long startTime = System.currentTimeMillis();
			Map<String, Object> props = new HashMap<String, Object>();
			props.put("machineCertificationThreshold", 50);
			props.put("certFormId", 1);
			props.put("personIdcardNum", "330111293874653678");
			props.put("personName", "wxj");
			props.put("personPhoto", "photo");
			props.put("personVideo", "video");
			String result = certClient.createCert("socialsecProcess:1:84", "http://www.ezid.cn/certcallback", props);
			System.out.println(result + " ->" + (System.currentTimeMillis() - startTime));
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
}

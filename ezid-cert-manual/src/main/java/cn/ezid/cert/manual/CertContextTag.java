package cn.ezid.cert.manual;

import java.util.Map;

import cn.ezid.cert.core.ApplicationContextHelper;

public class CertContextTag {
	private static CertContext certContext = ApplicationContextHelper.getBeanFactory().getBean(CertContext.class);
	
	public static Map<String, ProcessDefinition> getProcessDefinitions() {
		return certContext.getProcessDefinitions();
	}
	
	public static ProcessDefinition getProcessDefinition(String key) {
		return certContext.getProcessDefinition(key);
	}
	
	public static ProcessDefinition getProcessDefinition() {
		return certContext.getProcessDefinition();
	}
}

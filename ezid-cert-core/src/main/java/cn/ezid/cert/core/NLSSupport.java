package cn.ezid.cert.core;

public class NLSSupport {
	
	public static String getMessage(String code) {
		return ApplicationContextHelper.getApplicationContext().getMessage(code, null, null);
	}
	
	
	public static String getMessage(String code, Object... args) {
		return ApplicationContextHelper.getApplicationContext().getMessage(code, args, null);
	}
	
}

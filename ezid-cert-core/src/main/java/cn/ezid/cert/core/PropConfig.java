package cn.ezid.cert.core;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import cn.ezid.cert.core.util.EZUtils;

@Configuration
public class PropConfig {
	private static Properties prop = new Properties();
	
	@Bean
	public static PropertyPlaceholderConfigurer localProperties() {
		PropertyPlaceholderConfigurer ppc = new Configurer();
		ppc.setIgnoreUnresolvablePlaceholders(true);
		ppc.setIgnoreResourceNotFound(true);
		
		ppc.setLocation(new ClassPathResource("system-config.properties"));
		ppc.setOrder(0);;
		return ppc;
	}
	
	@Bean
	public static PropertyPlaceholderConfigurer globalProperties() {
		PropertyPlaceholderConfigurer ppc = new Configurer();
		ppc.setIgnoreUnresolvablePlaceholders(true);
		ppc.setIgnoreResourceNotFound(true);
		
		String globalConfig = System.getenv("platform.global.config");
		if (globalConfig == null)
			globalConfig = System.getProperty("platform.global.config");
		if (globalConfig == null)
			globalConfig = "/var/ezid/platform/platform-system-config.properties";
		
		ppc.setLocation(new FileSystemResource(globalConfig));
		ppc.setOrder(-1);
		return ppc;
	}

	public static String getParameter(String key) {
		return prop.getProperty(key);
	}

	public static String getParameter(String key, String defaultValue) {
		return prop.getProperty(key, defaultValue);
	}

	public static int getParameterAsInt(String key, int defaultValue) {
		String tempValue = getParameter(key, "" + defaultValue);
		return Integer.parseInt(tempValue);
	}

	public static boolean getParameterAsBoolean(String key, boolean defaultValue) {
		String tmp = getParameter(key);
		if (!EZUtils.isValidString(tmp))
			return defaultValue;
		
		char b = tmp.charAt(0);
		switch (b) {
		case 'T':
		case 't':
		case '1':
		case 'Y':
		case 'y':
			return true;
		default:
			return false;
		}
	}

	public static long getParameterAsLong(String key, long defaultValue) {
		try {
			return Long.parseLong(getParameter(key));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	public static float getParameterAsFloat(String key, float defaultValue) {
		try {
			return Float.parseFloat(getParameter(key));
		} catch (Exception ex) {
			return defaultValue;
		}
	}
	
	public static double getParameterAsDouble(String key, double defaultValue) {
		try {
			return Double.parseDouble(getParameter(key));
		} catch (Exception ex) {
			return defaultValue;
		}
	}
		
	private static class Configurer extends PropertyPlaceholderConfigurer {
		@Override
		protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
				throws BeansException { 
	  
	        super.processProperties(beanFactory, props);     
	        for (Object key : props.keySet()) {
	        	if (!prop.containsKey(key))
	        		prop.put(key, props.get(key));
	        }
		}   
	}
}
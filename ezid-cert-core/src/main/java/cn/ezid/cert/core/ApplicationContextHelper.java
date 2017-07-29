package cn.ezid.cert.core;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class ApplicationContextHelper implements ApplicationContextAware,
		BeanFactoryAware, ServletContextAware {

	private static ApplicationContext applicationContext;
	private static BeanFactory beanFactory;
	private static ServletContext servletContext;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		ApplicationContextHelper.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		ApplicationContextHelper.applicationContext = applicationContext;
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		ApplicationContextHelper.servletContext = servletContext;
	}


	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	public static ServletContext getServletContext() {
		return servletContext;
	}
	
	public static UserDetails getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null)
			return null;

		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails)
			return (UserDetails) principal;
		return null;
		
	}
	
	public static boolean hasRole(String role) {
		UserDetails userDetails = getUserDetails();
		if (userDetails == null)
			return false;
		
		for (GrantedAuthority authority : userDetails.getAuthorities()){
			if (authority.getAuthority().equalsIgnoreCase(role))
				return true;
		}
		
		return false;
	}
}

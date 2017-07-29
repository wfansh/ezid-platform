package cn.ezid.cert.manual;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.ezid.cert.core.EzidException;
import cn.ezid.cert.core.NLSSupport;

public class CertContextInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LoggerFactory.getLogger(CertContextInterceptor.class);

	@Autowired
	private CertContext certContext;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		if (!certContext.isEnabled()) {
			certContext.sync();
		}
		
		if (!certContext.isEnabled()) {
			log.warn("Manual cert module is not enabled.");
			throw new EzidException(NLSSupport.getMessage("Exception.Module.Disabled"));
		}

		return true;
	}
}

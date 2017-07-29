package cn.ezid.cert.manual.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {
		// TODO Auto-generated method stub
		log.warn("Caught exception.", e);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorMsg", e.getMessage());
		return new ModelAndView("common/error", map);
	}
}

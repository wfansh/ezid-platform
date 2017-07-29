package cn.ezid.cert.deploy;

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
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		log.warn("Caught exception.", ex);
		ModelAndView mv = new ModelAndView("sys/error");
		mv.addObject("error", ex.getMessage());
		return new ModelAndView("sys/error");
	}
}

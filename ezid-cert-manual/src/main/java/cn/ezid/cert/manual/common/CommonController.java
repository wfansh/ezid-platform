package cn.ezid.cert.manual.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.ezid.cert.manual.CertContext;

@Controller
public class CommonController {
	@Autowired
	private CertContext certContext;
	
	@RequestMapping(method = RequestMethod.GET, value = {"login"})
	public String login() {
		return "common/login";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "changeProcessDefinition")
	public String changeProcessDefinition(String key) {
		certContext.setProcessDefinition(key);
		return "redirect:/task";
	}
}

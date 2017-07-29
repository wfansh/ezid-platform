package cn.ezid.cert.app.executor.adapter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ezid.cert.app.executor.adapter.AdaptException.ErrorType;
import cn.ezid.cert.app.executor.adapter.loong.AdapterServiceLoongImpl;
import cn.ezid.cert.core.history.HistoryService;

@Controller
public class AdapterController {
	private static final Logger log = LoggerFactory.getLogger(AdapterController.class);
	
	@Autowired
	private AdapterServiceLoongImpl adapterService;
	
	@Autowired
	private HistoryService historySerivce;
	
	@RequestMapping(value = "/identify-idcard-baocheng2015", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> identifyIdCard(@RequestParam String idNum, @RequestParam String name,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		log.debug("id number {}", idNum);
		log.debug("name {}", name);
		boolean debugMode = false;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			adapterService.getPhoto(idNum, name, true, debugMode);
			result.put("status", true);
			result.put("message", "认证通过");
		} catch (AdaptException e) {
			if (e.getErrorType() == ErrorType.PHOTO_UNFORMAT) {
				result.put("status", true);
				result.put("message", "认证通过");
			} else {
				result.put("status", false);
				result.put("message", e.getMessage());
//				if (!debugMode) {
//					historySerivce.insertInvalid(idNum, name, e.getTitle(), e.getMessage());
//				}
			}
			log.warn("Execute get photo task failed for idcardNum {} name {}. Caused by {}.", idNum, name, e.getMessage());
		} catch (Exception e) {
			log.error("Execute photo adapter task failed with exception", e);
			result.put("status", false);
			result.put("message", "内部错误");
		}
		return result;
	}
}

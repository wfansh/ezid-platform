package cn.ezid.cert.deploy.landscape;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.ezid.cert.core.NLSSupport;
import cn.ezid.cert.core.topo.LandscapeEntity;
import cn.ezid.cert.core.topo.MappingStatus;
import cn.ezid.cert.core.topo.ResourceType;

@Controller
@RequestMapping("/landscape")
public class LandscapeController {
	private static final Logger log = LoggerFactory.getLogger(LandscapeController.class);
		
	@Autowired
	private LandscapeService landscapeService;
	
	//取得所有的Landscape
	@RequestMapping(method = RequestMethod.GET, value = "")
	public String listLandscape(Model model) {
		log.info("list landscape action.");
		
		List<LandscapeViewBean> resourceList = landscapeService.getAllResources();
		List<LandscapeEntity> appList = landscapeService.getTaskExecutorsByModuleType(ResourceType.app);
		List<LandscapeEntity> manualList = landscapeService.getTaskExecutorsByModuleType(ResourceType.manual);
		
		model.addAttribute("resourceList", resourceList);
		model.addAttribute("appList", appList);
		model.addAttribute("manualList", manualList);
		
		log.info("list landscape success.");
		return "landscape.list";
	}
	
	//新增一个Landscape
	@RequestMapping(method = RequestMethod.POST, value = "addLandscape")
	public String addLandscape(
			@ModelAttribute("landscapeViewBean") LandscapeViewBean landscapeViewBean,
			RedirectAttributes redirectAttributes) {
		log.info("add landscape action.");
		
		String result = landscapeService.createResource(landscapeViewBean);
		
		if("".equals(result)){
			redirectAttributes.addFlashAttribute("alertMsgType", "success");  
			redirectAttributes.addFlashAttribute("alertMsgTitle", landscapeViewBean.getResourceType() + " " + NLSSupport.getMessage("Landscape.Resource.addSuccess")); 
			redirectAttributes.addFlashAttribute("alertMsgContent", landscapeViewBean.getResourceType() + ":" + landscapeViewBean.getResourceName() + NLSSupport.getMessage("Landscape.Resource.addSuccessMsg")); 
		}else {
			redirectAttributes.addFlashAttribute("alertMsgType", "danger");  
			redirectAttributes.addFlashAttribute("alertMsgTitle", NLSSupport.getMessage("Landscape.Resource.addFailed")); 
			redirectAttributes.addFlashAttribute("alertMsgContent", result); 
		}
		
		log.info("add landscape success.");
		return "redirect:/landscape";
	}
	
	//删除Landscape
	@RequestMapping(method = RequestMethod.GET, value = "deleteLandscape/{id}/{resourceType}")
	public String deleteLandscape(@PathVariable long id, @PathVariable ResourceType resourceType, RedirectAttributes redirectAttributes) {
		log.info("delete landscape action.");
		
		String result=landscapeService.deleteResource(resourceType, id);
		
		if("".equals(result)){
			redirectAttributes.addFlashAttribute("alertMsgType", "success");  
			redirectAttributes.addFlashAttribute("alertMsgTitle", NLSSupport.getMessage("Landscape.Resource.deleteSuccess"));  
			redirectAttributes.addFlashAttribute("alertMsgContent", resourceType + " " + NLSSupport.getMessage("Landscape.Resource.deleteSuccessMsg")); 
		}else{
			redirectAttributes.addFlashAttribute("alertMsgType", "danger");  
			redirectAttributes.addFlashAttribute("alertMsgTitle", NLSSupport.getMessage("Landscape.Resource.deleteFailed"));  
			redirectAttributes.addFlashAttribute("alertMsgContent", result); 
		}
		
		log.info("delete landscape success.");
		return "redirect:/landscape";		
	}

	
	//更新Landscape开关
	@RequestMapping(method = RequestMethod.POST, value = "updateLandscape", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JsonResult updateLandscape(long id, boolean status) {
		log.info("update landscape action.");
		
		JsonResult result= new JsonResult();
		if(status) {
			landscapeService.updateMappingStatus(id, MappingStatus.ON);
		} else {
			landscapeService.updateMappingStatus(id, MappingStatus.OFF);
		}
		result.setSuccess(true);
		Date currentDate=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result.setData(sdf.format(currentDate));
		
		log.info("update landscape action.");
		return result;
	}
}

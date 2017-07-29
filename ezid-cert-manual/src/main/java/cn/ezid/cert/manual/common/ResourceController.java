package cn.ezid.cert.manual.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.ezid.cert.core.oss.OSSService;
import cn.ezid.cert.core.util.EZUtils;
import cn.ezid.cert.core.util.ResourceEncoder;

import com.aliyun.openservices.oss.model.OSSObject;


@Controller
@RequestMapping(value = "/res")
public class ResourceController {
	private static final Logger log = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private OSSService ossService;
	
	@RequestMapping(method = RequestMethod.GET, value = {"/{prefix}.{suffix}"})
	public void getImageResoruce(@PathVariable String prefix, @PathVariable String suffix, HttpServletResponse response) {
		if (!EZUtils.isValidString(prefix) || !EZUtils.isValidString(suffix)) {
			log.warn("Invalid resource request prefix{}, suffix {}", prefix, suffix);
			return;
		}
		
		try {
			String path = ResourceEncoder.decode(prefix + "." + suffix);
			OSSObject ossObject = ossService.getObject(path);
			
			if (ossObject == null) {
				log.warn("Can not find resource path {}.", path);
			}
						
			switch (suffix.toLowerCase()) {
			case "png":
				response.setContentType(MediaType.IMAGE_PNG_VALUE);
				break;
			case "jpg":
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
				break;
			case "mp4":
				response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
				break;
			case "flv":
				response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
				break;
			default:
				response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			}
			
	        IOUtils.copy(ossObject.getObjectContent(), response.getOutputStream());
	        response.flushBuffer();
		} catch (IOException e) {
			log.info("Caught exception : {}.", e.getMessage());
			return ;
		} 
	}
}

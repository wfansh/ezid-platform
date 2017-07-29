package cn.ezid.cert.app.executor.adapter.nciic;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxy;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.codehaus.xfire.transport.http.EasySSLProtocolSocketFactory;
import org.codehaus.xfire.util.dom.DOMOutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.ezid.cert.app.executor.ExecutorException;
import cn.ezid.cert.app.executor.adapter.AdaptException;
import cn.ezid.cert.app.executor.adapter.AdaptException.ErrorType;
import cn.ezid.cert.app.executor.adapter.VendorService;
import cn.ezid.cert.app.executor.adapter.nciic.condition.Condition;
import cn.ezid.cert.app.executor.adapter.nciic.condition.ConditionRow;
import cn.ezid.cert.app.executor.adapter.nciic.condition.Info;
import cn.ezid.cert.app.executor.adapter.nciic.response.ExceptionalResponse;
import cn.ezid.cert.app.executor.adapter.nciic.response.Item;
import cn.ezid.cert.app.executor.adapter.nciic.response.Response;
import cn.ezid.cert.app.executor.adapter.nciic.response.ResponseRow;
import cn.ezid.cert.core.NLSSupport;
import cn.ezid.cert.core.PropConfig;
import cn.ezid.cert.core.util.EZUtils;

@Service
public class VendorServiceNciicImpl implements VendorService {

	private static final Logger log = LoggerFactory.getLogger(VendorServiceNciicImpl.class);

	private static final String LICENSE_FILE = "nciic-authorize.txt";
	private static final String SERVICE_URL = PropConfig.getParameter("Nciic.WS.Url", "https://api.nciic.com.cn/nciic_ws/services/");
	private static final String SERVICE_NAME = PropConfig.getParameter("Nciic.WS.Name", "NciicServices");
	
	
	private ServiceInf service;
	private Client client;
	
	private String licenseCode;
	private String sbm;
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	private Unmarshaller exceptionalUnmarshaller;

	
	public VendorServiceNciicImpl() {
		try {
			// 1. XFire
			ProtocolSocketFactory easy = new EasySSLProtocolSocketFactory();
			Protocol protocol = new Protocol("https", easy, PropConfig.getParameterAsInt("Nciic.WS.Port", 443));
			Protocol.registerProtocol("https", protocol);
			
			org.codehaus.xfire.service.Service serviceModel = new ObjectServiceFactory().create(ServiceInf.class, SERVICE_NAME, null, null);
			service = (ServiceInf) new XFireProxyFactory().create(serviceModel, SERVICE_URL + SERVICE_NAME);
			
			client = ((XFireProxy)Proxy.getInvocationHandler(service)).getClient();
			client.addOutHandler(new DOMOutHandler());
			client.setProperty(CommonsHttpMessageSender.GZIP_ENABLED, Boolean.TRUE);
			client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "1");  
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "0");
			
			// 2. Nciic
			BufferedReader in = null;
			if (System.getenv("ezid.nciic.authorize") != null) {
				in = new BufferedReader(new FileReader(System.getenv("ezid.nciic.authorize")));
			} else if (System.getProperty("ezid.nciic.authorize") != null) {
				in = new BufferedReader(new FileReader(System.getProperty("ezid.nciic.authorize")));
			} else if (new File("/var/ezid/" + LICENSE_FILE).exists()) {
				in = new BufferedReader(new FileReader(new File("/var/ezid/" + LICENSE_FILE)));
			} else {
				in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + LICENSE_FILE)));
			}
			
			licenseCode = in.readLine();
			in.close();
			sbm = PropConfig.getParameter("Nciic.Service.SBM", "shbcshbc46306");
			
			// 3. JAXB
	        marshaller = JAXBContext.newInstance(Condition.class).createMarshaller();  
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);  	  
	        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); 
	        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);  
	        unmarshaller = JAXBContext.newInstance(Response.class).createUnmarshaller();
	        exceptionalUnmarshaller = JAXBContext.newInstance(ExceptionalResponse.class).createUnmarshaller();
	        
		} catch (JAXBException | IOException e) {
			log.error("Failed to init external.", e);
			throw new ExecutorException(e);
		}
	}
	
	
	@Override
	public Image getPhoto(String idcardNum, String name, boolean photoIgnored) {
		// TODO Auto-generated method stub
		Info info = new Info(sbm);
		ConditionRow titleRow = new ConditionRow(
				PropConfig.getParameter("Nciic.Condition.TR.ID"), 
				PropConfig.getParameter("Nciic.Condition.TR.Name"));
		
		ConditionRow queryRow = new ConditionRow(
				PropConfig.getParameter("Nciic.Condition.FSD"),
				PropConfig.getParameter("Nciic.Condition.YWLX"),
				idcardNum,
				name);
		
		Condition condition = new Condition(info, Arrays.asList(titleRow, queryRow));
		
		String answer = null;
		try {
			StringWriter writer = new StringWriter();
			marshaller.marshal(condition, writer);
			answer = service.nciicCheck(licenseCode, writer.toString());
		} catch (JAXBException | RuntimeException e) {
			log.error("Failed to call externalCheck() for {} {}.", idcardNum, name);
			log.error("", e);
			throw new ExecutorException(e);
		}
		
		try {
			Response resp = (Response)unmarshaller.unmarshal(new StringReader(answer));
						
			String xp = null;
			String resultGmsfhm = null;
			String resultXm = null;
			
			for(Item item : resp.getRows().get(0).getItems()) {
				if (item.getErrormesage() != null) {
					log.warn("Error message {} for {} {}.", item.getErrormesage(), idcardNum, name);
					throw new AdaptException(NLSSupport.getMessage("Nciic.exception.title"), ErrorType.NOT_FOUND,
							item.getErrormesage());
				}
				
				if (item.getXp() != null)
					xp = item.getXp();
				
				if (item.getResultGmsfhm() != null)
					resultGmsfhm = item.getResultGmsfhm();
				
				if (item.getResultXm() != null)
					resultXm = item.getResultXm();
			}
			
			if (EZUtils.isValidString(resultGmsfhm) && EZUtils.isValidString(resultXm)
					&& resultGmsfhm.trim().equals(resultXm.trim())) {
				try {
					return decodeImage(xp);
				} catch (IOException e) {
					log.warn("Photo result for {} {} is {}", idcardNum, name, xp);
					
					if (photoIgnored) {
						return null;
					}
					
					throw new AdaptException(NLSSupport.getMessage("Nciic.exception.title"), ErrorType.PHOTO_UNFORMAT,
							NLSSupport.getMessage("Nciic.xp.unformatted"));
				}
			}
			
			log.warn("Inconsistent {} {}.", idcardNum, name);
			throw new AdaptException(NLSSupport.getMessage("Nciic.exception.title"), ErrorType.INCONSISTENT,
					NLSSupport.getMessage("Nciic.validation.inconsistent"));
		} catch (JAXBException e) {
			try {
				ExceptionalResponse excpResp = (ExceptionalResponse)exceptionalUnmarshaller.unmarshal(new StringReader(answer));
				ResponseRow row = excpResp.getRows().get(0);
				throw new ExecutorException(
						String.format("External exception for %s %s, %s %s.",
								idcardNum, name, row.getErrorCode(),
								row.getErrorMsg()));		
			} catch (JAXBException e1) {
				log.error("Failed to unmarshall external response for {} {}.", idcardNum, name);
				log.error("", e1);
				throw new ExecutorException(e1);
			}	
		}
	}	
	
	private Image decodeImage(String xp) throws IOException {
		if (!EZUtils.isValidString(xp))
			throw new IOException();
		
		xp = xp.replaceAll("\u0020","");
		xp = xp.replaceAll("\r\n","");
		
		@SuppressWarnings("restriction")
		byte[] byteBuffer = new sun.misc.BASE64Decoder().decodeBuffer(xp);
		InputStream in = new ByteArrayInputStream(byteBuffer);
		Image img = ImageIO.read(in);
		in.close();
		
		if (img == null)
			throw new IOException();
		
		return img;
	}

}

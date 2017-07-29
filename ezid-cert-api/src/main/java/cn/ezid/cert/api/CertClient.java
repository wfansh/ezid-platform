package cn.ezid.cert.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import cn.ezid.cert.api.model.CertResult;
import cn.ezid.cert.api.model.CertResult.CertStatus;
import cn.ezid.cert.api.model.rest.ProcessObject;
import cn.ezid.cert.api.model.rest.ProcessRequestObject;
import cn.ezid.cert.api.model.rest.VariableObject;
import cn.ezid.cert.callback.CallbackFailoverEntity;
import cn.ezid.cert.callback.CallbackFailoverService;
import cn.ezid.cert.callback.impl.CallbackFailoverServiceImpl;

public class CertClient {
	
	private static final Logger log = LoggerFactory.getLogger(CertClient.class);
	
	private String engineUrl;
	private String user;
	private String password;
	
	private RestTemplate template;
	
	private CallbackFailoverService callbackFailoverService;
	
	public CertClient(String engineUrl, String user, String password) {
		this.engineUrl = engineUrl;
		this.user = user;
		this.password = password;
		
	    BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
	    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.user, this.password));
	    
	    HttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
	    
	    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		
		template = new RestTemplate(requestFactory);
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		template.setMessageConverters(messageConverters);
		
		callbackFailoverService = new CallbackFailoverServiceImpl();
	}
	
	public String createCert(String certDefinitionKey, String callback, Map<String, Object> props) throws CertException {
		ProcessRequestObject request = new ProcessRequestObject();
		request.setProcessDefinitionKey(certDefinitionKey);
		
		List<VariableObject> vars = new ArrayList<>();
		for (String key: props.keySet()) {
			VariableObject var = new VariableObject(key, props.get(key));
			vars.add(var);
		}
		
		if (callback != null) {
			vars.add(new VariableObject(CertClientConstants.IN_VAR_CERT_CALLBACK, callback));
		}
		
		request.setVariables(vars);
	
		try {
			ProcessObject po = template.postForObject(engineUrl + "runtime/process-instances", request, ProcessObject.class);
			return po.getId();
		} catch (RuntimeException e) {
			log.warn(e.getMessage());
			throw new CertException(e);
		}		
	}
	
	public boolean deleteCert(String instanceId) {
		try {
			// There is not a runtime instance if completed
			template.delete(engineUrl + "runtime/process-instances/{instanceId}", instanceId);
		} catch (RuntimeException e) {
			log.debug(e.getMessage());
		}
		
		try {
			template.delete(engineUrl + "history/historic-process-instances/{processInstanceId}", instanceId);
		} catch (RuntimeException e) {
			log.debug(e.getMessage());
			return false;
		}
		
		return true;
	}
		
	public CertResult getCertResult(String instanceId) {
		return this.getCertResult(instanceId, false);
	}

	
	/**
	 * If a process instance is launched in runtime, a correlating history instance is also created 
	 * Only if the activiti EndListener executed, the runtime instance is deleted 
	 * 
	 * @param instanceId
	 * @param callback
	 * @return
	 */
	public CertResult getCertResult(String instanceId, boolean callback) {
		if (callback) {
			throw new CertException("Currently not supported when callback is true, caused by limitation of Activiti "
					+ "variables synchronization in the same thread with EndListener. "
					+ "Please start a simultaneous thread to invoke getCertResult() instead.");
		}
		
		try {
			// 1. Processing
			try {
				// When there is a processing instance
				template.getForObject(engineUrl + "runtime/process-instances/{instanceId}", ProcessObject.class, instanceId);
				return createCertResult(CertStatus.PROCESSING, Collections.<VariableObject> emptyList());
			} catch (RuntimeException e) {
				
			}

			try {
				// 2. Completed (parse history process instance)
				VariableObject[] variables = template.getForObject(engineUrl + "history/historic-process-instances/{instanceId}/variables", VariableObject[].class, instanceId);
				return createCertResult(Arrays.asList(variables));
			} catch (RuntimeException e) {
				// 3. No result
				return new CertResult(CertStatus.ERROR, "No result.");
			}
		} catch (RuntimeException e) {
			return new CertResult(CertStatus.ERROR, e.getMessage());
		}
	}
	
	public List<CallbackFailoverEntity> selectCallbackFailovers(String urlPattern) {
		try {
			return callbackFailoverService.selectByUrlPattern(urlPattern);
		} catch (NullPointerException e) {
			throw new CertException("Sql session not initialized.");
		}
		
	}
	
	public int deleteCallbackFailover(long id) {
		try {
			return callbackFailoverService.delete(id);
		} catch (NullPointerException e) {
			throw new CertException("Sql session not initialized.");
		}
	}
	
	public void setSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		callbackFailoverService.setSqlSessionTemplate(sqlSessionFactory);
	}
	
	
	/**
	 * @param variables
	 * @return
	 */
	private CertResult createCertResult(List<VariableObject> variables) {
		return this.createCertResult(null, variables);
	}
	
	/**
	 * @param certStatus
	 * @param variables
	 * @return
	 */
	private CertResult createCertResult(CertStatus certStatus, List<VariableObject> variables) {
		boolean result = false;
		int certainty = 100;
		String desc = null;
		Map<String, Object> props = new HashMap<>();
		
		for (VariableObject var : variables) {
			switch (var.getName()) {
			case CertClientConstants.OUT_VAR_TASK_RESULT:
				result = (Boolean)var.getValue();
				break;
			case CertClientConstants.OUT_VAR_TASK_RESULT_CERTAINTY:
				certainty = (Integer)var.getValue();
				break;
			case CertClientConstants.OUT_VAR_TASK_RESULT_DESC:
				desc = var.getValue().toString();
				break;
			default:
				if (var.getName().startsWith(CertClientConstants.VAR_PREFIX_OUT)) {
					props.put(var.getName(), var.getValue());
				}
				break;
			}
		}
		
		if (certStatus != null) {
			return new CertResult(certStatus, desc, props);
		}
		
		if (certainty <= 50) {
			return new CertResult(CertStatus.UNCERTAIN, desc, props);
		}
		
		if (result) {
			return new CertResult(CertStatus.SUCCESS, desc, props);
		} else {
			return new CertResult(CertStatus.FAIL, desc, props);
		}
	}
}

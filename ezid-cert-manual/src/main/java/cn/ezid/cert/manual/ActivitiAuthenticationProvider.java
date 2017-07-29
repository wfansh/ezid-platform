package cn.ezid.cert.manual;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.client.HttpClientErrorException;

import cn.ezid.cert.core.NLSSupport;
import cn.ezid.cert.core.activiti.TaskClient;
import cn.ezid.cert.core.activiti.model.GroupListObject;
import cn.ezid.cert.core.activiti.model.GroupObject;

public class ActivitiAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	private static final Logger log = LoggerFactory.getLogger(ActivitiAuthenticationProvider.class);
	
	@Autowired
	private CertContext certContext;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		if (!certContext.isEnabled()) {
			log.error("Current module is not enabled!");
			throw new SessionAuthenticationException(NLSSupport.getMessage("Exception.Module.Disabled"));
		}
					
		try {
			String password = authentication.getCredentials().toString();
			
			TaskClient client = certContext.getTaskClient(username, password);

			GroupListObject groups = client.getGroupsByMember(username);
	
			List<GrantedAuthority> auths = new ArrayList<>();
			for (GroupObject group : groups.getData()) {
				GrantedAuthority auth = new SimpleGrantedAuthority(group.getId());
				auths.add(auth);
			}
			
			return new User(username, password, true, true, true, true, auths);
		} catch (HttpClientErrorException e) {
			throw new BadCredentialsException(NLSSupport.getMessage("Exception.Credential.Invalid"));
		} 
	}
}

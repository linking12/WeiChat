package net.chat.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.chat.domain.Resources;
import net.chat.service.SecurityService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

@Service("securityMetadataSource")
public class SecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	@Autowired
	private SecurityService secutiryService;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resources> resources = this.secutiryService.getAllResource();
			for (Resources resource : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_"
						+ resource.getName());
				configAttributes.add(configAttribute);
				resourceMap.put(resource.getUrl(), configAttributes);
			}
		}

	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {

		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if (resourceMap == null)
			loadResourceDefine();
		return resourceMap.get("/"+StringUtils.substringBetween(requestUrl, "/",
				"/"));
	}
}

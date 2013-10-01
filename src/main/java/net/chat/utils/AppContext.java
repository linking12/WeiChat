/**
 * 
 */
package net.chat.utils;

import java.util.HashMap;
import java.util.Map;

import net.chat.security.UserDetailsDecorator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author apple
 * 
 */
public class AppContext {

	private static final Log logger = LogFactory.getLog(AppContext.class);

	private static final ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new ThreadLocal<Map<String, Object>>();

	private AppContext() {

	}

	/**
	 * get user id from spring context
	 * 
	 * @return user id
	 */
	public static Long getUserId() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (null != context
				&& context.getAuthentication() != null
				&& context.getAuthentication().getPrincipal() instanceof UserDetails) {
			UserDetails details = (UserDetails) context.getAuthentication()
					.getPrincipal();
			if (details instanceof UserDetailsDecorator) {
				return ((UserDetailsDecorator) details).getUserId();
			} else {
				logger.warn("No authentication in the spring security context");
			}
		}
		return null;
	}

	/**
	 * get user name from spring context <br>
	 * use spring in the jsp. &lt;%@ taglib prefix="sec"
	 * uri="http://www.springframework.org/security/tags" %&gt;<br>
	 * username : &lt;sec:authentication property="name"/&gt;
	 * 
	 * @return user name
	 */
	public static String getUsername() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (null != context
				&& context.getAuthentication() != null
				&& context.getAuthentication().getPrincipal() instanceof UserDetails) {
			UserDetails details = (UserDetails) context.getAuthentication()
					.getPrincipal();
			if (logger.isDebugEnabled()) {
				logger.debug("Read user id = " + details.getUsername()
						+ " from UserDetails.");
			}
			return details.getUsername();
		} else {
			logger.debug("No authentication in the spring security context");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T get(String key) {
		if (THREAD_CONTEXT.get() == null) {
			return null;
		} else {
			return (T) THREAD_CONTEXT.get().get(key);
		}
	}

	public static final void put(String key, Object value) {
		if (THREAD_CONTEXT.get() == null) {
			THREAD_CONTEXT.set(new HashMap<String, Object>());
		}
		THREAD_CONTEXT.get().put(key, value);
	}

	@SuppressWarnings("unchecked")
	public static final <T> T remove(String key) {
		if (THREAD_CONTEXT.get() == null) {
			return null;
		} else {
			return (T) THREAD_CONTEXT.get().remove(key);
		}
	}

	public static final void destroy() {
		THREAD_CONTEXT.set(null);
	}

}

package net.chat.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.chat.domain.Resources;
import net.chat.domain.Roles;
import net.chat.domain.User;
import net.chat.security.UserDetailsDecorator;
import net.chat.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private SecurityService secutiryService;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// Assert.isNull(username, "UserName is Null" + username);
		User user = secutiryService.findUserByName(username);

		if (user == null)
			throw new UsernameNotFoundException(username);

		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);

		UserDetails userdetail = new UserDetailsDecorator(user.getUserId(),
				user.getAccount(), user.getPassWord(), grantedAuths);
		return userdetail;
	}

	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

		List<Roles> roles = user.getRoleList();
		for (Roles role : roles) {
			Set<Resources> tempRes = role.getResourcesList();
			for (Resources res : tempRes) {
				authSet.add(new SimpleGrantedAuthority(res.getName()));
			}
		}
		return authSet;
	}
}

package net.chat.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.User;

@SuppressWarnings("serial")
public class UserDetailsDecorator extends User {

	private long userId;

	public UserDetailsDecorator(long userId, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this(userId, username, password, true, true, true, true, authorities);
	}

	public UserDetailsDecorator(long userId, String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserDetailsImpl [userId=" + userId + ", toString()="
				+ super.toString() + "]";
	}

}

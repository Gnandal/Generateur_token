package com.gev.auth.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	
	private static final long serialVersionUID = -3948086789301792152L;
	
	private User user;
	
	public UserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		user.getPermissionsList().forEach( p ->{
			GrantedAuthority auth = new SimpleGrantedAuthority(p);
			authorities.add(auth);
		});
		
		user.getRolesList().forEach( r ->{
			GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_"+r);
			authorities.add(auth);
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return (user == null)? false : user.isEnable();
	}

}

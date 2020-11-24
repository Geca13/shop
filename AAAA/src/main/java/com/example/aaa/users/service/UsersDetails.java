package com.example.aaa.users.service;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import com.example.aaa.users.entity.Role;
import com.example.aaa.users.entity.Users;


public class UsersDetails implements UserDetails{

	
	
	
	private Users users;
	

	public UsersDetails(Users users) {
		super();
		this.users = users;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return users.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
        
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = users.getRoles();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
	}
	
	

}

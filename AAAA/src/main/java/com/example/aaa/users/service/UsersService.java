package com.example.aaa.users.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.aaa.UserDto;

import com.example.aaa.users.entity.Users;

public interface UsersService extends UserDetailsService {
	
	Users save(Users userDto);
	
	public Page<Users> findPagina(Integer pageNumber, Integer pageSize,String sortField, String sortDirection);

}

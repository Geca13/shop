package com.example.aaa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.aaa.users.entity.Role;
import com.example.aaa.users.entity.RoleName;
import com.example.aaa.users.repository.RoleRepository;




@SpringBootApplication
public class AaaaApplication {

	@Autowired
	RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AaaaApplication.class, args);
		
		
	}/*
		@PostConstruct
		public void init() {
			
			try {
				roleRepository.save(new Role(1, RoleName.ROLE_ADMIN));
				roleRepository.save(new Role(2, RoleName.ROLE_USER));
			} catch (Exception e) {
				System.err.println(e);
			}
		*/
		
	}



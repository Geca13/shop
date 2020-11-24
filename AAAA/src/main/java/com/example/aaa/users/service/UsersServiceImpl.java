package com.example.aaa.users.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Arrays;


import javax.transaction.Transactional;


import com.example.aaa.users.entity.Role;

import com.example.aaa.users.entity.Users;
import com.example.aaa.users.repository.RoleRepository;
import com.example.aaa.users.repository.UsersRepository;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	@Autowired
	private UsersRepository usersRepository;

	
	
	



	@Override
	public Users save(Users userDto) {
		
		//Role role = new Role(2,RoleName.ROLE_USER);
		
		
		Users user = new Users(userDto.getEmail(),passwordEncoder.encode(userDto.getPassword()),userDto.getFirstName(),userDto.getLastName(),userDto.getAge(),userDto.getDate(),  Arrays.asList
				(new Role("ROLE_USER")));
		LocalDate date = LocalDate.now();
		user.setDate(date);
		
		return usersRepository.save(user);
	}
	
	



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = usersRepository.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("You are not signUped with that email");
		}
		
		return new UsersDetails(user);
	}
	
	



	@Override
	public Page<Users> findPagina(Integer pageNumber, Integer pageSize, String sortField, String sortDirection) {
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
		
		return usersRepository.findAll(pageable);
	}
	
	

}

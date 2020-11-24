package com.example.aaa;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.aaa.users.entity.Address;
import com.example.aaa.users.entity.Country;
import com.example.aaa.users.entity.Users;
import com.example.aaa.users.repository.AddressRepository;
import com.example.aaa.users.repository.CountryRepository;
import com.example.aaa.users.repository.UsersRepository;
import com.example.aaa.users.service.UsersDetails;
import com.example.aaa.users.service.UsersServiceImpl;



@Controller

public class MainControllor {
	
	
	@Autowired
	UsersRepository userRepository;
	
	@Autowired
	UsersServiceImpl usersServiceImpl;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String index(Users userdto) {
		
		return "index";
	
	}
	
	@GetMapping("/users")
	public String viewProductPage(Model model) {
		
		 findPage(1,"firstName", "asc", model);
		 
		 return "all_users";
	}
	
	@GetMapping("pages/{pageNumber}")
	public String findPage(@PathVariable("pageNumber") Integer pageNumber,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		
		Integer pageSize = 2;
		
		Page<Users>pages = usersServiceImpl.findPagina(pageNumber, pageSize, sortField, sortDir);
		
		List<Users> listUsers = pages.getContent();
		model.addAttribute("currentPage",pageNumber);
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalItems", pages.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listUsers", listUsers);
		return "all_users";
		
	}
	
	
	
	@GetMapping("/users/profile/{id}")
	public String getProfile(@PathVariable ("id") Integer id, Model model) {
		
		Users user = userRepository.findById(id).get();
		model.addAttribute("user", user);
				
		return "profile";
	}
	
	@GetMapping("/users/profile/changePassword/{id}")
	public String changePassword(@PathVariable ("id") Integer id, Model model ) {
		
		Users user = userRepository.findById(id).get();
	
		
		model.addAttribute("user", user);
		
	
		return "changePassword";
		
	}
	@PostMapping("/users/profile/changePassword/{id}")
	  public String passwordForm( @PathVariable ("id") Integer id,@ModelAttribute("user")Users user) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Users user1 = userRepository.findById(id).get();
		
		user1.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user1);
		
		return "redirect:/users/profile/" + user1.getId() ;
		
	}
	
	@GetMapping("/profile")
	public String profile( @AuthenticationPrincipal UsersDetails userD, Model model) {
		
		String userEmail = userD.getUsername();
        Users user = userRepository.findByEmail(userEmail);
        List<Address> listAddress = new ArrayList<>();
        List<Address> listAddresss = addressRepository.findAll();
        for (Address address : listAddresss) {
			
        	if(address.getUser().getId() == user.getId()) {
        		listAddress.add(address);
        	}
		}
        
		model.addAttribute("user", user);
		model.addAttribute("listAddress", listAddress);
		
				
		return "profile" ;
	}
	
	@GetMapping("/profile/{id}/newAddress")
	public String setAddress(@PathVariable ("id") Integer id, @AuthenticationPrincipal UsersDetails userD, Model model,@ModelAttribute("address") Address address) {
		
	
		Users user = userRepository.findById(id).get();
		
        List<Country> listCountry = countryRepository.findAll();
        
        model.addAttribute("user", user);
		model.addAttribute("listCountry", listCountry);
		
				
		return "new_Address";
	}
	
	
	@PostMapping("/profile/{id}/newAddress")
	public String processCreationForm(@PathVariable("id")Integer id, Address address) {
		
		
		Users user = userRepository.findById(id).get();
		Address  address1 = new Address();
		address1.setStreetName(address.getStreetName());
		address1.setStreetNumber(address.getStreetNumber());
		address1.setCity(address.getCity());
		address1.setZipCode(address.getZipCode());
		address1.setCountry(address.getCountry());
		address1.setUser(user);
		
		addressRepository.save(address1);
		
		
		
	      return "redirect:/profile"  ;
		
	}
	
	
	
	
	
}




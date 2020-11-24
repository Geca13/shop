package com.example.aaa.users.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.aaa.UserDto;
import com.example.aaa.users.entity.Users;
import com.example.aaa.users.service.UsersService;

@Controller
@RequestMapping("/signUpForm")
public class UsersController {
	
	private UsersService usersService;

	public UsersController(UsersService usersService) {
		super();
		this.usersService = usersService;
	}
	
	@ModelAttribute("user")
	public Users userDto() {
		return new Users();
	}
	
	
	@GetMapping
	public String showSignUpForm(Model model) {
		
	    return "signUpForm";
	}
	
	@PostMapping
	public String registerUser(@ModelAttribute("user") Users userDto) {
		
		usersService.save(userDto);
		
		return  "redirect:/signUpForm?success" ;
	}
	
	
	
	
	

}

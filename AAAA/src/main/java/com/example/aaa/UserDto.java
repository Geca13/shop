package com.example.aaa;

import java.time.LocalDate;
import java.util.List;

import com.example.aaa.users.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String email;
	
	private String password;
	
    private String firstName;
	
	private String lastName;
	
	private Integer age;
	
	private LocalDate date;
	
	private List<Address> address;

}

package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.UserDTO;
import com.spring.model.MessageAlertModel;
import com.spring.service.IUserService;

@RestController
public class AuthenticAPI {
	@Autowired
	private IUserService userService;
	
	
	@PostMapping(value = "/api/register")
	public MessageAlertModel register(@RequestBody UserDTO dto) {
		

		return userService.register(dto);
	}
	 
	 
	@PostMapping(value = "/api/login")
	public MessageAlertModel login(@RequestBody UserDTO dto){
		
        return userService.login(dto.getUserName(), dto.getPassword());
    }
	
	@PostMapping(value = "/api/forgotpassword")
	public MessageAlertModel forgotPassword(@RequestBody UserDTO dto){
		
		 return userService.forgotPassword(dto.getEmail());
    }
}

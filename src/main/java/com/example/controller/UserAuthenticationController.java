package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserAuthentication;
import com.example.service.SecurityService;
import com.example.service.UserService;

@RestController
public class UserAuthenticationController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@PostMapping("/registration")
	public String registrationPOST(@RequestBody UserAuthentication userAuthentication) {
		userService.save(userAuthentication);

		securityService.autoLogin(userAuthentication.getUsername(), userAuthentication.getPasswordConfirm());
		return "Successfull";

	}

}

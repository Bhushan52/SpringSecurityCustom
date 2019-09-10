package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserAuthentication;
import com.example.service.SecurityService;
import com.example.service.UserService;

@RestController
public class UserAuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@PostMapping(value= {"/registration"},consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> registrationPOST(@RequestBody UserAuthentication userAuthentication) {
		userService.save(userAuthentication);

		securityService.autoLogin(userAuthentication.getUsername(), userAuthentication.getPasswordConfirm());
		return new ResponseEntity<>("Hello, you!", HttpStatus.OK);

	}
	
	@PostMapping(value = "/login-custom")
	public ResponseEntity<String> loginPage(@RequestParam("username")String username,
			@RequestParam("password")String password) {
		securityService.autoLogin(username, password);
	    return new ResponseEntity<>("Hello, you!", HttpStatus.OK);
	}
	 
	@GetMapping(value="/logout-custom")
	public ResponseEntity<String> logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return new ResponseEntity<>("Successfully logged out!", HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/api/to-do")
	public ResponseEntity<String> todoPOST(@RequestParam("task")String task) {
		
		logger.info("todddodod:{}",task);
	    return new ResponseEntity<>("Hello, you!", HttpStatus.OK);
	}
	
	@PostMapping(value = "/admin/create-user")
	public ResponseEntity<String> todoTest(@RequestParam("para")String task) {
		
		logger.info("todddodod:{}",task);
	    return new ResponseEntity<>("Hello, you!", HttpStatus.OK);
	}

	
}

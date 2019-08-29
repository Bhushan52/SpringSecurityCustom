package com.example.samplespringsecurity;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.model.Role;
import com.example.model.UserAuthentication;

@SpringBootApplication
public class DemoApplication {

@Autowired
UserDetailsService userDetailsService;


	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	 @PostConstruct
	    public void init(){
	        UserAuthentication user = new UserAuthentication();
	        
	        user.setFirstName("Bhushan");
	        user.setLastName("Chaudhari");
	        user.setUsername("bhushanchaudhari5252@gmail.com");
	                
	        user.setRoles(Arrays.asList(
	                        new Role("ROLE_USER"),
	                        new Role("ROLE_ADMIN")));
	     
	        userDetailsService.loadUserByUsername(user.getUsername());
	 }
	   
}

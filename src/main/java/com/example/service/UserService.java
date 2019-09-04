package com.example.service;

import org.springframework.security.core.userdetails.User;

import com.example.model.UserAuthentication;

public interface UserService {

	void save(UserAuthentication userAuthentication);

	UserAuthentication findByUsername(String username);
}

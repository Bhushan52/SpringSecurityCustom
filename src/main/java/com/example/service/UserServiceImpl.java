package com.example.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.UserAuthentication;
import com.example.repository.RoleRepository;
import com.example.repository.UserAuthenticationRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAuthenticationRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(UserAuthentication userAuthentication) {
		userAuthentication.setPassword((bCryptPasswordEncoder.encode(userAuthentication.getPassword())));
		
		userAuthentication.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(userAuthentication);

	}

	@Override
	public UserAuthentication findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}

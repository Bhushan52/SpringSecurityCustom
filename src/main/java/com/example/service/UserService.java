package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.repository.UserRepository;

@Service
public class UserService implements /* IUserService,*/UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userRepository.findByUserName(username);
		if (userDetails == null) {
			throw new UsernameNotFoundException(username);
		}
		return userDetails;
	}

	/*@Override
	public void save(UserAuthentication user) {
		if (this.loadUserByUsername(user.getUsername()) == null) {
			userRepository.save(user);
		}

	}*/
}

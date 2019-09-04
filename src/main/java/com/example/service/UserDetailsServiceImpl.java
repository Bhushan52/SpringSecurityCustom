package com.example.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.UserAuthentication;
import com.example.repository.UserAuthenticationRepository;

@Service
public class UserDetailsServiceImpl  implements  /*IUserService,*/UserDetailsService{

	@Autowired
	private UserAuthenticationRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserAuthentication userAuthentication = userRepository.findByUsername(username);
		if (userAuthentication == null) {
			throw new UsernameNotFoundException(username);
		}
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		 for (Role role : userAuthentication.getRoles()){
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
		return new User(userAuthentication.getUsername(), userAuthentication.getPassword(), grantedAuthorities);
	}

	/*@Override
	public void save(UserAuthentication user) {
		if (this.loadUserByUsername(user.getUsername()) == null) {
			userRepository.save(user);
		}

	}*/
}
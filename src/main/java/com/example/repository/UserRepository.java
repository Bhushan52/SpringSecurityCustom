package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.model.UserAuthentication;

public interface UserRepository extends JpaRepository<UserDetails, Integer>{

	UserAuthentication findByUserName(String username);

}
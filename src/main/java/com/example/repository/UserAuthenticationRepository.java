package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.UserAuthentication;

public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long>{

	UserAuthentication findByUsername(String username);
}

package com.SE2024.SocialBookStore.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SE2024.SocialBookStore.model.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
}
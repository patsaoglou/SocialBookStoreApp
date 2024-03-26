package com.SE2024.SocialBookStore.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SE2024.SocialBookStore.model.UserProfile;

@Repository
public interface UserProfileDAO extends JpaRepository<UserProfile, Integer>{

    public UserProfile findById(int id);
}
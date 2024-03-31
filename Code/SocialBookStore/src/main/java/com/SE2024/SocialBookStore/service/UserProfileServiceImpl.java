package com.SE2024.SocialBookStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SE2024.SocialBookStore.dao.UserProfileDAO;
import com.SE2024.SocialBookStore.model.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService{
    
    @Autowired
    UserProfileDAO userProfileDAO;
   
    public UserProfile registerUserProfileData(UserProfile profileData){
        UserProfileServiceValidator validator = new UserProfileServiceValidator(userProfileDAO);
        validator.validateUserProfileObject(profileData);
        userProfileDAO.save(profileData);

        return userProfileDAO.findByUsername(profileData.getUsername());        
    }
    
    public UserProfile retrieveUserProfile(String username){
        UserProfileServiceValidator validator = new UserProfileServiceValidator(userProfileDAO);
        UserProfile user = validator.validateUsername(username);

        return user;        
    }

}

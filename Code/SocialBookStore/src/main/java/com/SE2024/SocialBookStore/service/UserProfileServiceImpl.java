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
        validateUserProfileObject(profileData);
        userProfileDAO.save(profileData);

        return userProfileDAO.findByUsername(profileData.getUsername());        
    }

    private void validateUserProfileObject(UserProfile profileData){
        validateIfUsernameNull(profileData.getUsername());
        validateUsernameExistance(profileData.getUsername());
        validateFirstName(profileData.getFirstName());
        validateLastName(profileData.getLastName());
        validateTelephone(profileData.getTelephone());
        validateAddress(profileData.getAddress());
        validateAge(profileData.getAge());
    }

    private void validateUsernameExistance(String username){
        if (userProfileDAO.findByUsername(username) != null){
            throw new IllegalArgumentException("Username already exists");
        }
    }
    
    private void validateIfUsernameNull(String username){
        if (username == null){
            throw new IllegalArgumentException("Username is null");
        }
    }
    
    private void validateFirstName(String firstName){
        if (firstName == null){
            throw new IllegalArgumentException("First name cannot be null");
        }
    }

    private void validateLastName(String lastName){
        if (lastName == null){
            throw new IllegalArgumentException("Last name cannot be null");
        }
    }
    
    private void validateTelephone(String telephone){
        if (telephone == null){
            throw new IllegalArgumentException("Telephone cannot be null");
        }
    }

    private void validateAddress(String address){
        if (address == null){
            throw new IllegalArgumentException("Address cannot be null");
        }
    }

    private void validateAge(int age){
        if (age <= 0){
            throw new IllegalArgumentException("Age must be >0");
        }
    }

}

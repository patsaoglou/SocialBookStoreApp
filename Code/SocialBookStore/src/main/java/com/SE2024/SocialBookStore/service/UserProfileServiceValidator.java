package com.SE2024.SocialBookStore.service;

import org.springframework.stereotype.Service;

import com.SE2024.SocialBookStore.dao.UserProfileDAO;
import com.SE2024.SocialBookStore.model.UserProfile;

@Service
public class UserProfileServiceValidator {

    UserProfileDAO userProfileDAO;

    public UserProfileServiceValidator(UserProfileDAO userProfileDAO) {
        this.userProfileDAO = userProfileDAO;
    }

    public void validateUserProfileObject(UserProfile profileData){
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

    public UserProfile validateUsername(String username){
        validateIfUsernameNull(username);
        return validateUsernameNonExistance(username);
    }

    private void validateIfUsernameNull(String username){
        if (username == null){
            throw new IllegalArgumentException("Username is null");
        }
    }
    
    private UserProfile validateUsernameNonExistance(String username){
        UserProfile user = userProfileDAO.findByUsername(username);
        
        if (user == null){
            throw new IllegalArgumentException("Username doesn't exists");
        } 
        return user;
    }

}

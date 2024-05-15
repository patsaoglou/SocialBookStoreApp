package com.SE2024.SocialBookStore.service.userProfile;

import com.SE2024.SocialBookStore.dtos.UserProfileDTO;

public interface UserProfileService {
    
    public UserProfileDTO retrieveUserProfile(String username);
    public UserProfileDTO registerUserProfileData(UserProfileDTO userProfile);
    public void addFavouriteAuthor(String authorName, String username);
    public void addFavouriteCategory(String categoryName, String username);
} 
